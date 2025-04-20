package hooks;

import io.cucumber.java.Before;
import io.cucumber.java.After;
import utilities.BrowserFactory;
import utilities.ConfigReader;

import io.cucumber.java.Scenario;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.io.File;
import org.apache.commons.io.FileUtils;
import java.io.IOException;

public class Hooks {

    private static WebDriver driver;
    private static final Logger logger = LoggerFactory.getLogger(Hooks.class);

    @Before
    public void setUp(){
        try {

            String browserName = ConfigReader.getProperty("browserName");
            String testURL = ConfigReader.getProperty("testURL");
            driver = BrowserFactory.startApplication(browserName, testURL);
        } catch (Exception e) {
            logger.error("Failed to initialize the browser: {}", e.getMessage());
        }
    }

    @After
    public void tearDownTest(Scenario scenario) {
        if (scenario.isFailed()) {
            TakesScreenshot screenshot = (TakesScreenshot) driver;
            File source = screenshot.getScreenshotAs(OutputType.FILE);

            try {
                String screenshotPath = "./src/test/resources/Screenshots/" + scenario.getName().replaceAll("[^a-zA-Z0-9]", "_") + ".png";
                FileUtils.copyFile(source, new File(screenshotPath));
                logger.info("Screenshot saved at: {}", screenshotPath);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        try {
            if (driver != null) {
                driver.quit();
                logger.info("Browser closed successfully.");
            }
        } catch (Exception e) {
            logger.error("Error during browser teardown: {}", e.getMessage());
        }
    }

    public static WebDriver getDriver() {
        return driver;
    }
}


