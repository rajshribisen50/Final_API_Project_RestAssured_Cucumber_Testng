Feature: Order API automation for paypal
  Scenario: Paypal Create Order
    Given I have base URL FOR Order Api
    When I have valid request payload for create order
      | intent        | return_url                   | cancel_url            | user_action | currency_code | value  |
      | CAPTURE       | https://developer.paypal.com | https://www.bing.com  | PAY_NOW     | USD           | 100.00 |

    Then  I should get success response message for paypal

