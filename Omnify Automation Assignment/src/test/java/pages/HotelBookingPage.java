package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;
import java.util.List;


public class HotelBookingPage {

    private WebDriver driver;
    private final Logger logger = LoggerFactory.getLogger(HotelBookingPage.class);
    private WebDriverWait wait;
    private JavascriptExecutor js;

    public HotelBookingPage(WebDriver IDriver) {
        this.driver = IDriver;
        PageFactory.initElements(driver, this);
        wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        js = (JavascriptExecutor) driver;
    }

    @FindBy(xpath = "//img[@id='popupImage']//ancestor::div//button[@id='closeButton']")
    private WebElement closeSpecialOfferModal;

    @FindBy(css = "input[placeholder='Enter city, area or property name']")
    private WebElement citySearchBox;

    @FindBy(xpath = "//button[contains(@class,'next-button')]")
    private WebElement nextMonthArrow;

    @FindBy(xpath = "//div[@class='rmsGst']//button[text()='APPLY']")
    private WebElement roomsApplyButton;

    @FindBy(xpath = "//button[@data-testid='search-hotels']")
    private WebElement searchButton;

    @FindBy(xpath = "(//div[@data-testid='hotel-card'])[1]")
    private WebElement firstHotel;

    @FindBy(xpath = "(//button[text()='Reserve 1 Room'])[1]")
    private WebElement reserveRoomButton;

    public void handleLoginModal() {

            try {
                wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.id("wiz-iframe-intent")));

                closeSpecialOfferModal.click();
                logger.info("Closed the Special Offer Modal popup successfully");
            } catch (Exception e) {
                logger.info("No Special Offer Modal popup appeared... Proceeding further");
            }
    }

    public void verifyHotelsPage() {
        logger.info("Successfully landed on Hotels page {{}}", driver.getTitle());
    }


    private void selectDate(String targetMonthAndDay) {
        String targetMonth = targetMonthAndDay.split(" ")[0];

        while (!calendarShowsMonth(targetMonth)) {
            nextMonthArrow.click();
        }

        String datePath = String.format("//button[.//abbr[contains(@aria-label, '%s')]]", targetMonthAndDay);
        driver.findElement(By.xpath(datePath)).click();
        logger.info("Selected check In/Out date: {}", targetMonthAndDay);
    }

    private boolean calendarShowsMonth(String targetMonth) {

        String monthPath = String.format("//div[@class='react-calendar__navigation']//span[contains(text(), '%s')]", targetMonth);

        List<WebElement> months = driver.findElements(By.xpath(monthPath));

        return !months.isEmpty() && months.get(0).isDisplayed();

    }

    public void searchHotelsByCityAndDates(String city, String checkInDate, String checkoutDate) throws InterruptedException {
        citySearchBox.click();
        citySearchBox.clear();

        for (char c : city.toCharArray()) {
            citySearchBox.sendKeys(String.valueOf(c));
            Thread.sleep(150);
        }

        String citySuggestion = String.format("//div[@role='button'][.//p[2][contains(text(), 'City') and contains(text(), '%s')]]", city);
        driver.findElement(By.xpath(citySuggestion)).click();
        logger.info("Clicked on the given city from the suggestion");

        selectDate(checkInDate);
        selectDate(checkoutDate);

        Thread.sleep(5000);
        searchButton.click();

        logger.info("Clicked on search button!");
    }

    public void clickOnFirstHotelFromResults() {
        String originalWindow = driver.getWindowHandle();

        wait.until(ExpectedConditions.visibilityOf(firstHotel));
        firstHotel.click();

        for (String windowHandle : driver.getWindowHandles()) {
            if (!windowHandle.equals(originalWindow)) {
                driver.switchTo().window(windowHandle);
                break;
            }
        }

        logger.info("Clicked on first hotel from hotel listing page & Switched to first hotel page");

        wait.until(ExpectedConditions.elementToBeClickable(reserveRoomButton)).click();

    }



}

