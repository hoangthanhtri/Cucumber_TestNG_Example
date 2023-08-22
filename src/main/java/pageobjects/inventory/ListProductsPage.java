package pageobjects.inventory;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pageobjects.BasePage;

public class ListProductsPage extends BasePage {
    @FindBy(xpath = "//button[@title='Create record']")
    WebElement createProductButton;

    public ListProductsPage() {
        super();
    }

    public void clickCreateProductButton() {
        waitAndClick(createProductButton);
    }
}
