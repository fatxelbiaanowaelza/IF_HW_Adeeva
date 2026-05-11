package cucumber;

import io.cucumber.java.ru.Дано;
import io.cucumber.java.ru.Когда;
import io.cucumber.java.ru.Тогда;
import io.qameta.allure.Step;
import task_1.api.CharacterApi;
import task_1.context.RickMortyContext;
import task_1.dto.Character;
import task_1.dto.CharacterResponse;
import task_1.steps.CharacterRelationSteps;
import task_1.steps.EpisodeSteps;

import static org.junit.jupiter.api.Assertions.*;
import static task_1.context.RickMortyCredentials.*;

public class RickMortyStepDefinitions {

    private final RickMortyContext context = new RickMortyContext();
    private final CharacterApi api = new CharacterApi();
    private final EpisodeSteps episodeSteps = new EpisodeSteps(api);
    private final CharacterRelationSteps relationSteps = new CharacterRelationSteps(api);

    @Дано("я ищу персонажа по имени {string}")
    @Step("Поиск персонажа по имени: {0}")
    public void iSearchForCharacterByName(String name) {

        context.response = api.searchCharacter(name);
        context.statusCode = context.response.getStatusCode();
    }

    @Тогда("статус код рикморти ответа должен быть {int}")
    @Step("Проверка статус кода Rick & Morty API: ожидается {0}")
    public void statusCodeShouldBe(int expectedStatus) {

        assertEquals(expectedStatus, context.statusCode, INVALID_STATUS_CODE_MESSAGE);
    }

    @Тогда("в ответе рикморти есть персонаж с именем {string}")
    @Step("Проверка, что в ответе есть персонаж с именем: {0}")
    public void responseContainsCharacter(String expectedName) {

        CharacterResponse result = context.response.as(CharacterResponse.class);

        assertNotNull(result.getResults(), RESULTS_NULL_MESSAGE);
        assertFalse(result.getResults().isEmpty(), RESULTS_EMPTY_MESSAGE);

        context.morty = result.getResults().getFirst();

        assertEquals(expectedName, context.morty.name, NAME_MISMATCH_MESSAGE);
    }

    @Тогда("я запоминаю последний эпизод из списка эпизодов Морти")
    @Step("Извлечение последнего эпизода из списка эпизодов Морти")
    public void rememberLastEpisode() {

        assertNotNull(context.morty.episode, EPISODE_NULL_MESSAGE);
        assertFalse(context.morty.episode.isEmpty(), EPISODE_EMPTY_MESSAGE);

        context.lastEpisodeUrl =
                context.morty.episode.getLast();
    }

    @Когда("я получаю информацию о последнем эпизоде")
    @Step("Получение информации о последнем эпизоде по URL")
    public void getLastEpisode() {

        context.lastEpisode =
                episodeSteps.getEpisode(context.lastEpisodeUrl);

        assertNotNull(context.lastEpisode, LAST_EPISODE_NULL_MESSAGE);
    }

    @Тогда("я запоминаю последнего персонажа из этого эпизода")
    @Step("Извлечение последнего персонажа из эпизода")
    public void rememberLastCharacter() {

        assertNotNull(context.lastEpisode.characters, CHARACTERS_NULL_MESSAGE);
        assertFalse(context.lastEpisode.characters.isEmpty(), CHARACTERS_EMPTY_MESSAGE);

        context.lastCharacterUrl =
                context.lastEpisode.characters.getLast(
                );
    }

    @Когда("я получаю информацию о последнем персонаже")
    @Step("Получение информации о последнем персонаже")
    public void getLastCharacter() {

        context.lastCharacter =
                relationSteps.getCharacter(context.lastCharacterUrl);

        assertNotNull(context.lastCharacter, CHARACTER_NULL_MESSAGE);
    }

    @Тогда("проверяю, что последний персонаж не совпадает с Морти по расе и локации")
    @Step("Проверка, что персонаж отличается от Морти")
    public void checkMismatch() {

        boolean sameSpeciesAndLocation =
                context.lastCharacter.species.equals(context.morty.species)
                        && context.lastCharacter.location.name
                        .equals(context.morty.location.name);

        assertFalse(sameSpeciesAndLocation, CHARACTER_MATCH_MESSAGE);
    }

    @Тогда("у персонажа рикморти заполнены все обязательные поля")
    @Step("Проверка заполнения обязательных полей")
    public void checkFields() {

        CharacterResponse result = context.response.as(CharacterResponse.class);
        Character character = result.getResults().getFirst();

        assertNotEquals(0, character.id, ID_ZERO_MESSAGE);

        assertNotNull(character.name);
        assertFalse(character.name.isEmpty());

        assertNotNull(character.status);
        assertFalse(character.status.isEmpty());

        assertNotNull(character.species);
        assertFalse(character.species.isEmpty());

        assertNotNull(character.gender);
        assertFalse(character.gender.isEmpty());

        assertNotNull(character.origin);
        assertNotNull(character.location);

        assertNotNull(character.episode);
        assertFalse(character.episode.isEmpty());
    }
}