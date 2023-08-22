package pageobjects;

import driverfactory.DriverFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utils.WaitHandler;

import java.util.List;

import static utils.GlobalVars.BASE_URL;
import static utils.GlobalVars.DEFAULT_EXPLICIT_TIMEOUTS;

public class BasePage {
    public static WaitHandler waitHandler;

    @FindBy(xpath = "//a[@title='Home menu']")
    WebElement backToHomePageButton;

    public BasePage() {
        PageFactory.initElements(getDriver(), this);
        waitHandler = new WaitHandler(getDriver(), DEFAULT_EXPLICIT_TIMEOUTS);
    }

    public static WebDriver getDriver() {
        return DriverFactory.getWebDriver();
    }

    protected static void navigateToPath(String path) {
        getDriver().get(BASE_URL + path);
    }

    protected static void navigateToUrl(String url) {
        getDriver().get(url);
    }

    protected static void waitAndClick(WebElement element) {
        WebElement webElement = waitHandler.waitForElementToBeClickable(element);
        webElement.click();
    }

    protected static void waitAndSendKeys(WebElement element, String keys) {
        WebElement webElement = waitHandler.waitForVisibilityOf(element);
        webElement.clear();
        webElement.sendKeys(keys);
    }

    protected String waitAndGetText(WebElement element) {
        WebElement webElement = waitHandler.waitForVisibilityOf(element);
        return webElement.getText();
    }

    public void clickBackToHomePageButton() {
        waitAndClick(backToHomePageButton);
    }

    protected void selectHeaderDropdownListByName(WebElement dropdownButton, String selectValue) {
        waitAndClick(dropdownButton);
        By optionXpath = By.xpath("//button[contains(@aria-expanded,'true')]/following-sibling::div/a");
        List<WebElement> options = waitHandler.waitForVisibilityOfAllElements(getDriver().findElements(optionXpath));
        for (WebElement option : options
        ) {
            if (option.getText().compareToIgnoreCase(selectValue) == 0) {
                waitAndClick(option);
                break;
            }

        }
    }

    protected void searchAndSelectComboBoxByIndex(WebElement comboBoxTextbox, String searchValue) {
        WebElement comboBoxTextboxElement = waitHandler.waitForVisibilityOf(comboBoxTextbox);
        comboBoxTextboxElement.sendKeys(searchValue);
        By optionXpath = By.xpath("//ul[contains(@style,'display: block')]/li");
        List<WebElement> options = waitHandler.waitForVisibilityOfAllElements(getDriver().findElements(optionXpath));
        waitAndClick(options.get(0));
    }

    protected void searchAndSelectComboBoxByIndex(WebElement comboBoxTextbox, String searchValue, int index) {
        WebElement comboBoxTextboxElement = waitHandler.waitForVisibilityOf(comboBoxTextbox);
        comboBoxTextboxElement.sendKeys(searchValue);
        By optionXpath = By.xpath("//ul[contains(@style,'display: block')]/li");
        List<WebElement> options = waitHandler.waitForVisibilityOfAllElements(getDriver().findElements(optionXpath));
        waitAndClick(options.get(index));
    }

}
