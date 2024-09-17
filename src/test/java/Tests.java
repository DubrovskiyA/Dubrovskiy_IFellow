import com.codeborne.selenide.junit5.BrowserPerTestStrategyExtension;
import com.codeborne.selenide.junit5.SoftAssertsExtension;
import config.WebHooks;
import edujira.ifellow.pages.*;
import edujira.ifellow.pages.header.HeaderItem;
import edujira.ifellow.pages.sidebar.SideBarItems;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

@ExtendWith(BrowserPerTestStrategyExtension.class)
@ExtendWith(SoftAssertsExtension.class)
public class Tests extends WebHooks {
    private final SystemDashboardPage dashboardPage = new SystemDashboardPage();
    private final ProjectMainPage projectMainPage = new ProjectMainPage();
    private final ProjectTasksPage projectTasksPage = new ProjectTasksPage();
    private final ProjectTasksSearchPage projectTasksSearchPage = new ProjectTasksSearchPage();
    private final CreateNewTaskDialogPage createNewTaskDialogPage = new CreateNewTaskDialogPage();
    private final Message message = new Message();


    @Test
    public void test1() {
        dashboardPage.setUsername("AT10");
        dashboardPage.setPassword("Qwerty123");
        dashboardPage.submit();
        String userFromProfile = dashboardPage.getUserFromProfile();
        Assertions.assertEquals("AT10", userFromProfile);
    }

    @Test
    public void test2() {
        dashboardPage.setUsername("AT10");
        dashboardPage.setPassword("Qwerty123");
        dashboardPage.submit();
        Assertions.assertEquals("AT10", dashboardPage.getUserFromProfile());
        dashboardPage.getHeader().openDropdownMenu(HeaderItem.PROJECTS);
        String project = "Test";
        dashboardPage.getHeader().selectProjectInDropdownMenu(project);
        String nameOfCurrentProject = projectMainPage.getProjectTitle();
        Assertions.assertEquals(project, nameOfCurrentProject);
    }

    @Test
    public void test3() {
        dashboardPage.setUsername("AT10");
        dashboardPage.setPassword("Qwerty123");
        dashboardPage.submit();
        Assertions.assertEquals("AT10", dashboardPage.getUserFromProfile());
        dashboardPage.getHeader().openDropdownMenu(HeaderItem.PROJECTS);
        dashboardPage.getHeader().selectProjectInDropdownMenu("Test");
        Assertions.assertEquals("Test", projectMainPage.getProjectTitle());
        int countOfAllOpenedTasksBefore = projectTasksPage.getCountOfAllOpenedTasks();
        projectTasksPage.createNewTaskByWidget(TypeOfNewTask.BUG, "emptyTask");
        projectTasksPage.getSideBar().openProjectItemOnSidebar(SideBarItems.TASKS);
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
        Assertions.assertEquals("AT10", dashboardPage.getUserFromProfile());
        dashboardPage.getHeader().openDropdownMenu(HeaderItem.PROJECTS);
        dashboardPage.getHeader().selectProjectInDropdownMenu("Test");
        Assertions.assertEquals("Test", projectMainPage.getProjectTitle());
        projectMainPage.getSideBar().openProjectItemOnSidebar(SideBarItems.TASKS);
        int countOfAllOpenedTasksBefore = projectTasksPage.getCountOfAllOpenedTasks();
        projectTasksPage.createNewTaskByWidget(TypeOfNewTask.BUG, "emptyTask");
        projectTasksPage.getSideBar().openProjectItemOnSidebar(SideBarItems.TASKS);
        int countOfAllOpenedTasksAfter = projectTasksPage.getCountOfAllOpenedTasks();
        Assertions.assertEquals(countOfAllOpenedTasksBefore + 1, countOfAllOpenedTasksAfter);
        projectTasksPage.sortListOfTaskByCreated();
        projectTasksPage.deleteTask();
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
        dashboardPage.setUsername("AT10");
        dashboardPage.setPassword("Qwerty123");
        dashboardPage.submit();
        Assertions.assertEquals("AT10", dashboardPage.getUserFromProfile());
        dashboardPage.getHeader().openDropdownMenu(HeaderItem.PROJECTS);
        dashboardPage.getHeader().selectProjectInDropdownMenu("Test");
        Assertions.assertEquals("Test", projectMainPage.getProjectTitle());
        projectMainPage.getSideBar().openProjectItemOnSidebar(SideBarItems.TASKS);
        int countOfAllOpenedTasksBefore = projectTasksPage.getCountOfAllOpenedTasks();
        projectTasksPage.createNewTaskByWidget(TypeOfNewTask.BUG, "emptyTask");
        projectTasksPage.getSideBar().openProjectItemOnSidebar(SideBarItems.TASKS);
        int countOfAllOpenedTasksAfter = projectTasksPage.getCountOfAllOpenedTasks();
        Assertions.assertEquals(countOfAllOpenedTasksBefore + 1, countOfAllOpenedTasksAfter);
        projectTasksPage.sortListOfTaskByCreated();
        projectTasksPage.deleteTask();
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
        createNewTaskDialogPage.setDescription("Шаги воспроизведения: \n" +
                "\n" +
                "Открыть страницу https://academybugs.com/articles/ \n" +
                "\n" +
                "Нажать на любую из статей (на название статьи, картинку либо на ссылку «Read More») \n" +
                "\n" +
                "Ожидаемый результат: Открывается новая страница с содержимым статьи \n" +
                "\n" +
                "Фактический результат: Открывается страница с ошибкой «404»");
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
        //        delete task
        projectTasksPage.deleteTask();
    }
}
