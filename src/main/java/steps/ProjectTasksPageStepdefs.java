package steps;

import pages.ProjectTasksPage;
import pages.ProjectTasksSearchPage;
import pages.elements.enums.SideBarItems;
import pages.elements.enums.TypeOfNewTask;
import io.cucumber.java.ru.Дано;
import org.junit.jupiter.api.Assertions;

public class ProjectTasksPageStepdefs {
    private ProjectTasksPage projectTasksPage;
    private ProjectTasksSearchPage projectTasksSearchPage;
    private static int countOfOpenedTasks;

    public ProjectTasksPageStepdefs() {
        projectTasksPage = new ProjectTasksPage();
        projectTasksSearchPage = new ProjectTasksSearchPage();
    }

    @Дано("пользователь находится на странице проекта {string}")
    public void checkOpenedProjectTitle(String currentProjectTitle) {
        Assertions.assertEquals(currentProjectTitle, projectTasksPage.getProjectTitle());
    }


    @Дано("пользователь создает одну новую задачу")
    public void createAbstractTask() {
        countOfOpenedTasks = projectTasksPage.getCountOfAllOpenedTasks();
        projectTasksPage.createNewTaskByWidget(TypeOfNewTask.BUG, "emptyTask");
        projectTasksPage.getSideBar().openProjectItemOnSidebar(SideBarItems.TASKS);
    }

    @Дано("^счетчик открытых задач увеличивается на (\\d)$")
    public void checkCounterIncreased(int count) {
        Assertions.assertEquals(countOfOpenedTasks + count, projectTasksPage.getCountOfAllOpenedTasks());
    }

    public void deleteCreatedAbstractTask() {
        projectTasksPage.sortListOfTaskByCreated();
        projectTasksPage.deleteOpenedTask();
    }

    @Дано("пользователь находит и открывает задачу {string}")
    public void openTaskByName(String taskName) {
        projectTasksPage.seeAllTasksAndFilters();
        projectTasksPage.searchTasks(taskName);
        projectTasksSearchPage.selectSearchedTask(taskName);
    }
}
