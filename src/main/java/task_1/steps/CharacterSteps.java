package task_1.steps;

import task_1.api.CharacterRickMortyApi;
import task_1.dto.Character;
import io.restassured.response.Response;

public class CharacterSteps {
    private static final int MORTY_ID = 2;
    private final CharacterRickMortyApi api = new CharacterRickMortyApi();

    public Character getMorty() {
        Response response = api.getCharacterById(MORTY_ID);
        return response.as(Character.class);
    }

    public Character getCharacterByUrl(String url) {
        Response response = api.getByUrl(url);
        return response.as(Character.class);
    }

    public String getLastEpisodeUrl(Character character) {
        int lastIndex = character.episode.size() - 1;
        return character.episode.get(lastIndex);
    }
}