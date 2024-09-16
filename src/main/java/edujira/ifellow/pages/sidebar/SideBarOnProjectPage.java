package edujira.ifellow.pages.sidebar;

import com.codeborne.selenide.CollectionCondition;
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
                    if (itemOfList.$x(".//span[contains(@class,'item-label')]").getText().equals("Список задач")) {
                        itemOfList.click();
                        break;
                    }
                }
                break;
            case TASKS:
                for (SelenideElement itemOfList : listOfSidebarItems
                        .shouldBe(CollectionCondition.sizeGreaterThanOrEqual(6))) {
                    if (itemOfList.$x(".//span[contains(@class,'item-label')]").getText().equals("Задачи")) {
                        itemOfList.click();
                        break;
                    }
                }

        }
    }

}
