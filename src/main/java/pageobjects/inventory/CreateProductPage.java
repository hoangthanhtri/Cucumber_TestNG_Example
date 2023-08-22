package pageobjects.inventory;


import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pageobjects.BasePage;

public class CreateProductPage extends BasePage {
    private static final String CREATE_PRODUCT_PAGE_PATH = "/web#cids=1&menu_id=108&action=215&model=product.template&view_type=form";
    private @FindBy(xpath = "//label[contains(text(),'Product Name')]/following-sibling::h1//input") WebElement productNameTextbox;
    private @FindBy(xpath = "//button[contains(@title,'Save record')]") WebElement saveButton;
    private @FindBy(xpath = "//span[contains(text(),'Update Quantity')]/parent::button") WebElement updateQuantityButton;
    private @FindBy(xpath = "//li[@class='breadcrumb-item active']/span") WebElement createdProductName;

    public CreateProductPage() {
        super();
    }

    //Nagigate to create product page
    public void navigateToCreateProductPage() {
        navigateToPath(CREATE_PRODUCT_PAGE_PATH);
    }

    //Input product name textbox
    public void inputProductNameTextbox(String inputValue) {
        waitAndSendKeys(productNameTextbox, inputValue);
    }

    //Input product name textbox
    public void clickSaveButton() {
        waitAndClick(saveButton);
    }

    public void clickUpdateQuantityButton() {
        waitUntilProductCreated();
        waitAndClick(updateQuantityButton);
    }

    public String createdProductName() {
        waitUntilProductCreated();
        return waitAndGetText(createdProductName);
    }

    private void waitUntilProductCreated() {
        waitHandler.waitForTextNotToBePresentInElement(createdProductName, "New");
    }

}
