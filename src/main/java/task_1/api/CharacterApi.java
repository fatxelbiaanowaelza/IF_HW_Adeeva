package task_1.api;

import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class CharacterApi {

    private static final String BASE_URL = "https://rickandmortyapi.com/api";
    private static final String CHARACTER_PATH = "/character/";

    public Response searchCharacter(String name) {
        return given()
                .baseUri(BASE_URL)
                .queryParam("name", name)
                .when()
                .get(CHARACTER_PATH);
    }

    public Response get(String url) {
        return given()
                .when()
                .get(url);
    }
}