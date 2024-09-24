package edujira.ifellow.pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import edujira.ifellow.props.PropertyProvider;
import edujira.ifellow.pages.elements.Header;

import static com.codeborne.selenide.Selenide.$x;

public class SystemDashboardPage {
    Header header;
    private final SelenideElement USER_NAME = $x("//input[@name='os_username']");
    private final SelenideElement PASSWORD = $x("//input[@name='os_password']");
    private final SelenideElement SUBMIT_BUTTON = $x("//input[@value='Войти']");
    private final SelenideElement USER_OPTIONS = $x("//li[@id='user-options']");
    private final SelenideElement USER_OPTIONS_PROFILE =
            $x("//div[@id='user-options-content']//a[@id='view_profile']");
    private final SelenideElement USER_TITLE_NAME = $x("//span[@id='up-user-title-name']");
    private final String URL = PropertyProvider.getInstance().getProps().getProperty("test.url");
    private final String LOGIN = PropertyProvider.getInstance().getProps().getProperty("test.user");
    private final String PASS = PropertyProvider.getInstance().getProps().getProperty("test.pass");

    public SystemDashboardPage() {
        header = new Header();
    }

    public void openMainPage() {
        Selenide.open(URL);
    }

    public void setUserName() {
        USER_NAME.val(LOGIN);
    }

    public void setPassword() {
        PASSWORD.val(PASS);
    }

    public void submitLogIn() {
        SUBMIT_BUTTON.click();
    }

    public String getUserFromProfile() {
        USER_OPTIONS.shouldNotBe(Condition.text("Вход")).click();
        USER_OPTIONS_PROFILE.click();
        return USER_TITLE_NAME.getText();
    }

    public Header getHeader() {
        return header;
    }
}
