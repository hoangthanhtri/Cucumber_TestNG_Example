package stepdefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.Assert;
import pageobjects.BasePage;
import pageobjects.home.HomePage;
import pageobjects.home.LoginPage;
import pageobjects.inventory.*;
import pageobjects.manufacturing.CreateOrderPage;
import pageobjects.manufacturing.ListOrdersPage;

public class SampleSteps extends BasePage {
    private LoginPage loginPage;


    public SampleSteps(LoginPage loginPage1) {
        this.loginPage = loginPage1;
    }
    @Given("I am on the login page")
    public void iAmOnTheLoginPage() {
        loginPage.navigateToLoginPage();
    }

    @When("I login with username {string} and password {string}")
    public void iLoginWithUsernameAndPassword(String email, String password) {
        System.out.println("");
    }
    

    @Then("I should see the error message {string}")
    public void iShouldSeeTheErrorMessage(String arg0) {
        Assert.assertTrue(false);
    }

    @And("I navigate to create product page")
    public void iNavigateToCreateProductPage() {
        System.out.println("");
    }

    @Then("I should see the welcome page")
    public void iShouldSeeTheWelcomePage() {
        Assert.assertTrue(true);
    }

}
