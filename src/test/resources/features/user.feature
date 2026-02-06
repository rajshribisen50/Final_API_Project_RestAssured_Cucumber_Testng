#Get,Post,get,put,delete for USER
Feature: USER API calls
  Scenario: Get Existing user result
    Given I have base URL for user get api
    When  I fetch all the user data from get call
    Then  I should be get success status code for use fetch

    #Create new USER
  Scenario: Create new user
    Given I have base URL for create user api
    When  I create new user with data:
    |id|username|email|password|
    |13 |veerbaccha  |veerlove@gmail.com|veer@124 |
    Then  I should get success response

    #Get a Single user
  Scenario: Get a user above created
    Given I have base URL for get created user
    When  I have to get user details for fetched id
    Then  I have to get success response for fetched data

    #Update user
  Scenario: Update user
    Given I have base URL to update user
    When  I have to update request body:
      |id|username|email|password|
      |13 |veerLove  |veerbaccha@gmail.com|veer@100 |
    Then  I have to get success response after update

    #Delete User
  Scenario:
    Given I have base URL for delete
    When  I have to delete request
    Then  I have to get success response after delete


