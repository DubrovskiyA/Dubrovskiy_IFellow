package edujira.ifellow.pages;

import com.codeborne.selenide.CollectionCondition;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import edujira.ifellow.pages.sidebar.SideBarOnProjectPage;

import static com.codeborne.selenide.Selenide.$$x;
import static com.codeborne.selenide.Selenide.$x;

public class ProjectTasksPage extends SideBarOnProjectPage {
    private final SelenideElement counter =
            $x("//div[@class='pager-container']//span[contains(text(),'из')]");
    private final SelenideElement createTask = $x("//button[contains(text(),'Создать задачу')]");
    private final SelenideElement typeSelector = $x("//div[contains(@class,'issue-type-selector')]/button");
    private final ElementsCollection listOfType = $$x("//div[contains(@id,'issue-type-dropdown')]//li");
    private final SelenideElement descriptionFieldForNewTask = $x("//textarea[@name='summary']");
    private final SelenideElement taskSortTypeDropdown = $x("//div[@class='order-by-fields']");
    private final SelenideElement createdTypeToSortTasks = $x("//label[@title='Cоздано']");
    private final SelenideElement nextPage = $x("//div[@class='pagination']//a[contains(@class,'next')]");
    private final SelenideElement previousPage = $x("//div[@class='pagination']//a[contains(@class,'previous')]");
    private final SelenideElement taskOptions = $x("//span[contains(text(),'Еще')]");
    private final SelenideElement deleteTask = $x("//span[contains(text(),'Удалить')]");
    private final SelenideElement deleteTaskSubmit = $x("//input[@id='delete-issue-submit']");


    public int getCountOfAllOpenedTasks() {
        String[] s = counter.shouldBe(Condition.visible).getText().split(" ");
        return Integer.parseInt(s[2]);
    }

    public void createNewTaskByWidget(TypeOfNewTask type, String whatNeedsToBeDone) {
        createTask.click();
        typeSelector.click();
        switch (type) {
            case HISTORY:
                for (SelenideElement itemOfList : listOfType
                        .shouldBe(CollectionCondition.sizeGreaterThanOrEqual(4))) {
                    if (itemOfList.$x("./a]").getText().equals("История")) {
                        itemOfList.click();
                        break;
                    }
                }
            case TASK:
                for (SelenideElement itemOfList : listOfType
                        .shouldBe(CollectionCondition.sizeGreaterThanOrEqual(4))) {
                    if (itemOfList.$x("./a").getText().equals("Задача")) {
                        itemOfList.click();
                        break;
                    }
                }
            case BUG:
                for (SelenideElement itemOfList : listOfType
                        .shouldBe(CollectionCondition.sizeGreaterThanOrEqual(4))) {
                    if (itemOfList.$x("./a").getText().equals("Ошибка")) {
                        itemOfList.click();
                        break;
                    }
                }
            case EPIC:
                for (SelenideElement itemOfList : listOfType
                        .shouldBe(CollectionCondition.sizeGreaterThanOrEqual(4))) {
                    if (itemOfList.$x("./a").getText().equals("Задача")) {
                        itemOfList.click();
                        break;
                    }
                }
        }
        descriptionFieldForNewTask.val(whatNeedsToBeDone).pressEnter();
    }

    public void sortListOfTaskByCreated() {
        taskSortTypeDropdown.click();
        createdTypeToSortTasks.click();
        nextPage.click();
        previousPage.click();
    }

    public void deleteTask() {
        taskOptions.click();
        deleteTask.click();
        deleteTaskSubmit.click();
    }
}
