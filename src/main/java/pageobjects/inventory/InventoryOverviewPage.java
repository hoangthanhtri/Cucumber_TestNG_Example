package pageobjects.inventory;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pageobjects.BasePage;

public class InventoryOverviewPage extends BasePage {
    @FindBy(xpath = "//div[@role='menu']//span[contains(text(),'Product')]/parent::button")
    WebElement productDropdownList;
    @FindBy(xpath = "//span[text()='Manufacturing']/parent::a")
    WebElement manufacturingLabel;

    public InventoryOverviewPage() {
        super();
    }

    public void selectDropdownListOption(String selectValue) {
        selectHeaderDropdownListByName(productDropdownList, selectValue);
    }

    public void clickManufacturingLabel() {
        waitAndClick(manufacturingLabel);
    }
}
