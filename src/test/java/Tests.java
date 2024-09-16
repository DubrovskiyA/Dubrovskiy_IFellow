import com.codeborne.selenide.junit5.BrowserPerTestStrategyExtension;
import config.WebHooks;
import edujira.ifellow.pages.*;
import edujira.ifellow.pages.header.HeaderItem;
import edujira.ifellow.pages.sidebar.SideBarItems;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

@ExtendWith(BrowserPerTestStrategyExtension.class)
public class Tests extends WebHooks {
    private final SystemDashboardPage dashboardPage = new SystemDashboardPage();
    private final ProjectMainPage projectMainPage = new ProjectMainPage();
    private final ProjectTasksPage projectTasksPage = new ProjectTasksPage();
    private final ProjectListOfTaskPage projectListOfTaskPage = new ProjectListOfTaskPage();

    @Test
    public void test1() {
        dashboardPage.setUsername("AT10");
        dashboardPage.setPassword("Qwerty123");
        dashboardPage.submit();
        String userFromProfile = dashboardPage.getUserFromProfile();
        Assertions.assertEquals("AT10", userFromProfile);
    }

    @Test
    public void test2() throws InterruptedException {
        dashboardPage.setUsername("AT10");
        dashboardPage.setPassword("Qwerty123");
        dashboardPage.submit();
        dashboardPage.openDropdownMenu(HeaderItem.PROJECTS);
        String project = "Test";
        dashboardPage.selectProjectInDropdownMenu(project);
        String nameOfCurrentProject = projectMainPage.getProjectTitle();
        Assertions.assertEquals(project, nameOfCurrentProject);
    }

    @Test
    public void test3() throws InterruptedException {
        dashboardPage.setUsername("AT10");
        dashboardPage.setPassword("Qwerty123");
        dashboardPage.submit();
        dashboardPage.openDropdownMenu(HeaderItem.PROJECTS);
        dashboardPage.selectProjectInDropdownMenu("Test");
        int countOfAllOpenedTasksBefore = projectTasksPage.getCountOfAllOpenedTasks();
        projectTasksPage.createNewTaskByWidget(TypeOfNewTask.BUG, "emptyTask");
        projectTasksPage.openProjectItemOnSidebar(SideBarItems.TASKS);
        int countOfAllOpenedTasksAfter = projectTasksPage.getCountOfAllOpenedTasks();
        Assertions.assertEquals(countOfAllOpenedTasksBefore + 1, countOfAllOpenedTasksAfter);
        projectTasksPage.sortListOfTaskByCreated();
        projectTasksPage.deleteTask();
    }

    @Test
    public void test4() {
        dashboardPage.setUsername("AT10");
        dashboardPage.setPassword("Qwerty123");
        dashboardPage.submit();
        dashboardPage.openDropdownMenu(HeaderItem.PROJECTS);
        dashboardPage.selectProjectInDropdownMenu("Test");
        projectMainPage.openProjectItemOnSidebar(SideBarItems.TASKS);
        int countOfAllOpenedTasksBefore = projectTasksPage.getCountOfAllOpenedTasks();
        projectTasksPage.createNewTaskByWidget(TypeOfNewTask.BUG, "emptyTask");
        projectTasksPage.openProjectItemOnSidebar(SideBarItems.TASKS);
        int countOfAllOpenedTasksAfter = projectTasksPage.getCountOfAllOpenedTasks();
        Assertions.assertEquals(countOfAllOpenedTasksBefore + 1, countOfAllOpenedTasksAfter);
        projectTasksPage.sortListOfTaskByCreated();
        projectTasksPage.deleteTask();
        projectTasksPage.openProjectItemOnSidebar(SideBarItems.LIST_OF_TASKS);
        projectListOfTaskPage.searchTask("TestSeleniumATHomework");
        String statusValue = projectListOfTaskPage.getStatusValue();
        Assertions.assertEquals("Сделать", statusValue);
        String fixVersionsField = projectListOfTaskPage.getFixVersionsField().trim();
        Assertions.assertEquals("Version 2.0", fixVersionsField);
    }
}
