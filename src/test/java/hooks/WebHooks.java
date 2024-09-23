package hooks;

import com.codeborne.selenide.AssertionMode;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import edujira.ifellow.props.PropertyProvider;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.chrome.ChromeOptions;


public class WebHooks {
    private final String URL= PropertyProvider.getInstance().getProps().getProperty("test.url");
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
        Selenide.open(URL);
    }
}
