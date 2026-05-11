package hooks;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeAll;
import task_2.api.AuthApiClient;
import task_2.spec.Specifications;
import task_2.steps.AuthSteps;

import java.io.InputStream;
import java.util.Map;

public class ServerHooks {

    protected static AuthSteps authSteps;
    protected static Map<String, String> credentials;

    private static final ObjectMapper mapper = new ObjectMapper();

    @BeforeAll
    static void setup() throws Exception {

        RestAssured.requestSpecification =
                Specifications.requestSpec("http://localhost:8080");

        AuthApiClient apiClient = new AuthApiClient();
        authSteps = new AuthSteps(apiClient);

        try (InputStream is = ServerHooks.class
                .getClassLoader()
                .getResourceAsStream("user.json")) {

            credentials = mapper.readValue(is, Map.class);
        }
    }
}