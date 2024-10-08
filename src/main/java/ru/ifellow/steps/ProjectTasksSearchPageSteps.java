package ru.ifellow.steps;

import io.qameta.allure.Step;
import ru.ifellow.pages.CreateNewTaskDialogPage;
import ru.ifellow.pages.ProjectTasksSearchPage;
import ru.ifellow.pages.elements.enums.Priority;
import ru.ifellow.pages.elements.enums.Severity;
import ru.ifellow.pages.elements.enums.TypeOfNewTask;
import ru.ifellow.pages.elements.enums.Version;

public class ProjectTasksSearchPageSteps {
    private ProjectTasksSearchPage projectTasksSearchPage;
    private CreateNewTaskDialogPage createNewTaskDialogPage;
    String topicForCreatingAbstractBugReport = "Статьи на странице «Articles» не открываются";

    public ProjectTasksSearchPageSteps() {
        projectTasksSearchPage = new ProjectTasksSearchPage();
        createNewTaskDialogPage = new CreateNewTaskDialogPage();
    }

    @Step("Поиск и открытие задачи")
    public void openCreatedBugReport() {
        projectTasksSearchPage.searchTasks(topicForCreatingAbstractBugReport);
        projectTasksSearchPage.selectSearchedTask();
        projectTasksSearchPage.waitOpenTask(topicForCreatingAbstractBugReport);
    }

    @Step("Проверка того,что Статус открытой задачи: \"{status}\"")
    public boolean checkOpenedTaskStatusIs(String status) {
        projectTasksSearchPage.waitStatusToBe(status);
        return projectTasksSearchPage.getCurrentTaskStatus().equals(status);
    }

    @Step("Проверка того,что Исправить в версиях открытой задачи: \"{version}\"")
    public boolean checkOpenedTaskFixVersionIs(String version) {
        return projectTasksSearchPage.getFixVersion().equals(version);
    }

    @Step("Создание баг-репорта с полным заполнением")
    public void createAbstractBugReportWithFullFilling() {
        projectTasksSearchPage.getHeader().createNewTaskByDialogWindow();
        createNewTaskDialogPage.setType(TypeOfNewTask.BUG);
        createNewTaskDialogPage.setTopic(topicForCreatingAbstractBugReport);
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
    }
}
