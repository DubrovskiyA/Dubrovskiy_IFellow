package ru.ifellow.steps;


import org.apache.http.HttpStatus;
import ru.ifellow.model.rickandmorty.CharacterMod;
import ru.ifellow.api.rickandmorty.RickAndMortyApi;


import java.util.*;


public class RickAndMortySteps {
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

    public CharacterMod getCharacterById(int id) {
        return rickAndMortyApi.getCharacterById(id)
                .statusCode(HttpStatus.SC_OK)
                .extract()
                .body()
                .as(CharacterMod.class);
    }

    public CharacterMod getCharacterByName(String name) {
        return rickAndMortyApi.getCharacterByName(name)
                .statusCode(HttpStatus.SC_OK)
                .extract()
                .body()
                .jsonPath()
                .getObject("results[0]", CharacterMod.class);
    }
}
