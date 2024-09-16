package edujira.ifellow.pages;

import com.codeborne.selenide.SelenideElement;
import edujira.ifellow.pages.sidebar.SideBarOnProjectPage;

import static com.codeborne.selenide.Selenide.$x;

public class ProjectMainPage extends SideBarOnProjectPage {
    private final SelenideElement nameOfProject = $x("//div[contains(@class,'project-title')]/a");

    public String getProjectTitle() {
        return nameOfProject.getAttribute("title");
    }
}
