package ru.ifellow.task2.api;

import io.restassured.RestAssured;

import static ru.ifellow.task2.utils.Props.props;

public abstract class BaseApi {
    public BaseApi() {
        RestAssured.requestSpecification = Specification.baseRequestSpec(props.baseUrl());
        RestAssured.responseSpecification = Specification.baseResponseSpec();
    }
}
