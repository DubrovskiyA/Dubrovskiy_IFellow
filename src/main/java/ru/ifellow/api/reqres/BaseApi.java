package ru.ifellow.api.reqres;

import io.restassured.RestAssured;

import static ru.ifellow.utils.Props.props;

public abstract class BaseApi {
    public BaseApi() {
        RestAssured.requestSpecification = Specification.baseRequestSpec(props.reqResBaseUrl());
        RestAssured.responseSpecification = Specification.baseResponseSpec();
    }
}
