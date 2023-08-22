package driverfactory;

import org.openqa.selenium.Capabilities;
import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.safari.SafariOptions;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

import static utils.GlobalVars.DEFAULT_IMPLICIT_TIMEOUTS;

public class DriverFactory {


    private static WebDriver webDriver = null;
    //Declare a webDriver

    public static WebDriver getWebDriver() {

        if (webDriver == null) {
            webDriver = createDriver();
        }
        return webDriver;
    }

    //This method get run configuration
    private static WebDriver createDriver() {
        WebDriver driver = null;
        //Turn off Selenium logs
        Logger.getLogger("org.openqa.selenium").setLevel(Level.OFF);
        // Check the browser type and instantiate the appropriate WebDriver
        switch (getBrowserType()) {
            case "edge" -> {
                // set the edge driver
                setBrowserDriverPathBasedOnOS("msedgedriver");
                EdgeOptions edgeOptions = new EdgeOptions();
                edgeOptions.setPageLoadStrategy(PageLoadStrategy.NORMAL);
                if (isHeadless())
                    edgeOptions.addArguments("--headless");
                edgeOptions.addArguments("--no-sandbox");
                edgeOptions.addArguments("--disable-dev-shm-usage");
                driver = new EdgeDriver(edgeOptions);
                driver.manage().window().maximize();
            }
            case "chrome" -> {
                // set the chrome driver
                setBrowserDriverPathBasedOnOS("chromedriver");
                ChromeOptions chromeOptions = new ChromeOptions();
                chromeOptions.setPageLoadStrategy(PageLoadStrategy.NORMAL);

                if (isHeadless())
                    chromeOptions.addArguments("--headless");

                chromeOptions.addArguments("--no-sandbox");
                chromeOptions.addArguments("--disable-dev-shm-usage");

                driver = new ChromeDriver(chromeOptions);
                driver.manage().window().maximize();
            }
            case "firefox" -> {

                // set the firefox
                setBrowserDriverPathBasedOnOS("geckodriver");
                FirefoxOptions firefoxOptions = new FirefoxOptions();
                firefoxOptions.setPageLoadStrategy(PageLoadStrategy.NORMAL);

                if (isHeadless())
                    firefoxOptions.addArguments("--headless");
                firefoxOptions.addArguments("--no-sandbox");
                firefoxOptions.addArguments("--disable-dev-shm-usage");

                driver = new FirefoxDriver(firefoxOptions);
                driver.manage().window().maximize();
            }
            case "iphonexr" -> {
                //set the iphone xr on chrome
                setBrowserDriverPathBasedOnOS("chromedriver");
                ChromeOptions chromeOptions = new ChromeOptions();
                chromeOptions.setPageLoadStrategy(PageLoadStrategy.NORMAL);

                if (isHeadless())
                    chromeOptions.addArguments("--headless");

                chromeOptions.addArguments("--no-sandbox");
                chromeOptions.addArguments("--disable-dev-shm-usage");

                Map<String, String> mobileEmulation = new HashMap<>();
                mobileEmulation.put("deviceName", "iPhone XR");
                chromeOptions.setExperimentalOption("mobileEmulation", mobileEmulation);
                chromeOptions.addArguments("--remote-allow-origins=*");

                driver = new ChromeDriver(chromeOptions);
            }
            case "samsung" -> {
                //set the samsung on chrome
                setBrowserDriverPathBasedOnOS("chromedriver");
                ChromeOptions chromeOptions = new ChromeOptions();
                chromeOptions.setPageLoadStrategy(PageLoadStrategy.NORMAL);

                if (isHeadless())
                    chromeOptions.addArguments("--headless");

                chromeOptions.addArguments("--no-sandbox");
                chromeOptions.addArguments("--disable-dev-shm-usage");

                Map<String, String> mobileEmulation = new HashMap<>();
                mobileEmulation.put("deviceName", "Samsung Galaxy S20 Ultra");
                chromeOptions.setExperimentalOption("mobileEmulation", mobileEmulation);
                chromeOptions.addArguments("--remote-allow-origins=*");

                driver = new ChromeDriver(chromeOptions);
            }
            /*
            case "safari" -> {
                SafariOptions safariOptions = new SafariOptions();
                safariOptions.setUseTechnologyPreview(true);
                safariOptions.setCapability(CapabilityType.ACCEPT_INSECURE_CERTS, true);
                driver = new SafariDriver(safariOptions);
                driver.manage().window().maximize();
            }*/
            default -> throw new IllegalStateException("Unexpected value: " + getBrowserType());
        }

        driver.manage().timeouts().implicitlyWait(DEFAULT_IMPLICIT_TIMEOUTS, TimeUnit.SECONDS);
        driver.manage().timeouts().pageLoadTimeout(60, TimeUnit.SECONDS);

        // Return the created WebDriver instance
        return driver;
    }

