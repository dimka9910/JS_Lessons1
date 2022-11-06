package labs.lab2.src.registration.reader;

import com.fasterxml.jackson.databind.ObjectMapper;
import labs.lab2.src.registration.model.CourseInfo;
import labs.lab2.src.registration.model.CourseInstance;
import labs.lab2.src.registration.model.Student;

import java.io.File;
import java.io.IOException;

/**
 * Класс для чтения информации о курсах из файлов
 */
public class CourseDataReader {

    private ObjectMapper objectMapper = new ObjectMapper();

    /**
     * @return список с данными о проведении всех курсов. Один курс (например, дискретная математика) может быть проведен несколько раз.
     */
    public CourseInstance[] readCourseInstance() throws IOException {
        return objectMapper.readValue(new File("src/main/java/labs/lab2/src/resources/courseInstances.json"), CourseInstance[].class);
    }

    /**
     * @return список базовой информации по каждому курсу
     */
    public CourseInfo[] readCourseInfo() throws IOException {
        return objectMapper.readValue(new File("src/main/java/labs/lab2/src/resources/courseInfos.json"), CourseInfo[].class);
    }
}
