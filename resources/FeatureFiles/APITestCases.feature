@APITests
Feature: API TestCases

  @TC_007
  Scenario: end to end create user and retrieve user
    Given "john" and "jacob" of the user to be created
    When trigger request with method "POST" and endpoint "/api/users" and valid request body
    Then filter id generated in the response
    And validate name and job of the user
    Then trigger request with method "GET" and endpoint "/api/users/{id}"
    And validate user details in the response

  @TC_008
  Scenario: create user request with no request body
    When trigger request with method "POST" and endpoint "/api/users" and no request body
    Then validate the invalid response and response code "201"

  @TC_009
  Scenario: create user request with invalid request body
    When trigger request with method "POST" and endpoint "/api/users" and invalid json request
    Then validate the invalid response and response code "400"

  @TC_010
  Scenario: create user request with no request body
    When trigger request with method "POST" and endpoint "/api/users" and null name field
    Then validate the invalid response and response code "400"

  @TC_011
  Scenario: Retrieve user request with invalid id
    When trigger request with method "GET" and endpoint "/api/users"
    Then validate the invalid response and response code "400"

  @TC_012
  Scenario: Retrieve user request with invalid method
    When trigger request with invalid method "POST" and endpoint "/api/users/{id}"
    Then validate the invalid response and response code "403"
