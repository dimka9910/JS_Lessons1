package labs.lab23.src.registration.reader;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import labs.lab23.src.registration.model.Student;

import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * Класс для чтения информации о студентах из файлов
 */
public class StudentDataReader {

    private ObjectMapper objectMapper = new ObjectMapper();

    /**
     * @return список студентов-бакалавров
     */
    public List<Student> readBachelorStudentData() throws IOException {
        return objectMapper.readValue(new File("src/main/java/labs/lab23/src/resources/bachelorStudents.json"), new TypeReference<>() {
        });
    }

    /**
     * @return список студентов-магистров
     */
    public List<Student> readMasterStudentData() throws IOException {
        return objectMapper.readValue(new File("src/main/java/labs/lab23/src/resources/masterStudents.json"), new TypeReference<>() {
        });
    }

}
