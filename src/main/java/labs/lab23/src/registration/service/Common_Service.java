package labs.lab23.src.registration.service;

import labs.lab23.src.registration.model.*;
import labs.lab23.src.registration.reader.*;

import java.io.IOException;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Stream;

import static labs.lab23.src.registration.model.StudentCategory.BACHELOR;
import static labs.lab23.src.registration.model.StudentCategory.MASTER;

public class Common_Service implements StudentService, CourseInstructorService {
    private Student[] bachelorStudents;
    private Student[] masterStudents;
    private Instructor[] instructors;
    private CourseInfo[] courseInfos;
    private CourseInstance[] courseInstances;
    private Map<Long, List<Long>> subscriptions;

    public Common_Service() {
        subscriptions = new HashMap<>();

        try {
            loadData();
        } catch (IOException e) {
            System.out.println("Cannot load data");
        }
    }

    private void loadData() throws IOException {
        StudentDataReader reader = new StudentDataReader();
        bachelorStudents = reader.readBachelorStudentData();
        masterStudents = reader.readMasterStudentData();

        CourseDataReader reader2 = new CourseDataReader();
        courseInfos = reader2.readCourseInfoData();
        courseInstances = reader2.readCourseInstancesData();

        InstructorDataReader reader3 = new InstructorDataReader();
        instructors = reader3.readCourseInstructorData();
    }


    @Override
    public ActionStatus subscribe(long studentId, long courseId) {
        var courseInstance = Arrays.stream(courseInstances).filter(ci -> ci.getCourseId() == courseId)
                .filter(ci -> ci.getStartDate().isAfter(LocalDate.now()))
                .filter(ci -> ci.getCapacity() > subscriptions.values().stream().flatMap(Collection::stream)
                        .filter(x -> x == courseId).count())
                .min(Comparator.comparing(CourseInstance::getStartDate)).orElse(null);
        if (courseInstance == null) return ActionStatus.NOK;

        var courseInfo = Arrays.stream(courseInfos)
                .filter(c -> c.getId() == courseInstance.getCourseId()).findFirst().orElse(null);

        if (courseInfo == null) return ActionStatus.NOK;

        List<Long> lst = subscriptions.get(studentId);
        if (lst == null) {
            lst = new ArrayList<>();
            subscriptions.put(studentId, lst);
        }

        var student = Arrays.stream(courseInfo.getStudentCategories()).flatMap(x -> {
                    if (x.equals(BACHELOR)) {
                            return Arrays.stream(bachelorStudents);
                    }
                    else if (x.equals(MASTER)){
                        return Arrays.stream(masterStudents);
                    }
                    return Stream.empty();
                })
                .filter(s -> s.getId() == studentId).filter(s -> subscriptions.get(s.getId()).stream()
                        .filter(x -> x == courseInstance.getId()).count() == 0).findFirst().orElse(null);

        if (student == null) return ActionStatus.NOK;

        var prerequisites = courseInfo.getPrerequisites();
        if (prerequisites != null) {
            if(!Arrays.stream(prerequisites).allMatch(p -> 1 == Arrays.stream(student.getCompletedCourses())
                    .filter(c -> c == p).findFirst().stream().count()))
                return ActionStatus.NOK;
        }

        lst.add(courseInstance.getId());

        return ActionStatus.OK;
    }

    @Override
    public ActionStatus unsubscribe(long studentId, long courseId) {
        var courseInstanceIds = Arrays.stream(courseInstances)
                .filter(ci -> ci.getCourseId() == courseId)
                .map(CourseInstance::getId);

        List<Long> studentCI = subscriptions.get(studentId);
        if (studentCI == null) return ActionStatus.NOK;

        if (studentCI.removeAll(courseInstanceIds.toList())) return ActionStatus.OK;
        return ActionStatus.NOK;
    }

    @Override
    public CourseInstance[] findAllSubscriptionsByStudentId(long studentId) {
        var courseInstanceIds = Arrays.stream(courseInstances).filter(ci -> subscriptions.get(studentId)
                .stream().anyMatch(c -> c == ci.getId())).toList();
        long count = courseInstanceIds.size();
        return courseInstanceIds.toArray(new CourseInstance[(int)count]);
    }

    @Override
    public Student[] findStudentsByCourseId(long courseId) {
        List<CourseInstance> instances = Arrays.stream(courseInstances).filter(x -> x.getCourseId() == courseId).toList();

        var studentsIds = subscriptions.entrySet().stream().filter(s -> s.getValue().stream()
                .anyMatch(x -> instances.stream().anyMatch(i -> i.getId() == x))).map(Map.Entry::getKey).toList();

        var students = Arrays.stream(StudentCategory.values()).flatMap(x -> {
                    if (x.equals(BACHELOR)) {
                            return Arrays.stream(bachelorStudents);
                    }
                    else if (x.equals(MASTER)){
                        return Arrays.stream(masterStudents);
                    }
                    return Stream.empty();
                })
                .filter(s -> studentsIds.contains(s.getId())).toList();
        long count = students.size();
        return students.toArray(new Student[(int)count]);
    }

    @Override
    public Student[] findStudentsByInstructorId(long instructorId) {
        var canTeach = Arrays.stream(instructors).filter(x -> x.getId() == instructorId).findFirst()
                .map(x -> Arrays.stream(x.getCanTeach()).mapToObj(Long::valueOf).toList()).orElse(null);

        if (canTeach == null) return new Student[0];
        var students = canTeach.stream().flatMap(x -> Arrays.stream(findStudentsByCourseId(x))).toList();
        return students.toArray(new Student[(int)students.size()]);
    }

    @Override
    public Instructor[] findReplacement(long instructorId, long courseId) {
        var canTeach = Arrays.stream(instructors).filter(x -> x.getId() == instructorId).findFirst()
                .map(x -> Arrays.stream(x.getCanTeach()).mapToObj(Long::valueOf).toList()).orElse(null);

        if (canTeach == null) return new Instructor[0];
        var ins = Arrays.stream(instructors).filter(x -> x.getId() != instructorId)
                .filter(x -> Arrays.stream(x.getCanTeach()).anyMatch(y -> y == courseId)).toList();
        return ins.toArray(new Instructor[(int)ins.size()]);
    }
}