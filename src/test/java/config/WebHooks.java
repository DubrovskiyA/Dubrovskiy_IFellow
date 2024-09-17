package config;

import com.codeborne.selenide.AssertionMode;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.WebDriverRunner;
import org.junit.jupiter.api.BeforeEach;


public class WebHooks {
    @BeforeEach
    public void setUp() {
        Configuration.savePageSource = false;
        Configuration.screenshots = false;
        Configuration.timeout = 6000;
        Configuration.assertionMode = AssertionMode.SOFT;
        Selenide.open("https://edujira.ifellow.ru");
        WebDriverRunner.getWebDriver().manage().window().maximize();
    }
}
