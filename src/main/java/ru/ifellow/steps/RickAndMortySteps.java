package ru.ifellow.steps;


import io.cucumber.java.ru.Дано;
import io.restassured.response.ValidatableResponse;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.Assertions;
import ru.ifellow.api.rickandmorty.RickAndMortyApi;
import ru.ifellow.model.rickandmorty.CharacterMod;


public class RickAndMortySteps {
    private RickAndMortyApi rickAndMortyApi = new RickAndMortyApi();
    private ValidatableResponse response;
    private int lastEpisodeId;
    private int lastCharacterId;
    private String lastCharacterSpecies;
    private String lastCharacterLocation;
    private String mortySmithSpecies;
    private String mortySmithLocation;

    @Дано("пользователь отправляет get запрос с параметром name={string}")
    public void getCharacterByName(String name) {
        response = rickAndMortyApi.getCharacterByName(name);
    }

    @Дано("пользователь получает Id последнего эпизода где появлялся персонаж")
    public void getCharacterLastEpisodeIdByCharacterName() {
        lastEpisodeId = response
                .statusCode(HttpStatus.SC_OK)
                .extract()
                .body()
                .jsonPath().getList("results.episode[0]", String.class)
                .stream()
                .map(s -> s.replace("https://rickandmortyapi.com/api/episode/", ""))
                .map(Integer::parseInt)
                .max(Integer::compare)
                .get();
    }

    @Дано("пользователь отправляет get запрос на получение информации о эпизоде по Id")
    public void getEpisodeByEpisodeId() {
        response = rickAndMortyApi.getEpisodeById(lastEpisodeId);
    }

    @Дано("пользователь получает Id последнего персонажа эпизода")
    public void getLastCharacterIdOfEpisode() {
        lastCharacterId = response
                .statusCode(HttpStatus.SC_OK)
                .extract()
                .body()
                .jsonPath().getList("characters", String.class)
                .stream()
                .map(s -> s.replace("https://rickandmortyapi.com/api/character/", ""))
                .map(Integer::parseInt)
                .max(Integer::compare)
                .get();
    }

    @Дано("пользователь отправляет get запрос на получение информации о персонаже по Id")
    public void getCharacterById() {
        response = rickAndMortyApi.getCharacterById(lastCharacterId);
    }

    @Дано("пользователь получает информацию о местонахождении и расе персонажа")
    public void getCharacterSpeciesAndLocation() {
        CharacterMod lastCharacter = response
                .statusCode(HttpStatus.SC_OK)
                .extract()
                .body()
                .as(CharacterMod.class);
        lastCharacterSpecies = lastCharacter.getSpecies();
        lastCharacterLocation = lastCharacter.getLocation().getName();
    }


    @Дано("пользователь получает информацию о местонахождении и расе Морти Смита")
    public void getCharacterByNameSpeciesAndLocation() {
        CharacterMod mortySmith = response.statusCode(HttpStatus.SC_OK)
                .extract()
                .body()
                .jsonPath()
                .getObject("results[0]", CharacterMod.class);
        mortySmithSpecies = mortySmith.getSpecies();
        mortySmithLocation = mortySmith.getLocation().getName();
    }

    @Дано("последний персонаж и Морти Смит принадлежат к одной расе")
    public void checkSpeciesOfCharacters() {
        Assertions.assertEquals(mortySmithSpecies, lastCharacterSpecies);
    }

    @Дано("последний персонаж и Морти Смит имеют разное местонахождение")
    public void checkLocationOfCharacters() {
        Assertions.assertNotEquals(mortySmithLocation, lastCharacterLocation);
    }
}
