#Create Customer
#Fetch All Customer
#Fetch Customer with id
#Edit Customer Details

Feature: Test all the customer API
  Scenario: Create a customer for Razorpay
    Given I have base url for create a customer
    When  I have to send post request with valid data:
    |name|email|contact|fail_existing|gstin|notes_key_1|notes_key_2
    |Veer|veer@gmail.com|8920202088|1|12ABCDE2356F7GH|Tea, Earl Grey, Hot|Tea, Earl Grey, Hot|
    Then  I have to get success response after post
