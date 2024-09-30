package ru.ifellow.task1;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.ifellow.task1.dto.CharacterDTO;
import ru.ifellow.task1.steps.Task1Steps;

import java.util.Optional;


public class Task1Test {
    private Task1Steps steps = new Task1Steps();

    @Test
    public void task1Test() {
        Optional<Integer> mortySmithLastEpisodeId = steps.getCharacterLastEpisodeIdByCharacterName("Morty Smith");
        Optional<Integer> lastCharacterIdOfMortySmithLastEpisode =
                steps.getLastCharacterIdOfEpisodeByEpisodeId(mortySmithLastEpisodeId.get());
        CharacterDTO lastCharacter = steps.getCharacterById(lastCharacterIdOfMortySmithLastEpisode.get());
        CharacterDTO mortySmith = steps.getCharacterByName("Morty Smith");

        Assertions.assertEquals(mortySmith.getSpecies(), lastCharacter.getSpecies());
        Assertions.assertNotEquals(mortySmith.getLocation().getName(), lastCharacter.getLocation().getName());
    }
}
