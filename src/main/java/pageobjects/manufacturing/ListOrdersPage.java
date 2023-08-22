package pageobjects.manufacturing;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pageobjects.BasePage;

public class ListOrdersPage extends BasePage {

    @FindBy(xpath = "//button[@data-original-title='Create record']")
    WebElement createOrderButton;

    public ListOrdersPage() {
        super();
    }

    public void clickCreateOrderButton() {
        waitAndClick(createOrderButton);
    }
}
