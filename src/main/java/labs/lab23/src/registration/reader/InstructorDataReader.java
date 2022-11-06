package labs.lab23.src.registration.reader;

import com.fasterxml.jackson.databind.ObjectMapper;
import labs.lab23.src.registration.model.Instructor;

import java.io.File;
import java.io.IOException;

public class InstructorDataReader {

    private ObjectMapper objectMapper = new ObjectMapper();

    public Instructor[] readCourseInstructorData() throws IOException {
        return objectMapper.readValue(new File("src/main/java/labs/lab23/src/resources/instructors.json"), Instructor[].class);
    }

}
