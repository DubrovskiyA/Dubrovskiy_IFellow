package ru.ifellow.task2.api;

import io.restassured.response.ValidatableResponse;
import ru.ifellow.task2.dto.UserDTO;

import static io.restassured.RestAssured.given;
import static ru.ifellow.task2.utils.Props.props;

public class ReqResApi extends BaseApi {
    private final String CREATE_USER_PATH = props.createUserPath();

    public ValidatableResponse postUser(UserDTO body) {
        return given()
                .when()
                .basePath(CREATE_USER_PATH)
                .body(body)
                .post()
                .then();
    }


}
