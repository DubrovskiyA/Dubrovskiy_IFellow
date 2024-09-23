package testsBySteps;

import com.codeborne.selenide.junit5.BrowserPerTestStrategyExtension;
import com.codeborne.selenide.junit5.SoftAssertsExtension;
import edujira.ifellow.steps.ProjectTasksSearchPageSteps;
import edujira.ifellow.steps.ProjectTasksPageSteps;
import edujira.ifellow.steps.SystemDashboardPageSteps;
import hooks.WebHooks;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

@ExtendWith(BrowserPerTestStrategyExtension.class)
public class TestsBySteps extends WebHooks {
    private SystemDashboardPageSteps systemDashboardPageSteps=new SystemDashboardPageSteps();
    private ProjectTasksPageSteps projectTasksPageSteps =new ProjectTasksPageSteps();
    private ProjectTasksSearchPageSteps projectTasksSearchSteps=new ProjectTasksSearchPageSteps();
    @Test
    public void test1(){
        systemDashboardPageSteps.logIn();
        String authorizedUserName=systemDashboardPageSteps.getAuthorizedUserName();
        Assertions.assertEquals("AT10",authorizedUserName);
    }
    @Test
    public void test2(){
        systemDashboardPageSteps.logIn();
        String authorizedUserName=systemDashboardPageSteps.getAuthorizedUserName();
        Assertions.assertEquals("AT10",authorizedUserName);

        systemDashboardPageSteps.openProject("Test");
        String openedProjectName= projectTasksPageSteps.getCurrentProjectTitle();
        Assertions.assertEquals("Test",openedProjectName);
    }
    @Test
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
