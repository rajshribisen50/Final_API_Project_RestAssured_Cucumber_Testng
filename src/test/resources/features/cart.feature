#Get,Post,get,put,delete
  Feature: Cart API calls
    Scenario: Get Existing cart result
      Given I have base URL for Cart get api
      When  I fetch all the cart data from get call
      Then  I should be get success status code



  Scenario: Add data via post call
    Given I have base URL for Cart Post API
    When I add new data for cart item:
      | cartId | userId | productId | title  | price | description | category | image                |
      | 10      | 19      | 120         | post string | 0.4   | stringresult      | stringcategory   | http://exampleimage.com   |
      | 11      | 20      | 121         | post string | 0.4   | stringresult      | stringcategory   | http://exampleimage.com   |

   # Then I should able to view added cart item and status should success

    Scenario:
      Given I have base Url for get created item
      When  I have to fetch data with response_id
      Then  I should be get success status

    Scenario:
      Given I have base Url for update item
      When  I have to update data for above get response id


