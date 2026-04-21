package task_2.api;

public abstract class BaseApiClient {

    protected static final String BASE_URL = "http://localhost:8080";

    public BaseApiClient() {
        Specifications.installSpecification(BASE_URL);
    }
}