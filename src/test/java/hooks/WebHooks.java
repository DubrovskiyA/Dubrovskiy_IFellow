package hooks;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import org.openqa.selenium.chrome.ChromeOptions;


public class WebHooks {
    @Before
    public void setUp() {
        Configuration.browser = "chrome";
        Configuration.savePageSource = false;
        Configuration.screenshots = false;
        ChromeOptions options = new ChromeOptions();
        options.addArguments("start-maximized");
        Configuration.browserCapabilities = options;
        Configuration.browserSize = null;
        Configuration.timeout = 8000;
    }
    @After
    public void tearDown(){
        Selenide.closeWebDriver();
    }
}
