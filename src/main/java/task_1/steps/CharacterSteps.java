package task_1.steps;

import io.qameta.allure.Step;
import io.restassured.response.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import task_1.api.CharacterApi;
import task_1.dto.CharacterResponse;

public class CharacterSteps {

    private static final Logger log =
            LoggerFactory.getLogger(CharacterSteps.class);

    private final CharacterApi api;

    public CharacterSteps(CharacterApi api) {
        this.api = api;
    }

    @Step("Поиск персонажа по имени: {name}")
    public CharacterResponse getCharacter(String name) {

        log.info("Searching character by name: {}", name);

        Response response = api.searchCharacter(name);

        log.info("Response status: {}", response.getStatusCode());
        log.debug("Response body:\n{}", response.getBody().asPrettyString());

        return response.as(CharacterResponse.class);
    }
}