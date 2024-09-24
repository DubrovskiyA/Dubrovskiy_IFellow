package edujira.ifellow.steps;

import edujira.ifellow.pages.SystemDashboardPage;
import edujira.ifellow.pages.elements.enums.HeaderItem;
import edujira.ifellow.props.PropertyProvider;
import io.cucumber.java.ru.Дано;
import org.junit.jupiter.api.Assertions;

public class SystemDashboardPageStepdefs {
    private SystemDashboardPage systemDashboardPage;
    private final String LOGIN = PropertyProvider.getInstance().getProps().getProperty("test.user");

    public SystemDashboardPageStepdefs() {
        systemDashboardPage = new SystemDashboardPage();
    }

    @Дано("пользователь заходит на главную страницу")
    public void open() {
        systemDashboardPage.openMainPage();
    }

    @Дано("пользователь вводит \"Имя пользователя\" и \"Пароль\" и нажимает кнопку \"Войти\"")
    public void logIn() {
        systemDashboardPage.setUserName();
        systemDashboardPage.setPassword();
        systemDashboardPage.submitLogIn();
    }

    @Дано("пользователь авторизован")
    public void checkAuthorizedUserName() {
        Assertions.assertEquals(LOGIN, systemDashboardPage.getUserFromProfile());
    }

    @Дано("пользователь переходит в проект {string}")
    public void openProject(String projectTitle) {
        systemDashboardPage.getHeader().openDropdownMenu(HeaderItem.PROJECTS);
        systemDashboardPage.getHeader().selectProjectInDropdownMenu(projectTitle);
    }
}
