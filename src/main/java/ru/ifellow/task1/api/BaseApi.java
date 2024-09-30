package ru.ifellow.task1.api;

import io.restassured.RestAssured;

import static ru.ifellow.task1.utils.Props.props;


public abstract class BaseApi {
    public BaseApi() {
        RestAssured.requestSpecification = Specification.baseRequestSpec(props.baseUrl());
        RestAssured.responseSpecification = Specification.baseResponseSpec();
    }
}
