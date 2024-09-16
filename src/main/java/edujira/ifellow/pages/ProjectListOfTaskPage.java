package edujira.ifellow.pages;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$x;

public class ProjectListOfTaskPage {
    private final SelenideElement searchTaskField = $x("//input[contains(@id,'backlog-search-input')]");
    private final SelenideElement goalTask = $x("//div[@title='TestSeleniumATHomework']");
    private final SelenideElement statusValueOfGoalTask = $x("//span[@id='status-val']");
    private final SelenideElement fixVersionsField = $x("//span[@id='fixVersions-field']");


    public void searchTask(String name) {
        searchTaskField.val(name).pressEnter();
        goalTask.click();
    }

    public String getStatusValue() {
        return statusValueOfGoalTask.getText();
    }

    public String getFixVersionsField() {
        return fixVersionsField.getText();
    }
}
