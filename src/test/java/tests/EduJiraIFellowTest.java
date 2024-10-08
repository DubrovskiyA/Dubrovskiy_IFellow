package tests;

import com.codeborne.selenide.junit5.BrowserPerTestStrategyExtension;
import org.junit.jupiter.api.DisplayName;
import ru.ifellow.steps.ProjectTasksSearchPageSteps;
import ru.ifellow.steps.ProjectTasksPageSteps;
import ru.ifellow.steps.SystemDashboardPageSteps;
import hooks.WebHooks;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

@ExtendWith(BrowserPerTestStrategyExtension.class)
public class EduJiraIFellowTest extends WebHooks {
    private SystemDashboardPageSteps systemDashboardPageSteps=new SystemDashboardPageSteps();
    private ProjectTasksPageSteps projectTasksPageSteps =new ProjectTasksPageSteps();
    private ProjectTasksSearchPageSteps projectTasksSearchSteps=new ProjectTasksSearchPageSteps();
    @Test
    @DisplayName("Авторизация на сайте \"https://edujira.ifellow.ru\"")
    public void test1(){
        systemDashboardPageSteps.logIn();
        String authorizedUserName=systemDashboardPageSteps.getAuthorizedUserName();
        Assertions.assertEquals("AT10",authorizedUserName);
    }
    @Test
    @DisplayName("Авторизация и открытие проекта \"Test\" на сайте \"https://edujira.ifellow.ru\"")
    public void test2(){
        systemDashboardPageSteps.logIn();
        String authorizedUserName=systemDashboardPageSteps.getAuthorizedUserName();
        Assertions.assertEquals("AT10",authorizedUserName);

        systemDashboardPageSteps.openProject("Test");
        String openedProjectName= projectTasksPageSteps.getCurrentProjectTitle();
        Assertions.assertEquals("Test",openedProjectName);
    }
    @Test
    @DisplayName("Авторизация, открытие проекта и проверка счетчика открытых задач на сайте \"https://edujira.ifellow.ru\"")
    public void test3(){
        systemDashboardPageSteps.logIn();
        String authorizedUserName=systemDashboardPageSteps.getAuthorizedUserName();
        Assertions.assertEquals("AT10",authorizedUserName);

        systemDashboardPageSteps.openProject("Test");
        String openedProjectName= projectTasksPageSteps.getCurrentProjectTitle();
        Assertions.assertEquals("Test",openedProjectName);

        int currentCount= projectTasksPageSteps.getCountOfAllOpenedTasks();
        projectTasksPageSteps.createAbstractTask();
        int countAfterAddingTask= projectTasksPageSteps.getCountOfAllOpenedTasks();
        Assertions.assertEquals(currentCount+1,countAfterAddingTask);
        projectTasksPageSteps.deleteCreatedAbstractTask();
    }
    @Test
    @DisplayName("Авторизация, открытие проекта, проверка счетчика открытых задач и проверка полей \"Статус\" и " +
            "\"Исправить в версиях\" задачи \"TestSeleniumATHomework\" на сайте \"https://edujira.ifellow.ru\"")
    public void test4(){
        systemDashboardPageSteps.logIn();
        String authorizedUserName=systemDashboardPageSteps.getAuthorizedUserName();
        Assertions.assertEquals("AT10",authorizedUserName);

        systemDashboardPageSteps.openProject("Test");
        String openedProjectName= projectTasksPageSteps.getCurrentProjectTitle();
        Assertions.assertEquals("Test",openedProjectName);

        int currentCount= projectTasksPageSteps.getCountOfAllOpenedTasks();
        projectTasksPageSteps.createAbstractTask();
        int countAfterAddingTask= projectTasksPageSteps.getCountOfAllOpenedTasks();
        Assertions.assertEquals(currentCount+1,countAfterAddingTask);
        projectTasksPageSteps.deleteCreatedAbstractTask();

        projectTasksPageSteps.openTaskByName("TestSeleniumATHomework");
        Assertions.assertTrue(projectTasksSearchSteps.checkOpenedTaskStatusIs("СДЕЛАТЬ"));
        Assertions.assertTrue(projectTasksSearchSteps.checkOpenedTaskFixVersionIs("Version 2.0"));
    }
    @Test
    @DisplayName("Авторизация, открытие проекта, проверка счетчика открытых задач, проверка полей \"Статус\" и " +
            "\"Исправить в версиях\" задачи \"TestSeleniumATHomework\", создание баг-репорта с полным описанием и " +
            "продвижение его по статусам на сайте \"https://edujira.ifellow.ru\"")
    public void test5(){
        systemDashboardPageSteps.logIn();
        String authorizedUserName=systemDashboardPageSteps.getAuthorizedUserName();
        Assertions.assertEquals("AT10",authorizedUserName);

        systemDashboardPageSteps.openProject("Test");
        String openedProjectName= projectTasksPageSteps.getCurrentProjectTitle();
        Assertions.assertEquals("Test",openedProjectName);

        int currentCount= projectTasksPageSteps.getCountOfAllOpenedTasks();
        projectTasksPageSteps.createAbstractTask();
        int countAfterAddingTask= projectTasksPageSteps.getCountOfAllOpenedTasks();
        Assertions.assertEquals(currentCount+1,countAfterAddingTask);
        projectTasksPageSteps.deleteCreatedAbstractTask();

        projectTasksPageSteps.openTaskByName("TestSeleniumATHomework");
        Assertions.assertTrue(projectTasksSearchSteps.checkOpenedTaskStatusIs("СДЕЛАТЬ"));
        Assertions.assertTrue(projectTasksSearchSteps.checkOpenedTaskFixVersionIs("Version 2.0"));

        projectTasksSearchSteps.createAbstractBugReportWithFullFilling();
        Assertions.assertTrue(projectTasksPageSteps.checkTaskCreatedSuccessful());
        projectTasksSearchSteps.openCreatedBugReport();
        Assertions.assertTrue(projectTasksSearchSteps.checkOpenedTaskStatusIs("СДЕЛАТЬ"));
        projectTasksPageSteps.changeStatusOfOpenedTaskToInProgress();
        Assertions.assertTrue(projectTasksSearchSteps.checkOpenedTaskStatusIs("В РАБОТЕ"));
        projectTasksPageSteps.changeStatusOfOpenedTaskToDone();
        Assertions.assertTrue(projectTasksSearchSteps.checkOpenedTaskStatusIs("ГОТОВО"));
//        должно быть в hooks!
        projectTasksPageSteps.deleteCreatedBugReport();
    }
}
