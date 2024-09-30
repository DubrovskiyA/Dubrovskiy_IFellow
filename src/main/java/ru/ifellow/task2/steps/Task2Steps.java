package ru.ifellow.task2.steps;


import io.restassured.response.ExtractableResponse;
import ru.ifellow.task2.api.ReqResApi;
import ru.ifellow.task2.dto.UserDTO;
import ru.ifellow.task2.utils.Mapper;

import java.io.File;
import java.io.IOException;


public class Task2Steps {
    private ReqResApi reqResApi = new ReqResApi();
    Mapper mapper = new Mapper();

    public ExtractableResponse createUserByChangingFile(String name, String job) throws IOException {
        UserDTO userFromJson = mapper
                .getMapper()
                .readValue(new File("src/test/resources/task2/userToCreate.Json"), UserDTO.class);
        userFromJson.setName(name);
        userFromJson.setJob(job);
        return reqResApi.postUser(userFromJson)
                .extract();
    }
}
