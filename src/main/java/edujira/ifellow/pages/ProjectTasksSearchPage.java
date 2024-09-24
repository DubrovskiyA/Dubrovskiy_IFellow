package edujira.ifellow.pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import edujira.ifellow.pages.elements.Header;

import static com.codeborne.selenide.Selenide.$x;

public class ProjectTasksSearchPage {
    Header header;
    private final SelenideElement taskStatus = $x("//span[@id='status-val']/span");
    private final SelenideElement firstTaskFromSearchList = $x("//ol[@class='issue-list']/li");
    private final SelenideElement fixVersion = $x("//span[@id='fixVersions-field']/a");
    private final SelenideElement searchInput = $x("//input[contains(@class,'search-entry')]");
    private final SelenideElement topicOfTask = $x("//h1[@id='summary-val']");
    private final SelenideElement toInProgress = $x("//span[text()='В работе']");
    private final SelenideElement toDoneDropdown = $x("//a[contains(@id,'transitions_more')]");
    private final SelenideElement toDone = $x("//span[text()='Выполнено']");


    public ProjectTasksSearchPage() {
        header = new Header();
    }

    public String getCurrentTaskStatus() {
        return taskStatus.getText();
    }

    public String getFixVersion() {
        return fixVersion.getText().trim();
    }

    public void searchTasks(String task) {
        searchInput.val(task).pressEnter();
    }

    public void selectSearchedTask(String value) {
        firstTaskFromSearchList.shouldBe(Condition.attribute("title", value)).click();
    }

    public void waitOpenTask(String condition) {
        topicOfTask.shouldBe(Condition.text(condition));
    }

    public String getCurrentOpenTaskTopic() {
        return topicOfTask.getText().trim();
    }

    public void moveTaskToInProgressStatus() {
        toInProgress.click();
    }

    public void waitStatusToBe(String statusToBe) {
        taskStatus.shouldBe(Condition.text(statusToBe));
    }

    public void moveTaskToDoneStatus() {
        toDoneDropdown.click();
        toDone.click();
    }

    public Header getHeader() {
        return header;
    }
}
