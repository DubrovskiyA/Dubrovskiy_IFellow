package ru.ifellow.steps;

import io.qameta.allure.Step;
import ru.ifellow.pages.SystemDashboardPage;
import ru.ifellow.pages.elements.enums.HeaderItem;

public class SystemDashboardPageSteps {
    private SystemDashboardPage systemDashboardPage;

    public SystemDashboardPageSteps() {
        systemDashboardPage = new SystemDashboardPage();
    }

    @Step("Авторизация")
    public void logIn() {
        systemDashboardPage.setUserName();
        systemDashboardPage.setPassword();
        systemDashboardPage.submitLogIn();
    }

    @Step("Получение имени авторизованного пользователя")
    public String getAuthorizedUserName() {
        return systemDashboardPage.getUserFromProfile();
    }

    @Step("Открытие проекта \"{projectName}\"")
    public void openProject(String projectName) {
        systemDashboardPage.getHeader().openDropdownMenu(HeaderItem.PROJECTS);
        systemDashboardPage.getHeader().selectProjectInDropdownMenu(projectName);
    }
}
