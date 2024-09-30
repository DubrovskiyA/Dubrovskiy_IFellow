package ru.ifellow.task1.api;

import io.restassured.response.ValidatableResponse;

import static io.restassured.RestAssured.given;
import static ru.ifellow.task1.utils.Props.props;

public class RickAndMortyApi extends BaseApi {
    private final String CHARACTER_PATH = props.characterPath();
    private final String EPISODE_PATH = props.episodePath();

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
