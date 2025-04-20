# Automated Hotel Booking Test Script

This repository contains an automated test script for a hotel booking application. The script is written in **Java** using **Selenium and Cucumber BDD**. The goal of the test is to simulate a user journey on the hotel booking website, including searching for hotels, applying a discount, and proceeding to checkout.

## Test Scenario

**1)** Open the hotel booking application.

**2)** Search for hotels in "New York" for the dates April 10 - April 15.

**3)** Select the first hotel from the search results.

**4)** Apply the coupon code "SUMMER25".

**5)** Verify that the discount is applied correctly.

**6)** Proceed to checkout but do not complete payment.

## ⚠️ Note:
Due to common bot protection mechanisms used by most websites, I was unable to complete the step of verifying the discount applied. The test script reaches the hotel listing page and performs steps 1 to 4, but verification of the discount will require additional steps, such as handling bot protection or CAPTCHA challenges.

## Limitations and Challenges
**Bot Protection:** Many websites employ bot protection, such as CAPTCHA or JavaScript challenges, which prevent automation tools like Selenium from functioning properly. As a result, the script was unable to verify the discount, as the website's protection mechanisms blocked the automation.

**Leaned** I tried my best to bypass the bot protection mechanisms used by most websites, but unfortunately, I wasn't able to do so. Despite this, I learned a lot while working on this project, and I am thankful for this assessment opportunity.

