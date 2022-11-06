package labs.lab23.src.registration.reader;

import com.fasterxml.jackson.databind.ObjectMapper;
import labs.lab23.src.registration.model.Student;

import java.io.File;
import java.io.IOException;

/**
 * Класс для чтения информации о студентах из файлов
 */
public class StudentDataReader {

    private ObjectMapper objectMapper = new ObjectMapper();

    /**
     * @return список студентов-бакалавров
     */
    public Student[] readBachelorStudentData() throws IOException {
        return objectMapper.readValue(new File("src/main/java/labs/lab23/src/resources/bachelorStudents.json"), Student[].class);
    }

    /**
     * @return список студентов-магистров
     */
    public Student[] readMasterStudentData() throws IOException {
        return objectMapper.readValue(new File("src/main/java/labs/lab23/src/resources/masterStudents.json"), Student[].class);
    }

}
