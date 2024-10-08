package ru.ifellow.pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import ru.ifellow.pages.elements.Header;
import ru.ifellow.props.Props;

import static com.codeborne.selenide.Selenide.$x;

public class SystemDashboardPage {
    Header header;
    private final SelenideElement USER_NAME = $x("//input[@name='os_username']")
            .as("Поле ввода имени пользователя");
    private final SelenideElement PASSWORD = $x("//input[@name='os_password']")
            .as("Поле ввода пароля");
    private final SelenideElement SUBMIT_BUTTON = $x("//input[@value='Войти']")
            .as("Кнопка \"Войти\"");
    private final SelenideElement USER_OPTIONS = $x("//li[@id='user-options']")
            .as("Меню профиля пользователя");
    private final SelenideElement USER_OPTIONS_PROFILE =
            $x("//div[@id='user-options-content']//a[@id='view_profile']")
                    .as("Профиль пользователя");
    private final SelenideElement USER_TITLE_NAME = $x("//span[@id='up-user-title-name']")
            .as("Имя пользователя на странице профиля пользователя");

    private final String LOGIN = Props.props.userName();
    private final String PASS = Props.props.pass();

    public SystemDashboardPage() {
        header = new Header();
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
