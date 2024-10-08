package ru.ifellow.pages;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import ru.ifellow.pages.elements.enums.Priority;
import ru.ifellow.pages.elements.enums.Severity;
import ru.ifellow.pages.elements.enums.TypeOfNewTask;
import ru.ifellow.pages.elements.enums.Version;

import static com.codeborne.selenide.Selenide.$$x;
import static com.codeborne.selenide.Selenide.$x;

public class CreateNewTaskDialogPage {
    private final SelenideElement TYPE_OF_NEW_TASK = $x("//input[contains(@id,'issuetype-field')]")
            .as("Поле выбора типа создаваемой задачи в диалоговом окне \"Создание задачи\"");
    private final SelenideElement TOPIC_INPUT =
            $x("//section[contains(@id,'create-issue-dialog')]//input[@id='summary']")
                    .as("Поле ввода темы создаваемой задачи в диалоговом окне \"Создание задачи\"");
    private final SelenideElement DESCRIPTION_TEXT_AREA =
            $x("//section[contains(@id,'create-issue-dialog')]//textarea[@id='description']")
                    .as("Поле ввода описания создаваемой задачи в диалоговом окне \"Создание задачи\"");
    private final ElementsCollection LIST_OF_FIX_VERSION =
            $$x("//select[@id='fixVersions']//option")
                    .as("Список элементов \"Исправить в версиях\" создаваемой задачи в диалоговом окне \"Создание задачи\"");
    private final SelenideElement PRIORITY_INPUT = $x("//input[@id='priority-field']")
            .as("Поле выбора приоритета создаваемой задачи в диалоговом окне \"Создание задачи\"");
    private final SelenideElement TAGS = $x("//textarea[@id='labels-textarea']")
            .as("Поле ввода тэга создаваемой задачи в диалоговом окне \"Создание задачи\"");
    private final SelenideElement ENVIRONMENT_TEXT_AREA =
            $x("//section[contains(@id,'create-issue-dialog')]//textarea[@id='environment']")
                    .as("Поле ввода информации об окружении создаваемой задачи в диалоговом окне \"Создание задачи\"");
    private final ElementsCollection LIST_OF_VERSION_AFFECTED =
            $$x("//select[@id='versions']//option")
                    .as("Список элементов \"Затронуты версии\" создаваемой задачи в диалоговом окне \"Создание задачи\"");
    private final SelenideElement TASK_SEVERITY_DROPDOWN =
            $x("//label[text()='Серьезность']/following-sibling::select")
                    .as("Дропдаун \"Серьезность\" создаваемой задачи в диалоговом окне \"Создание задачи\"");
    private final ElementsCollection LIST_OF_SEVERITY =
            $$x("//label[text()='Серьезность']/following-sibling::select//option")
                    .as("Список элементов \"Серьезность\" создаваемой задачи в диалоговом окне \"Создание задачи\"");
    private final SelenideElement SUBMIT_NEW_TASK = $x("//input[contains(@id,'create-issue-submit')]")
            .as("Кнопка \"Создать\" диалогового окна \"Создание задачи\"");


    public void setType(TypeOfNewTask type) {
        switch (type) {
            case HISTORY:
                TYPE_OF_NEW_TASK.val("История").pressEnter();
                break;

            case TASK:
                TYPE_OF_NEW_TASK.val("Задача").pressEnter();
                break;

            case BUG:
                TYPE_OF_NEW_TASK.val("Ошибка").pressEnter();
                break;

            case EPIC:
                TYPE_OF_NEW_TASK.val("Epic").pressEnter();
        }
    }


    public void setTopic(String topic) {
        TOPIC_INPUT.val(topic);
    }

    public void setDescription(String description) {
        DESCRIPTION_TEXT_AREA.setValue(description);
    }

    public void setEnvironment(String environment) {
        ENVIRONMENT_TEXT_AREA.setValue(environment);
    }


    public void setFixVersion(Version version) {
        switch (version) {
            case UNKNOWN:
                checkVersion(LIST_OF_FIX_VERSION, "Неизвестный");
                break;

            case VERSION1:
                checkVersion(LIST_OF_FIX_VERSION, "Version 1.0");
                break;

            case VERSION2:
                checkVersion(LIST_OF_FIX_VERSION, "Version 2.0");
        }
    }


    public void setPriority(Priority priority) {
        switch (priority) {
            case HIGHEST:
                PRIORITY_INPUT.val("Highest").pressEnter();
                break;

            case HIGH:
                PRIORITY_INPUT.val("High").pressEnter();
                break;

            case MEDIUM:
                PRIORITY_INPUT.val("Medium").pressEnter();
                break;

            case LOW:
                PRIORITY_INPUT.val("Low").pressEnter();
                break;

            case LOWEST:
                PRIORITY_INPUT.val("Lowest").pressEnter();
        }
    }

    public void setTags(String tag) {
        TAGS.setValue(tag).pressEnter();
    }


    public void setVersionAffected(Version version) {
        switch (version) {
            case UNKNOWN:
                checkVersion(LIST_OF_VERSION_AFFECTED, "Неизвестный");
                break;

            case VERSION1:
                checkVersion(LIST_OF_VERSION_AFFECTED, "Version 1.0");
                break;

            case VERSION2:
                checkVersion(LIST_OF_VERSION_AFFECTED, "Version 2.0");
        }
    }


    public void setSeverity(Severity severity) {
        switch (severity) {
            case TRIVIAL:
                checkSeverity(LIST_OF_SEVERITY, "trivial");
                break;

            case MINOR:
                checkSeverity(LIST_OF_SEVERITY, "minor");
                break;

            case MAJOR:
                checkSeverity(LIST_OF_SEVERITY, "magor");
                break;

            case CRITICAL:
                checkSeverity(LIST_OF_SEVERITY, "critical");
                break;

            case BLOCKER:
                checkSeverity(LIST_OF_SEVERITY, "blocker");
        }
    }


    public void submit() {
        SUBMIT_NEW_TASK.click();
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
                TASK_SEVERITY_DROPDOWN.click();
                element.click();
                break;
            }
        }
    }
}



