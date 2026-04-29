package task_2.basetest;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeAll;
import task_2.steps.AuthSteps;

import java.io.InputStream;
import java.util.Map;

public abstract class BaseTest {

    protected static AuthSteps authSteps;
    protected static Map<String, String> credentials;
    protected static final ObjectMapper objectMapper = new ObjectMapper();

    @BeforeAll
    static void baseSetUp() throws Exception {
        authSteps = new AuthSteps();
        try (InputStream inputStream = BaseTest.class.getClassLoader()
                .getResourceAsStream("user.json")) {
            credentials = objectMapper.readValue(inputStream, Map.class);
        }
    }
}