package stepDefinitions;

import hooks.Hooks;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.And;
import org.openqa.selenium.WebDriver;
import pages.HotelBookingPage;

public class HotelBookingStep {

    private final WebDriver driver;
    private final HotelBookingPage hotelBookingPage;

    public HotelBookingStep() {
         this.driver = Hooks.getDriver();
        if (driver == null) {
            throw new RuntimeException("Driver not initialized. Ensure the Hooks setup is correct");
        }
        this.hotelBookingPage = new HotelBookingPage(driver);
    }

    @Given("^I open the hotel booking application$")
    public void I_open_the_hotel_booking_application() {
        hotelBookingPage.handleLoginModal();
        hotelBookingPage.verifyHotelsPage();
    }

    @When("^I search for hotels in \"([^\"]*)\" from \"([^\"]*)\" to \"([^\"]*)\"$")
    public void I_search_for_hotels_in_City_from_CheckInDate_to_CheckoutDate(String city, String checkInDate, String checkoutDate) throws InterruptedException {
        hotelBookingPage.searchHotelsByCityAndDates(city, checkInDate, checkoutDate);
    }

    @And("^I select the first hotel from the search results$")
    public void I_select_the_first_hotel_from_the_search_results() {
        hotelBookingPage.clickOnFirstHotelFromResults();
    }

    @And("^I apply the coupon code \"([^\"]*)\"$")
    public void I_apply_the_coupon_code_CouponCode(String couponCode) {

    }

    @Then("^I should see a message indicating whether the coupon is applied successfully or not$")
    public void I_should_see_a_message_indicating_whether_the_coupon_is_applied_successfully_or_not() {

    }

    @And("^I proceed to checkout without completing the payment$")
    public void I_proceed_to_checkout_without_completing_the_payment() {

    }


}
