package task_1.steps;

import task_1.api.CharacterRickMortyApi;
import task_1.dto.Episode;
import io.restassured.response.Response;

public class EpisodeSteps {

    private final CharacterRickMortyApi api = new CharacterRickMortyApi();

    public Episode getEpisodeByUrl(String url) {
        Response response = api.getByUrl(url);
        return response.as(Episode.class);
    }

    public String getLastCharacterUrl(Episode episode) {
        int lastIndex = episode.characters.size() - 1;
        return episode.characters.get(lastIndex);
    }
}