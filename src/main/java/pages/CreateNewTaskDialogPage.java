package pages;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import pages.elements.enums.Priority;
import pages.elements.enums.Severity;
import pages.elements.enums.TypeOfNewTask;
import pages.elements.enums.Version;

import static com.codeborne.selenide.Selenide.$$x;
import static com.codeborne.selenide.Selenide.$x;

public class CreateNewTaskDialogPage {
    private final SelenideElement typeOfNewTask = $x("//input[contains(@id,'issuetype-field')]");
    private final SelenideElement topicInput =
            $x("//section[contains(@id,'create-issue-dialog')]//input[@id='summary']");
    private final SelenideElement descriptionTextArea =
            $x("//section[contains(@id,'create-issue-dialog')]//textarea[@id='description']");
    private final ElementsCollection listOfFixVersion =
            $$x("//select[@id='fixVersions']//option");
    private final SelenideElement priorityInput = $x("//input[@id='priority-field']");
    private final SelenideElement tags = $x("//textarea[@id='labels-textarea']");
    private final SelenideElement environmentTextArea =
            $x("//section[contains(@id,'create-issue-dialog')]//textarea[@id='environment']");
    private final ElementsCollection listOfVersionAffected =
            $$x("//select[@id='versions']//option");
    private final ElementsCollection listOfSeverity =
            $$x("//label[text()='Серьезность']/following-sibling::select//option");
    private final SelenideElement taskSeverityDropdown =
            $x("//label[text()='Серьезность']/following-sibling::select");
    private final SelenideElement submitNewTask = $x("//input[contains(@id,'create-issue-submit')]");

    public void setType(TypeOfNewTask type) {
        switch (type) {
            case HISTORY:
                typeOfNewTask.val("История").pressEnter();
                break;

            case TASK:
                typeOfNewTask.val("Задача").pressEnter();
                break;

            case BUG:
                typeOfNewTask.val("Ошибка").pressEnter();
                break;

            case EPIC:
                typeOfNewTask.val("Epic").pressEnter();
        }
    }


    public void setTopic(String topic) {
        topicInput.val(topic);
    }

    public void setDescription(String description) {
        descriptionTextArea.setValue(description);
    }

    public void setEnvironment(String environment) {
        environmentTextArea.setValue(environment);
    }


    public void setFixVersion(Version version) {
        switch (version) {
            case UNKNOWN:
                checkVersion(listOfFixVersion, "Неизвестный");
                break;

            case VERSION1:
                checkVersion(listOfFixVersion, "Version 1.0");
                break;

            case VERSION2:
                checkVersion(listOfFixVersion, "Version 2.0");
        }
    }


    public void setPriority(Priority priority) {
        switch (priority) {
            case HIGHEST:
                priorityInput.val("Highest").pressEnter();
                break;

            case HIGH:
                priorityInput.val("High").pressEnter();
                break;

            case MEDIUM:
                priorityInput.val("Medium").pressEnter();
                break;

            case LOW:
                priorityInput.val("Low").pressEnter();
                break;

            case LOWEST:
                priorityInput.val("Lowest").pressEnter();
        }
    }

    public void setTags(String tag) {
        tags.setValue(tag).pressEnter();
    }


    public void setVersionAffected(Version version) {
        switch (version) {
            case UNKNOWN:
                checkVersion(listOfVersionAffected, "Неизвестный");
                break;

            case VERSION1:
                checkVersion(listOfVersionAffected, "Version 1.0");
                break;

            case VERSION2:
                checkVersion(listOfVersionAffected, "Version 2.0");
        }
    }


    public void setSeverity(Severity severity) {
        switch (severity) {
            case TRIVIAL:
                checkSeverity(listOfSeverity, "trivial");
                break;

            case MINOR:
                checkSeverity(listOfSeverity, "minor");
                break;

            case MAJOR:
                checkSeverity(listOfSeverity, "magor");
                break;

            case CRITICAL:
                checkSeverity(listOfSeverity, "critical");
                break;

            case BLOCKER:
                checkSeverity(listOfSeverity, "blocker");
        }
    }


    public void submit() {
        submitNewTask.click();
    }


    private void checkVersion(ElementsCollection list, String fixVersion) {
        for (SelenideElement element : list) {
            if (element.getText().contains(fixVersion)) {
                element.click();
                break;
            }
        }
    }


    private void checkSeverity(ElementsCollection list, String severity) {
        for (SelenideElement element : list) {
            if (element.getText().contains(severity)) {
                taskSeverityDropdown.click();
                element.click();
                break;
            }
        }
    }
}



