package task_2.context;

import io.restassured.response.ValidatableResponse;
import task_2.dto.UserDto;

public class AuthContext {

    public String email;
    public String password;
    public UserDto user;
    public String token;
    public ValidatableResponse response;
}