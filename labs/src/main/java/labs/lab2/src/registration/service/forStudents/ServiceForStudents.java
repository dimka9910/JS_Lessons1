package labs.lab2.src.registration.service.forStudents;

import labs.lab2.src.registration.model.*;
import labs.lab2.src.registration.reader.CourseDataReader;
import labs.lab2.src.registration.reader.StudentDataReader;
import labs.lab2.src.registration.service.common.CourseUtilityMethods;
import labs.lab2.src.registration.service.common.StudentUtilityMethods;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;

public class ServiceForStudents implements StudentService {

    //Подписки студентов на курсы key - studentId, value - instanceId's
    public static HashMap<Long, ArrayList<CourseInstance>> studentsSubscriptions = new HashMap<>();

    private Student[] bachelorStudents;
    private Student[] masterStudents;
    private CourseInstance[] courseInstances;
    private CourseInfo[] courseInfos;
    private StudentUtilityMethods studentUtilityMethods = new StudentUtilityMethods();
    private CourseUtilityMethods courseUtilityMethods = new CourseUtilityMethods();

    public ServiceForStudents() {
        try {
            CourseDataReader courseDataReader = new CourseDataReader();
            StudentDataReader studentDataReader = new StudentDataReader();

            bachelorStudents = studentDataReader.readBachelorStudentData();
            masterStudents = studentDataReader.readBachelorStudentData();
            courseInstances = courseDataReader.readCourseInstance();
            courseInfos = courseDataReader.readCourseInfo();
        } catch (Exception e) {
            System.out.println("ServiceForStudents error in initialization");
            e.printStackTrace();
        }
    }

    @Override
    public ActionStatus subscribe(long studentId, long courseId) {
        ArrayList<CourseInstance> instancesToSubscribe = courseUtilityMethods.findCourseInstances(courseInstances, courseId);

        boolean isStudentAllowedToCourse = courseUtilityMethods.isStudentAllowedToCourse(
                studentUtilityMethods.defineStudentCategory(studentId, bachelorStudents, masterStudents),
                courseInfos,
                courseId);
        boolean isAllPrerequisitesAreDone = courseUtilityMethods.isAllPrerequisitesAreDone(
                studentUtilityMethods.findStudent(studentId, bachelorStudents, masterStudents).getCompletedCourses(),
                courseUtilityMethods.findCourseInfo(courseInfos, courseId).getPrerequisites());

        if (isStudentAllowedToCourse & isAllPrerequisitesAreDone) {
            for (CourseInstance currentInstance : instancesToSubscribe) {
                if (courseUtilityMethods.isCourseStarted(currentInstance) & courseUtilityMethods.isCourseHavePlace(currentInstance)) {
                    if (!studentsSubscriptions.containsKey(studentId)) {
                        studentsSubscriptions.put(studentId, new ArrayList<>());
                    }
                    studentsSubscriptions.get(studentId).add(currentInstance);
                    currentInstance.setCapacity(currentInstance.getCapacity() - 1);
                    return ActionStatus.valueOf("OK");
                }
            }
        }

        return ActionStatus.valueOf("NOK");
    }

    @Override
    public ActionStatus unsubscribe(long studentId, long courseId) {
        ArrayList<CourseInstance> instancesToUnsubscribe = courseUtilityMethods.findCourseInstances(courseInstances, courseId);

        for (CourseInstance currentInstance : instancesToUnsubscribe) {
            studentsSubscriptions.get(studentId).remove(currentInstance.getId());
        }

        return ActionStatus.valueOf("OK");
    }

    @Override
    public CourseInstance[] findAllSubscriptionsByStudentId(long studentId) {
        CourseInstance[] courseInstancesOfStudent = new CourseInstance[studentsSubscriptions.get(studentId).size()];

        int i = 0;
        for (CourseInstance currentInstance : studentsSubscriptions.get(studentId)){
            courseInstancesOfStudent[i] = currentInstance;
        }

        return courseInstancesOfStudent;
    }
}
