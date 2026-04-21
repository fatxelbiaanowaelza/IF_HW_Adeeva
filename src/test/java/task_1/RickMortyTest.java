package task_1;

import task_1.dto.Character;
import task_1.dto.Episode;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import task_1.steps.CharacterSteps;
import task_1.steps.EpisodeSteps;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class RickMortyTest {

    private final CharacterSteps characterSteps = new CharacterSteps();
    private final EpisodeSteps episodeSteps = new EpisodeSteps();

    private static final String MORTY_NAME_EXPECTED = "Morty Smith";
    private static final String SPECIES_HUMAN = "Human";
    private static final String LOCATION_EARTH_UNKNOWN = "Earth (Unknown dimension)";

    @Test
    @DisplayName("Проверка последнего эпизода и последнего персонажа из него для Морти Смит")
    void mortyFlowTest() {
        Character morty = characterSteps.getMorty();

        assertNotNull(morty, "Морти не должен быть null");
        assertNotNull(morty.name, "Имя Морти не должно быть null");
        assertEquals(MORTY_NAME_EXPECTED, morty.name, "Имя персонажа должно совпадать");

        assertNotNull(morty.species, "Раса Морти не должна быть null");
        assertEquals(SPECIES_HUMAN, morty.species, "Раса Морти должна быть Human");

        assertNotNull(morty.location, "Локация Морти не должна быть null");
        assertNotNull(morty.location.name, "Название локации Морти не должно быть null");

        String lastEpisodeUrl = characterSteps.getLastEpisodeUrl(morty);
        assertNotNull(lastEpisodeUrl, "URL последнего эпизода не должен быть null");

        Episode episode = episodeSteps.getEpisodeByUrl(lastEpisodeUrl);
        assertNotNull(episode, "Эпизод не должен быть null");

        String lastCharacterUrl = episodeSteps.getLastCharacterUrl(episode);
        assertNotNull(lastCharacterUrl, "URL последнего персонажа не должен быть null");

        Character lastCharacter = characterSteps.getCharacterByUrl(lastCharacterUrl);

        assertNotNull(lastCharacter, "Последний персонаж не должен быть null");
        assertNotNull(lastCharacter.name, "Имя персонажа не должно быть null");
        assertNotNull(lastCharacter.species, "Раса персонажа не должна быть null");
        assertNotNull(lastCharacter.location, "Локация персонажа не должна быть null");
        assertNotNull(lastCharacter.location.name, "Название локации персонажа не должно быть null");

        assertEquals(SPECIES_HUMAN, lastCharacter.species, "Раса должна совпадать (Human)");

        assertEquals(LOCATION_EARTH_UNKNOWN, lastCharacter.location.name,
                "Локация последнего персонажа должна совпадать с ожидаемой");

        boolean sameSpecies = morty.species.equals(lastCharacter.species);
        boolean sameLocation = morty.location.name.equals(lastCharacter.location.name);

        assertEquals(true, sameSpecies, "Проверка совпадения расы");
        assertEquals(false, sameLocation, "Локации должны отличаться от Морти");
    }
}