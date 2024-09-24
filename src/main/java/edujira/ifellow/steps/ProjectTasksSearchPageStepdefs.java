package edujira.ifellow.steps;

import edujira.ifellow.pages.CreateNewTaskDialogPage;
import edujira.ifellow.pages.ProjectTasksSearchPage;
import edujira.ifellow.pages.elements.Message;
import edujira.ifellow.pages.elements.enums.Priority;
import edujira.ifellow.pages.elements.enums.Severity;
import edujira.ifellow.pages.elements.enums.TypeOfNewTask;
import edujira.ifellow.pages.elements.enums.Version;
import io.cucumber.java.ru.Дано;
import org.junit.jupiter.api.Assertions;

public class ProjectTasksSearchPageStepdefs {
    private ProjectTasksSearchPage projectTasksSearchPage;
    private CreateNewTaskDialogPage createNewTaskDialogPage;
    private Message message;
    String topicForCreatingAbstractBugReport = "Статьи на странице «Articles» не открываются";

    public ProjectTasksSearchPageStepdefs() {
        projectTasksSearchPage = new ProjectTasksSearchPage();
        createNewTaskDialogPage = new CreateNewTaskDialogPage();
        message = new Message();
    }

    @Дано("статус задачи - {string}")
    public void checkOpenedTaskStatusIs(String status) {
        projectTasksSearchPage.waitStatusToBe(status);
        Assertions.assertTrue(projectTasksSearchPage.getCurrentTaskStatus().equals(status));
    }

    @Дано("исправить в версиях - {string}")
    public void checkOpenedTaskFixVersionIs(String version) {
        Assertions.assertTrue(projectTasksSearchPage.getFixVersion().equals(version));
    }

    @Дано("пользователь создает Баг-репорт с полным заполнением на статусе заведения")
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

    @Дано("задача создана")
    public void checkTaskCreatedSuccessful() {
        String statusSubmittedTaskFromMessage = message.getStatusSubmittedTaskFromMessage();
        Assertions.assertTrue(statusSubmittedTaskFromMessage.contains("успешно"));
    }

    @Дано("пользователь изменяет статус задачи на - \"В РАБОТЕ\"")
    public void changeStatusOfOpenedTaskToInProgress() {
        projectTasksSearchPage.moveTaskToInProgressStatus();
    }

    @Дано("пользователь изменяет статус задачи на - \"ГОТОВО\"")
    public void changeStatusOfOpenedTaskToDone() {
        projectTasksSearchPage.moveTaskToDoneStatus();
    }

    @Дано("пользователь открывает созданную задачу")
    public void openCreatedBugReport() {
        projectTasksSearchPage.searchTasks(topicForCreatingAbstractBugReport);
        projectTasksSearchPage.selectSearchedTask(topicForCreatingAbstractBugReport);
        projectTasksSearchPage.waitOpenTask(topicForCreatingAbstractBugReport);
    }
}
