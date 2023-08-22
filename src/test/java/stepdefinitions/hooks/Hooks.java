package stepdefinitions.hooks;

import io.cucumber.java.After;
import io.cucumber.java.AfterStep;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import static driverfactory.DriverFactory.cleanUpDriver;
import static driverfactory.DriverFactory.getWebDriver;
import static utils.DataGenerate.getRunDatetime;


public class Hooks {

    @Before
    public void setUp() {
        getWebDriver();

    }

    @AfterStep
    public void captureExceptionImage(Scenario scenario) {
        if (scenario.isFailed()){
            byte[] screenshot;
            String alertText = null;
            try{
                alertText = getWebDriver().switchTo().alert().getText();
            } catch (NoAlertPresentException e){
                getWebDriver().switchTo().defaultContent();
            }
            if (alertText == null){
                screenshot = ((TakesScreenshot) getWebDriver()).getScreenshotAs(OutputType.BYTES);
                scenario.attach(screenshot,"image/png", getRunDatetime());
            } else {
                scenario.attach("Alert message:" + alertText, "text/plain", "Alert" + getRunDatetime());
            }

        }


    }

    @After
    public void tearDown() {
        cleanUpDriver();
    }

}
