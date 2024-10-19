package ru.ifellow.steps;


import io.cucumber.java.ru.Дано;
import io.restassured.response.ExtractableResponse;
import org.junit.jupiter.api.Assertions;
import ru.ifellow.api.reqres.ReqResApi;
import ru.ifellow.model.reqres.UserMod;
import ru.ifellow.utils.Mapper;

import java.io.File;
import java.io.IOException;


public class ReqResSteps {
    private ReqResApi reqResApi = new ReqResApi();
    Mapper mapper = new Mapper();
    ExtractableResponse userByChangingFileResponse;
    UserMod userByChangingFile;

    @Дано("пользователь отправляет post запрос на создание пользователя с именем {string} и работой {string}")
    public void createUserByChangingFile(String name, String job) throws IOException {
        UserMod userFromJson = mapper
                .getMapper()
                .readValue(new File("src/test/resources/reqresapi/userToCreate.Json"), UserMod.class);
        userFromJson.setName(name);
        userFromJson.setJob(job);
        userByChangingFileResponse = reqResApi.postUser(userFromJson).extract();
        userByChangingFile = userByChangingFileResponse.body().as(UserMod.class);
    }

    @Дано("ответ имеет статус-код: {int}")
    public void checkStatus(int statusCod) {
        Assertions.assertEquals(statusCod, userByChangingFileResponse.statusCode());
    }

    @Дано("имя созданного пользователя: {string}")
    public void checkName(String name) {
        Assertions.assertEquals(name, userByChangingFile.getName());
    }

    @Дано("работа созданного пользователя: {string}")
    public void checkJob(String job) {
        Assertions.assertEquals(job, userByChangingFile.getJob());
    }
}
