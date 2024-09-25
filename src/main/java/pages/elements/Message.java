package pages.elements;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$x;

public class Message {
    private final SelenideElement statusMessage = $x("//a[contains(@class,'issue-created-key')]/..");
    private final SelenideElement nameNewTask = $x("//a[contains(@class,'issue-created-key')]");

    public String getStatusSubmittedTaskFromMessage() {
        return statusMessage.getText();
    }

}
