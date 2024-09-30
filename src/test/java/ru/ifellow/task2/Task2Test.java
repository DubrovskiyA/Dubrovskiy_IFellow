package ru.ifellow.task2;

import io.restassured.response.ExtractableResponse;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.ifellow.task2.dto.UserDTO;
import ru.ifellow.task2.steps.Task2Steps;

import java.io.IOException;

public class Task2Test {
    Task2Steps steps = new Task2Steps();

    @Test
    public void task2Test() throws IOException {
        ExtractableResponse userByChangingFileResponse = steps.createUserByChangingFile("Tomato", "Eat maket");
        UserDTO userByChangingFile = userByChangingFileResponse.body().as(UserDTO.class);

        Assertions.assertEquals(HttpStatus.SC_CREATED, userByChangingFileResponse.statusCode());
        Assertions.assertEquals("Tomato", userByChangingFile.getName());
        Assertions.assertEquals("Eat maket", userByChangingFile.getJob());
    }
}
