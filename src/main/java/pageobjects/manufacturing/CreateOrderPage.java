package pageobjects.manufacturing;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import pageobjects.BasePage;

public class CreateOrderPage extends BasePage {
    @FindBy(xpath = "//label[text()='Product']/ancestor::tr//input")
    WebElement productNameComboBox;
    @FindBy(xpath = "//button[@name='action_confirm']")
    WebElement confirmOrderButton;
    @FindBy(xpath = "//span[text()='Mark as Done']/parent::button[@class='btn btn-primary']")
    WebElement markAsDoneOrderButton;
    @FindBy(xpath = "(//a[text()='Components']/ancestor::div[@class='o_notebook']//a[text()='Add a line'])[1]")
    WebElement addLineComponents;
    @FindBy(xpath = "//div[contains(@class,'active')]//a[text()='Add a line']/ancestor::tr/preceding-sibling::tr[contains(@class,'o_selected_row')]/td[1]//input")
    WebElement componentComboBox;
    @FindBy(xpath = "//div[contains(@class,'active')]//a[text()='Add a line']/ancestor::tr/preceding-sibling::tr[contains(@class,'o_selected_row')]/td[3]//input")
    WebElement consumedOfComponent;
    @FindBy(xpath = "//span[text()='Apply']/parent::button[@class='btn btn-primary']")
    WebElement applyButton;
    @FindBy(xpath = "//div[@role='toolbar']/button[@title='Save record']")
    WebElement saveButton;
    @FindBy(xpath = "//div[@class='oe_title']//span")
    WebElement manufacturingReferenceLabel;
    private String manufacturingReference;

    public CreateOrderPage() {
        super();
    }

    public void clickConfirmOrderButton() {
        waitAndClick(confirmOrderButton);
        waitUntilConfirmedOrder();
        manufacturingReference = waitAndGetText(manufacturingReferenceLabel);
    }

    public void clickMarkAsDoneOrderButton() {

        waitAndClick(markAsDoneOrderButton);
    }

    public void searchAndSelectProductNameOrderByIndex(String searchValue, int index) {
        searchAndSelectComboBoxByIndex(productNameComboBox, searchValue, index);
    }

    public void clickAddLineText() {
        waitAndClick(addLineComponents);
    }

    public void searchAndSelectProductComponent(String searchValue) {
        searchAndSelectComboBoxByIndex(componentComboBox, searchValue);
    }

    public void searchAndSelectProductComponent(String searchValue, int index) {
        searchAndSelectComboBoxByIndex(componentComboBox, searchValue, index);
    }

    public void inputConsumedComponent(String inputValue) {
        waitAndSendKeys(consumedOfComponent, inputValue);
    }

    public void clickApplyButton() {
        waitAndClick(applyButton);
    }

    public void clickSaveButton() {
        waitAndClick(saveButton);
    }

    public String getManufacturingReferenceLabel() {
        return manufacturingReference;
    }

    private void waitUntilConfirmedOrder() {
        waitHandler.wait.until(ExpectedConditions.not(ExpectedConditions.textToBePresentInElement(manufacturingReferenceLabel, "New")));
    }
}
