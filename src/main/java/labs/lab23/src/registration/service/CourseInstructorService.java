package labs.lab23.src.registration.service;


import labs.lab23.src.registration.model.Instructor;
import labs.lab23.src.registration.model.Student;

import java.util.List;

/**
 * Интерфейс сервиса для преподавателя
 */
public interface CourseInstructorService {
    
    /**
     * @param courseId идентификатор курса
     * @return список студентов, зарегистрированных на данный курс
     */
    List<Student> findStudentsByCourseId(long courseId);

    /**
     * @param instructorId идентификатор преподавателя
     * @return список студентов, посещающих один из курсов данного преподавателя
     */
    List<Student> findStudentsByInstructorId(long instructorId);

    /**
     * @param instructorId идентификатор преподавателя
     * @param courseId идентификатор курса
     * @return список преподавателей, которые могут прочитать данный курс вместо данного преподавателя
     */
    List<Instructor> findReplacement(long instructorId, long courseId);

}
