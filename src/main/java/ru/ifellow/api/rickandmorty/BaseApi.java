package ru.ifellow.api.rickandmorty;

import io.restassured.RestAssured;
import ru.ifellow.utils.Props;


public abstract class BaseApi {
    public BaseApi() {
        RestAssured.requestSpecification = Specification.baseRequestSpec(Props.props.rickAndMortyBaseUrl());
        RestAssured.responseSpecification = Specification.baseResponseSpec();
    }
}
