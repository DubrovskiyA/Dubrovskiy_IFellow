package tests;

import com.codeborne.selenide.junit5.BrowserPerTestStrategyExtension;
import com.codeborne.selenide.junit5.SoftAssertsExtension;
import edujira.ifellow.pages.elements.Message;
import hooks.WebHooks;
import edujira.ifellow.pages.elements.enums.Priority;
import edujira.ifellow.pages.elements.enums.Severity;
import edujira.ifellow.pages.elements.enums.TypeOfNewTask;
import edujira.ifellow.pages.elements.enums.Version;
import edujira.ifellow.pages.elements.enums.HeaderItem;
import edujira.ifellow.pages.*;
import edujira.ifellow.pages.elements.enums.SideBarItems;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

@ExtendWith(BrowserPerTestStrategyExtension.class)
@ExtendWith(SoftAssertsExtension.class)
public class Tests extends WebHooks {
    private final SystemDashboardPage dashboardPage = new SystemDashboardPage();
    private final ProjectTasksPage projectTasksPage = new ProjectTasksPage();
    private final ProjectTasksSearchPage projectTasksSearchPage = new ProjectTasksSearchPage();
    private final CreateNewTaskDialogPage createNewTaskDialogPage = new CreateNewTaskDialogPage();
    private final Message message = new Message();


    @Test
    public void test1() {
        dashboardPage.setUserName();
        dashboardPage.setPassword();
        dashboardPage.submitLogIn();
        String userFromProfile = dashboardPage.getUserFromProfile();
        Assertions.assertEquals("AT10", userFromProfile);
    }

    @Test
    public void test2() {
        dashboardPage.setUserName();
        dashboardPage.setPassword();
        dashboardPage.submitLogIn();
        Assertions.assertEquals("AT10", dashboardPage.getUserFromProfile());
        dashboardPage.getHeader().openDropdownMenu(HeaderItem.PROJECTS);
        String project = "Test";
        dashboardPage.getHeader().selectProjectInDropdownMenu(project);
        String nameOfCurrentProject = projectTasksPage.getProjectTitle();
        Assertions.assertEquals(project, nameOfCurrentProject);
    }

    @Test
    public void test3() {
        dashboardPage.setUserName();
        dashboardPage.setPassword();
        dashboardPage.submitLogIn();
        Assertions.assertEquals("AT10", dashboardPage.getUserFromProfile());
        dashboardPage.getHeader().openDropdownMenu(HeaderItem.PROJECTS);
        dashboardPage.getHeader().selectProjectInDropdownMenu("Test");
        Assertions.assertEquals("Test", projectTasksPage.getProjectTitle());
        int countOfAllOpenedTasksBefore = projectTasksPage.getCountOfAllOpenedTasks();
        projectTasksPage.createNewTaskByWidget(TypeOfNewTask.BUG, "emptyTask");
        projectTasksPage.getSideBar().openProjectItemOnSidebar(SideBarItems.TASKS);
        int countOfAllOpenedTasksAfter = projectTasksPage.getCountOfAllOpenedTasks();
        Assertions.assertEquals(countOfAllOpenedTasksBefore + 1, countOfAllOpenedTasksAfter);
        projectTasksPage.sortListOfTaskByCreated();
        projectTasksPage.deleteOpenedTask();
    }

    @Test
    public void test4() {
        dashboardPage.setUserName();
        dashboardPage.setPassword();
        dashboardPage.submitLogIn();
        Assertions.assertEquals("AT10", dashboardPage.getUserFromProfile());
        dashboardPage.getHeader().openDropdownMenu(HeaderItem.PROJECTS);
        dashboardPage.getHeader().selectProjectInDropdownMenu("Test");
        Assertions.assertEquals("Test", projectTasksPage.getProjectTitle());
        projectTasksPage.getSideBar().openProjectItemOnSidebar(SideBarItems.TASKS);
        int countOfAllOpenedTasksBefore = projectTasksPage.getCountOfAllOpenedTasks();
        projectTasksPage.createNewTaskByWidget(TypeOfNewTask.BUG, "emptyTask");
        projectTasksPage.getSideBar().openProjectItemOnSidebar(SideBarItems.TASKS);
        int countOfAllOpenedTasksAfter = projectTasksPage.getCountOfAllOpenedTasks();
        Assertions.assertEquals(countOfAllOpenedTasksBefore + 1, countOfAllOpenedTasksAfter);
        projectTasksPage.sortListOfTaskByCreated();
        projectTasksPage.deleteOpenedTask();
        projectTasksPage.seeAllTasksAndFilters();
        projectTasksPage.searchTasks("TestSeleniumATHomework");
        projectTasksSearchPage.selectSearchedTask();
        projectTasksSearchPage.waitStatusToBe("СДЕЛАТЬ");
        String statusValue = projectTasksSearchPage.getCurrentTaskStatus();
        Assertions.assertEquals("СДЕЛАТЬ", statusValue);
        String fixVersionsField = projectTasksSearchPage.getFixVersion();
        Assertions.assertEquals("Version 2.0", fixVersionsField);

    }

