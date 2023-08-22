package pageobjects.home;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pageobjects.BasePage;

public class LoginPage extends BasePage {

    private static final String LOGIN_PATH = "/web/login";
    @FindBy(id = "login")
    static WebElement emailTextbox;
    @FindBy(id = "password")
    static WebElement passwordTextbox;
    @FindBy(xpath = "//button[contains(text(),'Log in')]")
    static WebElement loginButton;

    public LoginPage() {
        super();
    }

    public static void navigateToLoginPage() {
        navigateToPath(LOGIN_PATH);
    }

    public static void inputEmailTextbox(String inputValue) {
        waitAndSendKeys(emailTextbox, inputValue);
    }

    public static void inputPasswordTextbox(String inputValue) {
        waitAndSendKeys(passwordTextbox, inputValue);
    }

    public static void clickLoginButton() {
        waitAndClick(loginButton);
    }

}
