Feature: Test Paypal Payment System

  Scenario: Authorized for paypal
    Given I have base url for test auth paypal
    When I have request to get access token with following data:
      | grant_type          | client_id        | client_secret        |
      | client_credentials  | {{client_id}}    | {{client_secret}}    |
    Then I should get success response message

    Scenario: Get user info
      Given I have valid base url to get user info
      When  I have get valid get request data
      Then  I should get valid success response

  Scenario: Generate Client Token
    Given I have base url for test auth paypal
    When I have request to get access token with following data:
      | grant_type |
      | client_credentials |
    Then I should get success response message
    Given I have valid base url to generate client token
    When I have valid request to generate client token
    Then I should get valid success response for client token


