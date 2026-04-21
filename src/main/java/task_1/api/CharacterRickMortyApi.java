package task_1.api;

import io.restassured.response.Response;
import static io.restassured.RestAssured.given;

public class CharacterRickMortyApi {
    private static final String BASE_URL = "https://rickandmortyapi.com/api";
    private static final String CHARACTER_PATH = "/character/";

    public Response getCharacterById(int id) {
        return given()
                .baseUri(BASE_URL)
                .when()
                .get(CHARACTER_PATH + id);
    }

    public Response getByUrl(String url) {
        return given()
                .when()
                .get(url);
    }
}