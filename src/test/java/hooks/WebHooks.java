package hooks;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.chrome.ChromeOptions;
import ru.ifellow.props.Props;

import static io.qameta.allure.Allure.step;


public class WebHooks {
    private final String URL = Props.props.baseUrl();

    @BeforeAll
    static void setupAllureReports() {
        SelenideLogger.addListener("AllureSelenide", new AllureSelenide()
                .screenshots(true)
                .savePageSource(true));
    }

    @BeforeEach
    public void setUp() {
        Configuration.browser = "chrome";
        Configuration.savePageSource = false;
        Configuration.screenshots = false;
        Configuration.timeout = 6000;
        ChromeOptions options = new ChromeOptions();
        options.addArguments("start-maximized");
        Configuration.browserCapabilities = options;
        Configuration.browserSize = null;
        step("Открытие главной страницы: " + URL, () -> Selenide.open(URL));
    }
}
