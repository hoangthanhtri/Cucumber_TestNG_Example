package runners;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.testng.annotations.DataProvider;


        @CucumberOptions(
            features = {"src/test/features"},
            glue = {"stepdefinitions"},
            plugin = {"pretty", "com.epam.reportportal.cucumber.ScenarioReporter"
            },
            monochrome = true,
            dryRun = false,
            tags = "",
            snippets = CucumberOptions.SnippetType.CAMELCASE
    )
    public class MainRunner extends AbstractTestNGCucumberTests {
        @Override
        @DataProvider(parallel = false)
        public Object[][] scenarios() {
            return super.scenarios();
        }

    }

