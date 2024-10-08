package ru.ifellow.pages;

import com.codeborne.selenide.CollectionCondition;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import ru.ifellow.pages.elements.enums.TypeOfNewTask;
import ru.ifellow.pages.elements.Header;
import ru.ifellow.pages.elements.SideBarOnProjectPage;

import static com.codeborne.selenide.Selenide.$$x;
import static com.codeborne.selenide.Selenide.$x;

public class ProjectTasksPage {
    Header header;
    SideBarOnProjectPage sideBar;
    private final SelenideElement NAME_OF_PROJECT = $x("//div[contains(@class,'project-title')]/a")
            .as("Название открытого проекта");
    private final SelenideElement COUNTER =
            $x("//div[@class='pager-container']//span[contains(text(),'из')]")
                    .as("Счетчик задач на странице проекта \"Задачи\"");
    private final SelenideElement CREATE_TASK = $x("//button[contains(text(),'Создать задачу')]")
            .as("Кнопка создания задачи внизу списка задач на странице проекта \"Задачи\"");
    private final SelenideElement TYPE_SELECTOR = $x("//div[contains(@class,'issue-type-selector')]/button")
            .as("Меню выбора типа создаваемой задачи на странице проекта \"Задачи\"");
    private final ElementsCollection LIST_OF_TYPE = $$x("//div[contains(@id,'issue-type-dropdown')]//li")
            .as("Список типов создаваемой задачи на странице проекта \"Задачи\"");
    private final SelenideElement DESCRIPTION_FIELD_FOR_NEW_TASK = $x("//textarea[@name='summary']")
            .as("Поле ввода описания создаваемой задачи на странице проекта \"Задачи\"");
    private final SelenideElement TASK_SORT_TYPE_DROPDOWN = $x("//div[@class='order-by-fields']")
            .as("Дропдаун сортировки задач на странице проекта \"Задачи\"");
    private final SelenideElement CREATED_TASKS_TO_SORT_TASKS = $x("//label[@title='Cоздано']")
            .as("Сортировка задач на странице проекта \"Задачи\" по \"Создано\"");
    private final SelenideElement NEXT_PAGE = $x("//div[@class='pagination']//a[contains(@class,'next')]")
            .as("Кнопка пролистывания страниц списка задач вперед на странице проекта \"Задачи\"");
    private final SelenideElement PREVIOUS_PAGE = $x("//div[@class='pagination']//a[contains(@class,'previous')]")
            .as("Кнопка пролистывания страниц списка задач назад на странице проекта \"Задачи\"");
    private final SelenideElement TASK_OPTIONS = $x("//span[contains(text(),'Еще')]")
            .as("Дропдаун открытой задачи \"Еще...\" на странице проекта \"Задачи\"");
    private final SelenideElement DELETE_TASK = $x("//span[contains(text(),'Удалить')]")
            .as("Удаление задачи в дропдауне открытой задачи \"Еще...\" на странице проекта \"Задачи\"");
    private final SelenideElement DELETE_TASK_SUBMIT = $x("//input[@id='delete-issue-submit']")
            .as("Кнопка подтверждения удаления задачи в всплывающем модальном окне");
    private final SelenideElement SEE_ALL_TASKS_AND_FILTERS = $x("//div[@id='full-issue-navigator']/a")
            .as("Ссылка \"Посмотреть все задачи и фильтры\" на странице проекта \"Задачи\"");
    private final SelenideElement SEARCH_INPUT = $x("//input[contains(@class,'search-entry')]")
            .as("Поисковое поле на странице проекта \"Искать\" открывающейся после клика на ссылку " +
                    "\"Посмотреть все задачи и фильтры\" на странице проекта \"Задачи\"");


    public ProjectTasksPage() {
        header = new Header();
        sideBar = new SideBarOnProjectPage();
    }

    public String getProjectTitle() {
        return NAME_OF_PROJECT.getAttribute("title");
    }

    public int getCountOfAllOpenedTasks() {
        String[] s = COUNTER.shouldBe(Condition.visible).getText().split(" ");
        return Integer.parseInt(s[2]);
    }

    public void createNewTaskByWidget(TypeOfNewTask type, String whatNeedsToBeDone) {
        CREATE_TASK.click();
        TYPE_SELECTOR.click();
        switch (type) {
            case HISTORY:
                for (SelenideElement itemOfList : LIST_OF_TYPE
                        .shouldBe(CollectionCondition.sizeGreaterThanOrEqual(4))) {
                    if (itemOfList.$x("./a]").getText().equals("История")) {
                        itemOfList.click();
                        break;
                    }
                }
            case TASK:
                for (SelenideElement itemOfList : LIST_OF_TYPE
                        .shouldBe(CollectionCondition.sizeGreaterThanOrEqual(4))) {
                    if (itemOfList.$x("./a").getText().equals("Задача")) {
                        itemOfList.click();
                        break;
                    }
                }
            case BUG:
                for (SelenideElement itemOfList : LIST_OF_TYPE
                        .shouldBe(CollectionCondition.sizeGreaterThanOrEqual(4))) {
                    if (itemOfList.$x("./a").getText().equals("Ошибка")) {
                        itemOfList.click();
                        break;
                    }
                }
            case EPIC:
                for (SelenideElement itemOfList : LIST_OF_TYPE
                        .shouldBe(CollectionCondition.sizeGreaterThanOrEqual(4))) {
                    if (itemOfList.$x("./a").getText().equals("Задача")) {
                        itemOfList.click();
                        break;
                    }
                }
        }
        DESCRIPTION_FIELD_FOR_NEW_TASK.val(whatNeedsToBeDone).pressEnter();
    }

    public void sortListOfTaskByCreated() {
        TASK_SORT_TYPE_DROPDOWN.click();
        CREATED_TASKS_TO_SORT_TASKS.click();
        NEXT_PAGE.click();
        PREVIOUS_PAGE.click();
    }

    public void deleteOpenedTask() {
        TASK_OPTIONS.click();
        DELETE_TASK.click();
        DELETE_TASK_SUBMIT.click();
    }

    public void seeAllTasksAndFilters() {
        SEE_ALL_TASKS_AND_FILTERS.click();
    }

    public void searchTasks(String task) {
        SEARCH_INPUT.val(task).pressEnter();
    }


    public Header getHeader() {
        return header;
    }

    public SideBarOnProjectPage getSideBar() {
        return sideBar;
    }
}
