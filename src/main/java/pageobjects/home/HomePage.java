package pageobjects.home;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pageobjects.BasePage;

public class HomePage extends BasePage {
    @FindBy(xpath = "//span[@class='oe_topbar_name']")
    WebElement loginedUsername;
    @FindBy(xpath = "//a[@href='#menu_id=108&action_id=232']")
    WebElement inventoryButton;
    @FindBy(xpath = "//a[@href='#menu_id=150&action_id=285']")
    WebElement manufacturingButton;

    public HomePage() {
        super();
    }

    public void clickInventoryButton() {
        waitAndClick(inventoryButton);
    }

    public void clickManufacturingButton() {
        waitAndClick(manufacturingButton);
    }
}
