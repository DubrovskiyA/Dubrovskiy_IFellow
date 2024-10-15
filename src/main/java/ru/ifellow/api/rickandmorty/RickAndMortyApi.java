package ru.ifellow.api.rickandmorty;

import io.restassured.response.ValidatableResponse;
import ru.ifellow.utils.Props;

import static io.restassured.RestAssured.given;

public class RickAndMortyApi extends BaseApi {
    private final String CHARACTER_PATH = Props.props.rickAndMortyCharacterPath();
    private final String EPISODE_PATH = Props.props.rickAndMortyEpisodePath();

    public ValidatableResponse getCharacterByName(String name) {
        return given()
                .when()
                .basePath(CHARACTER_PATH)
                .queryParam("name", name)
                .get()
                .then();
    }

    public ValidatableResponse getEpisodeById(int id) {
        return given()
                .when()
                .basePath(EPISODE_PATH + "/" + id)
                .get()
                .then();
    }

    public ValidatableResponse getCharacterById(int id) {
        return given()
                .when()
                .basePath(CHARACTER_PATH + "/" + id)
                .get()
                .then();
    }
}
