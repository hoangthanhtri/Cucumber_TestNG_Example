package pageobjects.inventory;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pageobjects.BasePage;

public class UpdateProductQuantityPage extends BasePage {

    @FindBy(xpath = "//button[contains(text(),'Create')]")
    WebElement createButton;
    @FindBy(xpath = "//li[@class='breadcrumb-item active']/span[text()='Update Quantity']")
    WebElement updateQuantityBreadCrumb;
    @FindBy(xpath = "//input[contains(@name,'inventory_quantity')]")
    WebElement countedQuantityTextbox;
    @FindBy(xpath = "//button[contains(@title,'Save record')]")
    WebElement saveButton;

    public UpdateProductQuantityPage() {
        super();
    }

    public void clickCreateButton() {
        waitHandler.waitForVisibilityOf(updateQuantityBreadCrumb);
        waitAndClick(createButton);
    }

    public void inputcountedQuantityTextbox(String inputValue) {
        waitAndSendKeys(countedQuantityTextbox, inputValue);
    }

    public void clickSaveButton() {
        waitAndClick(saveButton);
    }


}
