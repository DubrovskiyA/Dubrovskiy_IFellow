package ru.ifellow.task1.steps;


import org.apache.http.HttpStatus;
import ru.ifellow.task1.api.RickAndMortyApi;
import ru.ifellow.task1.dto.CharacterDTO;


import java.util.*;


public class Task1Steps {
    private RickAndMortyApi rickAndMortyApi = new RickAndMortyApi();

    public Optional<Integer> getCharacterLastEpisodeIdByCharacterName(String name) {
        return rickAndMortyApi.getCharacterByName(name)
                .statusCode(HttpStatus.SC_OK)
                .extract()
                .body()
                .jsonPath().getList("results.episode[0]", String.class)
                .stream()
                .map(s -> s.replace("https://rickandmortyapi.com/api/episode/", ""))
                .map(Integer::parseInt)
                .max(Integer::compare);
    }

    public Optional<Integer> getLastCharacterIdOfEpisodeByEpisodeId(int id) {
        return rickAndMortyApi.getEpisodeById(id)
                .statusCode(HttpStatus.SC_OK)
                .extract()
                .body()
                .jsonPath().getList("characters", String.class)
                .stream()
                .map(s -> s.replace("https://rickandmortyapi.com/api/character/", ""))
                .map(Integer::parseInt)
                .max(Integer::compare);
    }

    public CharacterDTO getCharacterById(int id) {
        return rickAndMortyApi.getCharacterById(id)
                .statusCode(HttpStatus.SC_OK)
                .extract()
                .body()
                .as(CharacterDTO.class);
    }

    public CharacterDTO getCharacterByName(String name) {
        return rickAndMortyApi.getCharacterByName(name)
                .statusCode(HttpStatus.SC_OK)
                .extract()
                .body()
                .jsonPath()
                .getObject("results[0]", CharacterDTO.class);
    }
}