    @Test
    public void test5() {
        dashboardPage.setUserName();
        dashboardPage.setPassword();
        dashboardPage.submitLogIn();
        Assertions.assertEquals("AT10", dashboardPage.getUserFromProfile());
        dashboardPage.getHeader().openDropdownMenu(HeaderItem.PROJECTS);
        dashboardPage.getHeader().selectProjectInDropdownMenu("Test");
        Assertions.assertEquals("Test", projectTasksPage.getProjectTitle());
        projectTasksPage.getSideBar().openProjectItemOnSidebar(SideBarItems.TASKS);
        int countOfAllOpenedTasksBefore = projectTasksPage.getCountOfAllOpenedTasks();
        projectTasksPage.createNewTaskByWidget(TypeOfNewTask.BUG, "emptyTask");
        projectTasksPage.getSideBar().openProjectItemOnSidebar(SideBarItems.TASKS);
        int countOfAllOpenedTasksAfter = projectTasksPage.getCountOfAllOpenedTasks();
        Assertions.assertEquals(countOfAllOpenedTasksBefore + 1, countOfAllOpenedTasksAfter);
        projectTasksPage.sortListOfTaskByCreated();
        projectTasksPage.deleteOpenedTask();
        projectTasksPage.seeAllTasksAndFilters();
        projectTasksPage.searchTasks("TestSeleniumATHomework");
        projectTasksSearchPage.selectSearchedTask();
        projectTasksSearchPage.waitStatusToBe("СДЕЛАТЬ");
        String statusValue = projectTasksSearchPage.getCurrentTaskStatus();
        Assertions.assertEquals("СДЕЛАТЬ", statusValue);
        String fixVersionsField = projectTasksSearchPage.getFixVersion();
        Assertions.assertEquals("Version 2.0", fixVersionsField);
        //        create new task
        projectTasksSearchPage.getHeader().createNewTaskByDialogWindow();
        createNewTaskDialogPage.setType(TypeOfNewTask.BUG);
        String topic = "Статьи на странице «Articles» не открываются";
        createNewTaskDialogPage.setTopic(topic);
        createNewTaskDialogPage.setDescription("""
                Шаги воспроизведения:\s

                Открыть страницу https://academybugs.com/articles/\s

                Нажать на любую из статей (на название статьи, картинку либо на ссылку «Read More»)\s

                Ожидаемый результат: Открывается новая страница с содержимым статьи\s

                Фактический результат: Открывается страница с ошибкой «404»""");
        createNewTaskDialogPage.setFixVersion(Version.VERSION2);
        createNewTaskDialogPage.setPriority(Priority.HIGH);
        createNewTaskDialogPage.setTags("QA_practice");
        createNewTaskDialogPage.setEnvironment("Windows 11; Google Chrome Версия 128");
        createNewTaskDialogPage.setVersionAffected(Version.VERSION1);
        createNewTaskDialogPage.setSeverity(Severity.MAJOR);
        createNewTaskDialogPage.submit();
        String statusSubmittedTaskFromMessage = message.getStatusSubmittedTaskFromMessage();
        Assertions.assertTrue(statusSubmittedTaskFromMessage.contains("успешно"));
        projectTasksSearchPage.searchTasks("Статьи");
        projectTasksSearchPage.selectSearchedTask();
        projectTasksSearchPage.waitOpenTask(topic);
        String currentOpenProjectTopic=projectTasksSearchPage.getCurrentOpenTaskTopic();
        Assertions.assertEquals(topic, currentOpenProjectTopic);
        //        move to inProgress
        projectTasksSearchPage.moveTaskToInProgressStatus();
        projectTasksSearchPage.waitStatusToBe("В РАБОТЕ");
        String statusValueInProgress = projectTasksSearchPage.getCurrentTaskStatus();
        Assertions.assertEquals("В РАБОТЕ", statusValueInProgress);
        //        move to done
        projectTasksSearchPage.moveTaskToDoneStatus();
        projectTasksSearchPage.waitStatusToBe("ГОТОВО");
        String statusValueInDone = projectTasksSearchPage.getCurrentTaskStatus();
        Assertions.assertEquals("ГОТОВО", statusValueInDone);
        //        delete task           должно быть в hooks!
        projectTasksPage.deleteOpenedTask();
    }
}
