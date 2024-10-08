package ru.ifellow.steps;

import ru.ifellow.pages.ProjectTasksPage;
import ru.ifellow.pages.ProjectTasksSearchPage;
import ru.ifellow.pages.elements.Message;
import ru.ifellow.pages.elements.enums.TypeOfNewTask;
import ru.ifellow.pages.elements.enums.SideBarItems;

public class ProjectTasksPageSteps {
    private ProjectTasksPage projectTasksPage;
    private ProjectTasksSearchPage projectTasksSearchPage;
    private Message message;

    public ProjectTasksPageSteps() {
        projectTasksPage = new ProjectTasksPage();
        projectTasksSearchPage = new ProjectTasksSearchPage();
        message = new Message();
    }

    public String getCurrentProjectTitle() {
        return projectTasksPage.getProjectTitle();
    }

    public int getCountOfAllOpenedTasks() {
        return projectTasksPage.getCountOfAllOpenedTasks();
    }

    public void createAbstractTask() {
        projectTasksPage.createNewTaskByWidget(TypeOfNewTask.BUG, "emptyTask");
        projectTasksPage.getSideBar().openProjectItemOnSidebar(SideBarItems.TASKS);
    }

    public void deleteCreatedAbstractTask() {
        projectTasksPage.sortListOfTaskByCreated();
        projectTasksPage.deleteOpenedTask();
    }

    public void openTaskByName(String taskName) {
        projectTasksPage.seeAllTasksAndFilters();
        projectTasksPage.searchTasks(taskName);
        projectTasksSearchPage.selectSearchedTask();
    }


    public boolean checkTaskCreatedSuccessful() {
        String statusSubmittedTaskFromMessage = message.getStatusSubmittedTaskFromMessage();
        return statusSubmittedTaskFromMessage.contains("успешно");
    }

    public void changeStatusOfOpenedTaskToInProgress() {
        projectTasksSearchPage.moveTaskToInProgressStatus();
    }

    public void changeStatusOfOpenedTaskToDone() {
        projectTasksSearchPage.moveTaskToDoneStatus();
    }

    public void deleteCreatedBugReport() {
        projectTasksPage.deleteOpenedTask();
    }
}
