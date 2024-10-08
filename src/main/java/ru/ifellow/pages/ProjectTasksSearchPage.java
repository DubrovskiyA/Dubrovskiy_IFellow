package ru.ifellow.pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import ru.ifellow.pages.elements.Header;

import static com.codeborne.selenide.Selenide.$x;

public class ProjectTasksSearchPage {
    Header header;
    private final SelenideElement TASK_STATUS = $x("//span[@id='status-val']/span")
            .as("Статус открытой задачи");
    private final SelenideElement FIRST_TASK_FROM_SEARCH_LIST = $x("//ol[@class='issue-list']/li")
            .as("Первая задаче в списке результатов поиска");
    private final SelenideElement FIX_VERSION = $x("//span[@id='fixVersions-field']/a")
            .as("\"Исправить в версиях\" открытой задачи");
    private final SelenideElement SEARCH_INPUT = $x("//input[contains(@class,'search-entry')]")
            .as("Поисковое поле на странице проекта \"Искать\"");
    private final SelenideElement TOPIC_OF_TASK = $x("//h1[@id='summary-val']")
            .as("Название открытой задачи");
    private final SelenideElement TO_IN_PROGRESS = $x("//span[text()='В работе']")
            .as("Кнопка перевода открытой задачи в статус \"В работе\"");
    private final SelenideElement TO_DONE_DROPDOWN = $x("//a[contains(@id,'transitions_more')]")
            .as("Дропдаун \"Бизнесс-процесс\" открытой задачи");
    private final SelenideElement TO_DONE = $x("//span[text()='Выполнено']")
            .as("Кнопка перевода открытой задачи в статус \"Выполнено\" в дропдауне \"Бизнесс-процесс\" открытой задачи");

    public ProjectTasksSearchPage() {
        header = new Header();
    }

    public String getCurrentTaskStatus() {
        return TASK_STATUS.getText();
    }

    public String getFixVersion() {
        return FIX_VERSION.getText().trim();
    }

    public void searchTasks(String task) {
        SEARCH_INPUT.val(task).pressEnter();
    }

    public void selectSearchedTask() {
        FIRST_TASK_FROM_SEARCH_LIST.shouldBe(Condition.interactable).click();
    }

    public void waitOpenTask(String condition) {
        TOPIC_OF_TASK.shouldBe(Condition.text(condition));
    }

    public String getCurrentOpenTaskTopic() {
        return TOPIC_OF_TASK.getText().trim();
    }

    public void moveTaskToInProgressStatus() {
        TO_IN_PROGRESS.click();
    }

    public void waitStatusToBe(String statusToBe) {
        TASK_STATUS.shouldBe(Condition.text(statusToBe));
    }

    public void moveTaskToDoneStatus() {
        TO_DONE_DROPDOWN.click();
        TO_DONE.click();
    }

    public Header getHeader() {
        return header;
    }
}
