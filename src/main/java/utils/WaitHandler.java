package utils;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class WaitHandler {

    public WebDriverWait wait;

    public WaitHandler(WebDriver driver, long timeout) {
        wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
    }

    public void waitForLoadedPage() {
        wait.until(webDriver -> ((JavascriptExecutor) webDriver).executeScript("return document.readyState").equals("complete"));
    }

    public WebElement waitForVisibilityOf(WebElement element) {
        return wait.until(ExpectedConditions.visibilityOf(element));
    }

    public List<WebElement> waitForVisibilityOfAllElements(List<WebElement> webElementList) {
        return wait.until(ExpectedConditions.visibilityOfAllElements(webElementList));
    }

    public void waitForTextNotToBePresentInElement(WebElement element, String text) {
        wait.until(ExpectedConditions.not(ExpectedConditions.textToBePresentInElement(element, text)));
    }


    public WebElement waitForElementToBeClickable(WebElement element) {
        return wait.until(ExpectedConditions.elementToBeClickable(element));
    }

}