    private static void setBrowserDriverPathBasedOnOS(String browserDriverFileName){
        String osName = System.getProperty("os.name").toLowerCase();
        String driverPath = System.getProperty("user.dir") + "/src/main/resources/drivers/";


        if (osName.contains("windows")){
            System.setProperty(setDriverName(browserDriverFileName),
                    driverPath + browserDriverFileName +".exe");
        } else if (osName.contains("mac")){
            System.setProperty(setDriverName(browserDriverFileName),
                    driverPath + browserDriverFileName);
        } else if (osName.contains("linux")){
            System.setProperty(setDriverName(browserDriverFileName),
                    driverPath + browserDriverFileName);
        }else {
            throw new IllegalArgumentException("Unsupported operating system: " + osName);
        }
    }

    private static String setDriverName(String browserDriverFileName){

        switch (browserDriverFileName) {
            case "msedgedriver" -> {
                return "webdriver.edge.driver";
            }
            case "chromedriver" -> {
                return "webdriver.chrome.driver";
            }
            case "geckodriver" -> {
                return "webdriver.gecko.driver";
            }
            default -> throw new IllegalStateException("Unexpected value: " + browserDriverFileName);
        }
    }

    //This method get run browser type from configuration
    public static String getBrowserType() {
        // Initialize a variable to store the browser type
        String browserType = null;

        // Get the browser type from the system property
        String browserTypeRemoteValue = System.getProperty("browserType");

        // Try to load the browser type from the properties file or use the system property value
        try {
            if (browserTypeRemoteValue == null || browserTypeRemoteValue.isEmpty()) {
                Properties properties = new Properties();
                FileInputStream file = new FileInputStream(
                        System.getProperty("user.dir") + "/config.properties");
                properties.load(file);
                browserType = properties.getProperty("browserType");
            } else {
                browserType = browserTypeRemoteValue;
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

        return browserType.toLowerCase().trim();
    }

    private static String getOS() {
        // Initialize a variable to store the browser type
        String os = null;

        // Get the browser type from the system property
        String browserTypeRemoteValue = System.getProperty("os");

        // Try to load the browser type from the properties file or use the system property value
        try {
            if (browserTypeRemoteValue == null || browserTypeRemoteValue.isEmpty()) {
                Properties properties = new Properties();
                FileInputStream file = new FileInputStream(
                        System.getProperty("user.dir") + "/config.properties");
                properties.load(file);
                os = properties.getProperty("os");
            } else {
                os = browserTypeRemoteValue;
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

        return os.toLowerCase().trim();
    }

    //This method get isHeadless
    private static boolean isHeadless() {
        boolean isHeadless = true;
        String isHeadlessRemoteValue = System.getProperty("isHeadless");
        try {
            if (isHeadlessRemoteValue == null || isHeadlessRemoteValue.isEmpty()) {
                Properties properties = new Properties();
                FileInputStream file = new FileInputStream(System.getProperty("user.dir") + "/config.properties");
                properties.load(file);
                isHeadless = Boolean.parseBoolean(properties.getProperty("isHeadless"));
            } else {
                isHeadless = Boolean.parseBoolean(isHeadlessRemoteValue.toLowerCase().trim());
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

        return isHeadless;
    }


    public static void cleanUpDriver() {
        if (webDriver != null) {
            webDriver.quit();
            webDriver = null;
        }
    }
}
