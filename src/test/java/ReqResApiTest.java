import io.restassured.response.ExtractableResponse;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.ifellow.model.reqres.UserMod;
import ru.ifellow.steps.ReqResSteps;

import java.io.IOException;

public class ReqResApiTest {
    ReqResSteps steps = new ReqResSteps();

    @Test
    public void createUserTest() throws IOException {
        ExtractableResponse userByChangingFileResponse = steps.createUserByChangingFile("Tomato", "Eat maket");
        UserMod userByChangingFile = userByChangingFileResponse.body().as(UserMod.class);

        Assertions.assertEquals(HttpStatus.SC_CREATED, userByChangingFileResponse.statusCode());
        Assertions.assertEquals("Tomato", userByChangingFile.getName());
        Assertions.assertEquals("Eat maket", userByChangingFile.getJob());
    }
}
