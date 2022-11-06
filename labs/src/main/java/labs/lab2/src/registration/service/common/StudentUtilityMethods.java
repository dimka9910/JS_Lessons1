package labs.lab2.src.registration.service.common;

import labs.lab2.src.registration.model.Student;
import labs.lab2.src.registration.model.StudentCategory;

public class StudentUtilityMethods {

    public Student findStudent(long studentId, Student[] bachelorStudents, Student[] masterStudents) {
        Student studentToFind = iterateStudentList(bachelorStudents, studentId);

        if (studentToFind == null) {
            return iterateStudentList(masterStudents, studentId);
        } else {
            return studentToFind;
        }
    }

    public StudentCategory defineStudentCategory(long studentId, Student[] bachelorStudents, Student[] masterStudents) {
        if (iterateStudentList(bachelorStudents, studentId) != null) {
            return StudentCategory.valueOf("BACHELOR");
        } else if (iterateStudentList(masterStudents, studentId) != null) {
            return StudentCategory.valueOf("MASTER");
        } else {
            return null;
        }
    }

    public Student iterateStudentList(Student[] students, long studentId) {
        for (Student currentStudent : students) {
            if (currentStudent.getId() == studentId)
                return currentStudent;
        }
        return null;
    }
}
