package testRunner;

import io.cucumber.testng.CucumberOptions;
import io.cucumber.testng.AbstractTestNGCucumberTests;

@CucumberOptions(
        features = "src/test/resources/Features/HotelBooking.feature",
        glue = {"stepDefinitions", "hooks"},
        monochrome = true
)

public class TestRunner extends AbstractTestNGCucumberTests {

}
