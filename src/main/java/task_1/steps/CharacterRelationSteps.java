package task_1.steps;

import io.qameta.allure.Step;
import io.restassured.response.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import task_1.api.CharacterApi;
import task_1.dto.Character;

public class CharacterRelationSteps {

    private static final Logger log =
            LoggerFactory.getLogger(CharacterRelationSteps.class);

    private final CharacterApi api;

    public CharacterRelationSteps(CharacterApi api) {
        this.api = api;
    }

    @Step("Получить персонажа по URL: {url}")
    public Character getCharacter(String url) {

        Response response = api.get(url);

        log.info("Response status: {}", response.getStatusCode());
        log.debug("Response body:\n{}", response.getBody().asPrettyString());

        return response.as(Character.class);
    }
}