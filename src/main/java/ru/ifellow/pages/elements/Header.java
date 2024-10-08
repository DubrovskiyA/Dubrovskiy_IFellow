package ru.ifellow.pages.elements;

import com.codeborne.selenide.CollectionCondition;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import ru.ifellow.pages.elements.enums.HeaderItem;

import static com.codeborne.selenide.Selenide.$$x;
import static com.codeborne.selenide.Selenide.$x;


public class Header {
    private final ElementsCollection listOfHeaderItems =
            $$x("//div[contains(@class,'header-primary')]/child::ul/child::li")
                    .as("Список элементов Хедера");
    private final ElementsCollection listOfProjectsOnDropDownMenu = $$x("//div[@id='project_current']//li")
            .as("Список проектов в дропдауне хедера \"Проеты\" ");
    private final SelenideElement createNewTask = $x("//li[@id='create-menu']")
            .as("Кнопка создания новой задачи в Хедере");

    public void openDropdownMenu(HeaderItem item) {
        listOfHeaderItems.shouldBe(CollectionCondition.sizeGreaterThanOrEqual(7));
        switch (item) {
            case PROJECTS:
                for (SelenideElement itemOfList : listOfHeaderItems) {
                    if (itemOfList.$x("./a").getText().equals("Проекты")) {
                        itemOfList.click();
                        break;
                    }
                }
        }
    }

    public void selectProjectInDropdownMenu(String projectName) {
        for (SelenideElement project : listOfProjectsOnDropDownMenu
                .shouldBe(CollectionCondition.sizeGreaterThanOrEqual(1))) {
            if (project.$x("./a").getText().contains(projectName)) {
                project.shouldBe(Condition.visible).click();
            }
        }
    }

    public void createNewTaskByDialogWindow() {
        createNewTask.click();
    }
}
