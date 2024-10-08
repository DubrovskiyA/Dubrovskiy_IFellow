package ru.ifellow.steps;

import io.qameta.allure.Step;
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

    @Step("Получение названия открытого проекта")
    public String getCurrentProjectTitle() {
        return projectTasksPage.getProjectTitle();
    }

    @Step("Получение количества открытых задач в проекте")
    public int getCountOfAllOpenedTasks() {
        return projectTasksPage.getCountOfAllOpenedTasks();
    }

    @Step("Создание пустой задачи")
    public void createAbstractTask() {
        projectTasksPage.createNewTaskByWidget(TypeOfNewTask.BUG, "emptyTask");
        projectTasksPage.getSideBar().openProjectItemOnSidebar(SideBarItems.TASKS);
    }

    @Step("Удаление пустой задачи")
    public void deleteCreatedAbstractTask() {
        projectTasksPage.sortListOfTaskByCreated();
        projectTasksPage.deleteOpenedTask();
    }

    @Step("Поиск и открытие задачи: \"{taskName}\"")
    public void openTaskByName(String taskName) {
        projectTasksPage.seeAllTasksAndFilters();
        projectTasksPage.searchTasks(taskName);
        projectTasksSearchPage.selectSearchedTask();
    }

    @Step("Проверка того, что после создания новой задачи всплывает сообщение об успешном создании")
    public boolean checkTaskCreatedSuccessful() {
        String statusSubmittedTaskFromMessage = message.getStatusSubmittedTaskFromMessage();
        return statusSubmittedTaskFromMessage.contains("успешно");
    }

    @Step("Изменение статуса задачи на \"В РАБОТЕ\"")
    public void changeStatusOfOpenedTaskToInProgress() {
        projectTasksSearchPage.moveTaskToInProgressStatus();
    }

    @Step("Изменение статуса задачи на \"ГОТОВО\"")
    public void changeStatusOfOpenedTaskToDone() {
        projectTasksSearchPage.moveTaskToDoneStatus();
    }

    @Step("Удаление задачи")
    public void deleteCreatedBugReport() {
        projectTasksPage.deleteOpenedTask();
    }
}
