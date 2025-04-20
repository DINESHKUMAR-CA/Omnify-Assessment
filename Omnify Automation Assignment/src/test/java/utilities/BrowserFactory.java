package utilities;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;

public class BrowserFactory {

    public static WebDriver driver;
    private static final Logger logger = LoggerFactory.getLogger(BrowserFactory.class);

    public static WebDriver startApplication(String browserName, String testUrl) {

        if (browserName.equalsIgnoreCase("chrome")) {
            ChromeOptions options = new ChromeOptions();

            // Spoof user-agent and disable automation flags
            options.addArguments("--user-agent=Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/135.0.7049.96 Safari/537.36");
            options.addArguments("--disable-blink-features=AutomationControlled");
            options.addArguments("--no-sandbox");
            options.addArguments("--disable-dev-shm-usage");
            options.addArguments("--disable-gpu");
            options.addArguments("--start-maximized");
            // Hide WebDriver property
            options.setExperimentalOption("excludeSwitches", new String[]{"enable-automation"});
            options.addArguments("disable-infobars");

            driver = new ChromeDriver(options);

        } else if (browserName.equalsIgnoreCase("Edge")) {
            driver = new EdgeDriver();
            logger.info("Initialized EdgeDriver");

        } else if (browserName.equalsIgnoreCase("firefox")) {
            driver = new FirefoxDriver();
            logger.info("Initialized FirefoxDriver");

        } else if (browserName.equalsIgnoreCase("safari")) {
            driver = new SafariDriver();
            logger.info("Initialized SafariDriver");

        } else {
            logger.error("This browser isn’t compatible. Please use Chrome, Edge, Firefox, or Safari");
            throw new RuntimeException("Unsupported browser: " + browserName);
        }

        logger.info("Browser Started Successfully");
        ((JavascriptExecutor) driver).executeScript("Object.defineProperty(navigator, 'webdriver', {get: () => undefined})");

        driver.get(testUrl);
        driver.manage().deleteAllCookies();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        logger.info("Opened URL: {}", testUrl);

        return driver;
    }
}
