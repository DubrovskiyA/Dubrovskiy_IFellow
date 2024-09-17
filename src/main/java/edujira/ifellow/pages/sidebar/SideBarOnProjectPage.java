package edujira.ifellow.pages.sidebar;

import com.codeborne.selenide.CollectionCondition;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$$x;

public class SideBarOnProjectPage {
    private final ElementsCollection listOfSidebarItems =
            $$x("//div[@data-id='sidebar-navigation-panel']//li");

    public void openProjectItemOnSidebar(SideBarItems item) {//
        switch (item) {
            case LIST_OF_TASKS:
                for (SelenideElement itemOfList : listOfSidebarItems
                        .shouldBe(CollectionCondition.sizeGreaterThanOrEqual(6))) {
                    if (itemOfList.$x(".//span[contains(@class,'item-label')]")
                            .shouldBe(Condition.visible)
                            .getText()
                            .equals("Список задач")) {
                        itemOfList.$x(".//span[contains(@class,'agile-icon-plan')]").click();
                        break;
                    }
                }
                break;
            case TASKS:
                for (SelenideElement itemOfList : listOfSidebarItems
                        .shouldBe(CollectionCondition.sizeGreaterThanOrEqual(6))) {
                    if (itemOfList.$x(".//span[contains(@class,'item-label')]")
                            .shouldBe(Condition.visible)
                            .getText()
                            .equals("Задачи")) {
                        itemOfList.$x(".//span[contains(@class,'icon-sidebar-issues')]").click();
                        break;
                    }
                }

        }
    }

}
