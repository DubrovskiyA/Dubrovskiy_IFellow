package ru.ifellow.steps;


import io.restassured.response.ExtractableResponse;
import ru.ifellow.model.reqres.UserMod;
import ru.ifellow.api.reqres.ReqResApi;
import ru.ifellow.utils.Mapper;

import java.io.File;
import java.io.IOException;


public class ReqResSteps {
    private ReqResApi reqResApi = new ReqResApi();
    Mapper mapper = new Mapper();

    public ExtractableResponse createUserByChangingFile(String name, String job) throws IOException {
        UserMod userFromJson = mapper
                .getMapper()
                .readValue(new File("src/test/resources/reqresapi/userToCreate.Json"), UserMod.class);
        userFromJson.setName(name);
        userFromJson.setJob(job);
        return reqResApi.postUser(userFromJson)
                .extract();
    }
}
