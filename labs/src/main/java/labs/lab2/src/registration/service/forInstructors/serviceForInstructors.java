package labs.lab2.src.registration.service.forInstructors;

import labs.lab2.src.registration.model.Instructor;
import labs.lab2.src.registration.model.Student;

public class serviceForInstructors implements CourseInstructorService{
    @Override
    public Student[] findStudentsByCourseId(long courseId) {
        return new Student[0];
    }

    @Override
    public Student[] findStudentsByInstructorId(long instructorId) {
        return new Student[0];
    }

    @Override
    public Instructor[] findReplacement(long instructorId, long courseId) {
        return new Instructor[0];
    }
}
