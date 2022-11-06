package labs.lab2.src.registration.reader;

import com.fasterxml.jackson.databind.ObjectMapper;
import labs.lab2.src.registration.model.CourseInfo;
import labs.lab2.src.registration.model.CourseInstance;
import labs.lab2.src.registration.model.Instructor;

import java.io.File;
import java.io.IOException;

public class InstructorsReader {
    private ObjectMapper objectMapper = new ObjectMapper();

    /**
     * @return список преподавателей
     */
    public Instructor[] readInstructors() throws IOException {
        return objectMapper.readValue(new File("src/main/java/labs/lab2/src/resources/instructors.json"), Instructor[].class);
    }
}
