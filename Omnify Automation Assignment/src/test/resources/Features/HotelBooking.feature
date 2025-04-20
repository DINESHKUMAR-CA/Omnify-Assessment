
Feature: Hotel booking with discount coupon

  Scenario Outline: Booking a hotel with coupon code and verifying the discount

    Given I open the hotel booking application
    When I search for hotels in "<City>" from "<CheckInDate>" to "<CheckoutDate>"
    And I select the first hotel from the search results
    And I apply the coupon code "<CouponCode>"
    Then I should see a message indicating whether the coupon is applied successfully or not
    And I proceed to checkout without completing the payment

    Examples:
      | City      | CheckInDate | CheckoutDate | CouponCode |
      | New Delhi  | June 10      | June 15    | SUMMER25   |