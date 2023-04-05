@listEndpoints
Feature: list API details and respective response codes and body

  Background: User navigates to the website
  Given User navigates to "https://reqres.in/"

  @TC_001
  Scenario: Display the API endpoints and get the respective details
    When user filters the list of endpoints
    Then display the different request type and endpoints
    And validate the number of endpoints to be "15"

  @TC_002
  Scenario: Display the URI , responseCode and responseBody for each API endpoint
    When user filters the list of endpoints
    Then click on each endpoint and display all the request and response details
    And take screenshot of each API details
    And navigate to support page

  @TC_003
  Scenario: One time support by adding amount manually
    When user navigates to support page
    Then user enters the amount "5"
    And click on the support request button
    And validate the amount paid result

  @TC_004
  Scenario: One time support by adding amount through mouse clicks
    When user navigates to support page
    Then mouse hover to the amount input and clicks amount increment button four times
    And validate the amount selected is "4"
    Then mouse hover to the amount input and clicks amount decrement button one time
    And validate the amount selected is "3"
    And click on the support request button
    And validate the amount paid result

  @TC_005
  Scenario: Monthly support by selecting monthly payment plan
    When user navigates to support page
    Then user selects monthly support plan
    And click on the support request button
    And validate the monthly amount payment to be "$5.00"

  @TC_006
  Scenario: Display broken links on the screen
    When user filters the list of endpoints
    Then click on each endpoint and display all the request and response details
    And display if there are broken links

