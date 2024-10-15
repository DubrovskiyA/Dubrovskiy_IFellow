package ru.ifellow.api.reqres;

import io.restassured.response.ValidatableResponse;
import ru.ifellow.model.reqres.UserMod;

import static io.restassured.RestAssured.given;
import static ru.ifellow.utils.Props.props;

public class ReqResApi extends BaseApi {
    private final String CREATE_USER_PATH = props.reqResCreateUserPath();

    public ValidatableResponse postUser(UserMod body) {
        return given()
                .when()
                .basePath(CREATE_USER_PATH)
                .body(body)
                .post()
                .then();
    }


}
