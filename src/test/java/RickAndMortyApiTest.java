import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.ifellow.model.rickandmorty.CharacterMod;
import ru.ifellow.steps.RickAndMortySteps;

import java.util.Optional;


public class RickAndMortyApiTest {
    private RickAndMortySteps steps = new RickAndMortySteps();

    @Test
    public void mortySmithTest() {
        Optional<Integer> mortySmithLastEpisodeId = steps.getCharacterLastEpisodeIdByCharacterName("Morty Smith");
        Optional<Integer> lastCharacterIdOfMortySmithLastEpisode =
                steps.getLastCharacterIdOfEpisodeByEpisodeId(mortySmithLastEpisodeId.get());
        CharacterMod lastCharacter = steps.getCharacterById(lastCharacterIdOfMortySmithLastEpisode.get());
        CharacterMod mortySmith = steps.getCharacterByName("Morty Smith");

        Assertions.assertEquals(mortySmith.getSpecies(), lastCharacter.getSpecies());
        Assertions.assertNotEquals(mortySmith.getLocation().getName(), lastCharacter.getLocation().getName());
    }
}
