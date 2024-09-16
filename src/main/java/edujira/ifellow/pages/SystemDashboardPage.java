package edujira.ifellow.pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import edujira.ifellow.pages.header.Header;

import static com.codeborne.selenide.Selenide.$x;

public class SystemDashboardPage extends Header {
    private final SelenideElement username = $x("//input[@name='os_username']");
    private final SelenideElement password = $x("//input[@name='os_password']");
    private final SelenideElement submitButton = $x("//input[@value='Войти']");
    private final SelenideElement userOptions = $x("//li[@id='user-options']");
    private final SelenideElement userOptionsProfile =
            $x("//div[@id='user-options-content']//a[@id='view_profile']");
    private final SelenideElement userTitleName = $x("//span[@id='up-user-title-name']");

    public void setUsername(String username1) {
        username.val(username1);
    }

    public void setPassword(String password1) {
        password.val(password1);
    }

    public void submit() {
        submitButton.click();
    }

    public String getUserFromProfile() {
        userOptions.shouldNotBe(Condition.text("Вход")).click();
        userOptionsProfile.click();
        return userTitleName.getText();
    }
}
