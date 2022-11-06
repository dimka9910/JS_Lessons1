package labs.lab23.src.registration.service.implementation;

import labs.lab23.src.registration.model.*;
import labs.lab23.src.registration.reader.*;
import labs.lab23.src.registration.service.CourseInstructorService;
import labs.lab23.src.registration.service.StudentService;

import java.io.IOException;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Stream;

import static labs.lab23.src.registration.model.StudentCategory.BACHELOR;
import static labs.lab23.src.registration.model.StudentCategory.MASTER;

public class CourseInstructorAndStudentServiceImpl implements StudentService, CourseInstructorService {
    private List<Student> bachelorStudents;
    private List<Student> masterStudents;
    private List<Instructor> instructors;
    private List<CourseInfo> courseInfos;
    private List<CourseInstance> courseInstances;
    private Map<Long, List<Long>> subscriptions;

    public CourseInstructorAndStudentServiceImpl() {

        subscriptions = new HashMap<>();

        try {
            StudentDataReader reader = new StudentDataReader();
            bachelorStudents = reader.readBachelorStudentData();
            masterStudents = reader.readMasterStudentData();

            CourseDataReader reader2 = new CourseDataReader();
            courseInfos = reader2.readCourseInfoData();
            courseInstances = reader2.readCourseInstancesData();

            InstructorDataReader reader3 = new InstructorDataReader();
            instructors = reader3.readCourseInstructorData();
        } catch (IOException e) {
            System.out.println("Cannot load data");
        }
    }


    @Override
    public ActionStatus subscribe(long studentId, long courseId) {
        var courseInstance = courseInstances.stream().filter(v -> v.getCourseId() == courseId)
                .filter(v -> v.getStartDate().isAfter(LocalDate.now()))
                .filter(v -> v.getCapacity() > subscriptions.values().stream().flatMap(Collection::stream).filter(x -> x == courseId).count())
                .min(Comparator.comparing(CourseInstance::getStartDate)).orElse(null);


        if (courseInstance == null) return ActionStatus.NOK;

        var courseInfo = courseInfos.stream()
                .filter(c -> c.getId() == courseInstance.getCourseId()).findFirst().orElse(null);

        if (courseInfo == null) return ActionStatus.NOK;

        List<Long> lst = subscriptions.get(studentId);
        if (lst == null) {
            lst = new ArrayList<>();
            subscriptions.put(studentId, lst);
        }

        var student = Stream.concat(bachelorStudents.stream(), masterStudents.stream())
                .filter(s -> s.getId() == studentId).filter(s -> subscriptions.get(s.getId()).stream()
                        .filter(x -> x == courseInstance.getId()).count() == 0).findFirst().orElse(null);

        if (student == null) return ActionStatus.NOK;

        var prerequisites = courseInfo.getPrerequisites();
        if (prerequisites != null) {
            if (!Arrays.stream(prerequisites).allMatch(p -> Arrays.stream(student.getCompletedCourses()).anyMatch(c -> c == p)))
                return ActionStatus.NOK;
        }

        lst.add(courseInstance.getId());

        return ActionStatus.OK;
    }

    @Override
    public ActionStatus unsubscribe(long studentId, long courseId) {
        var courseInstanceIds = courseInstances.stream()
                .filter(ci -> ci.getCourseId() == courseId)
                .map(CourseInstance::getId);

        List<Long> studentCI = subscriptions.get(studentId);
        if (studentCI == null) return ActionStatus.NOK;

        if (studentCI.removeAll(courseInstanceIds.toList())) return ActionStatus.OK;
        return ActionStatus.NOK;
    }

    @Override
    public List<CourseInstance> findAllSubscriptionsByStudentId(long studentId) {
        return courseInstances.stream().filter(ci -> subscriptions.get(studentId)
                .stream().anyMatch(c -> c == ci.getId())).toList();
    }

    @Override
    public List<Student> findStudentsByCourseId(long courseId) {
        List<CourseInstance> instances = courseInstances.stream().filter(x -> x.getCourseId() == courseId).toList();

        var studentsIds = subscriptions.entrySet().stream().filter(s -> s.getValue().stream()
                .anyMatch(x -> instances.stream().anyMatch(i -> i.getId() == x))).map(Map.Entry::getKey).toList();

        return Stream.concat(bachelorStudents.stream(), masterStudents.stream())
                .filter(s -> studentsIds.contains(s.getId())).toList();
    }

    @Override
    public List<Student> findStudentsByInstructorId(long instructorId) {
        var canTeach = instructors.stream().filter(x -> x.getId() == instructorId).findFirst()
                .map(x -> Arrays.stream(x.getCanTeach()).mapToObj(Long::valueOf).toList()).orElse(null);
        if (canTeach == null) return new ArrayList<>();
        return canTeach.stream().flatMap(x -> findStudentsByCourseId(x).stream()).toList();
    }

    @Override
    public List<Instructor> findReplacement(long instructorId, long courseId) {
        var canTeach = instructors.stream()
                .filter(x -> x.getId() == instructorId)
                .findFirst()
                .filter(x -> Arrays.stream(x.getCanTeach()).anyMatch(v -> v == courseId))
                .orElse(null);

        if (canTeach == null) return new ArrayList<>();

        return instructors.stream().filter(x -> x.getId() != instructorId)
                .filter(x -> Arrays.stream(x.getCanTeach()).anyMatch(y -> y == courseId)).toList();
    }
}
