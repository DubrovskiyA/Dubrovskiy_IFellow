package edujira.ifellow.pages;

import com.codeborne.selenide.SelenideElement;
import edujira.ifellow.pages.sidebar.SideBarOnProjectPage;

import static com.codeborne.selenide.Selenide.$x;

public class ProjectMainPage {
    SideBarOnProjectPage sideBar;
    private final SelenideElement nameOfProject = $x("//div[contains(@class,'project-title')]/a");

    public ProjectMainPage() {
        sideBar = new SideBarOnProjectPage();
    }

    public String getProjectTitle() {
        return nameOfProject.getAttribute("title");
    }

    public SideBarOnProjectPage getSideBar() {
        return sideBar;
    }
}
