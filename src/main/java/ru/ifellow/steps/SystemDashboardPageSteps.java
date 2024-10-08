package ru.ifellow.steps;

import ru.ifellow.pages.SystemDashboardPage;
import ru.ifellow.pages.elements.enums.HeaderItem;

public class SystemDashboardPageSteps {
    private SystemDashboardPage systemDashboardPage;

    public SystemDashboardPageSteps() {
        systemDashboardPage = new SystemDashboardPage();
    }

    public void logIn() {
        systemDashboardPage.setUserName();
        systemDashboardPage.setPassword();
        systemDashboardPage.submitLogIn();
    }

    public String getAuthorizedUserName() {
        return systemDashboardPage.getUserFromProfile();
    }

    public void openProject(String projectName) {
        systemDashboardPage.getHeader().openDropdownMenu(HeaderItem.PROJECTS);
        systemDashboardPage.getHeader().selectProjectInDropdownMenu(projectName);
    }
}
