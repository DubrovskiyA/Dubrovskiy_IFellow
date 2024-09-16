package config;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.WebDriverRunner;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.PageLoadStrategy;

public class WebHooks {
    @BeforeEach
    public void setUp() {
        Configuration.savePageSource = false;
        Configuration.screenshots = false;
        Configuration.pageLoadStrategy = PageLoadStrategy.NONE.toString();
        Selenide.open("https://edujira.ifellow.ru");
        WebDriverRunner.getWebDriver().manage().window().maximize();
    }
}
