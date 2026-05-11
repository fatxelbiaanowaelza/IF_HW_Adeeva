package task_1.context;

import io.restassured.response.Response;
import task_1.dto.Character;
import task_1.dto.Episode;

public class RickMortyContext {

    public Character morty;
    public Episode lastEpisode;
    public Character lastCharacter;
    public String lastEpisodeUrl;
    public String lastCharacterUrl;
    public Response response;
    public int statusCode;
}