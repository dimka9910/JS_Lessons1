package labs.lab23.src.registration.reader;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import labs.lab23.src.registration.model.CourseInfo;
import labs.lab23.src.registration.model.CourseInstance;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class CourseDataReader {

    private ObjectMapper objectMapper = new ObjectMapper();

    public CourseDataReader() {
        objectMapper.registerModule(new JavaTimeModule());
    }

    public List<CourseInfo> readCourseInfoData() throws IOException {
        return objectMapper.readValue(new File("src/main/java/labs/lab23/src/resources/courseInfos.json"), new TypeReference<List<CourseInfo>>(){});
    }

    public List<CourseInstance> readCourseInstancesData() throws IOException {
        return objectMapper.readValue(new File("src/main/java/labs/lab23/src/resources/courseInstances.json"), new TypeReference<List<CourseInstance>>(){});
    }
}
