Feature: Get and Post API testing
  #Get Products
  Scenario: Get existing data by /products
    Given I have the base URL set for products
    When I GET the post with endpoint
    Then I should be receive status code 200

    #Create Products
  Scenario: Create new record inside products
    Given I have the base URL set for Createproducts
    When I create new product with data:
      | id | title  | price | description | category | image                     |
      | 0  | string | 0.1   | string      | string   | http://example.com        |
      | 1  | string1 | 0.2   | string1     | string1   | http://example1.com        |
    Then I should be receive statuscode 201

    #get a single product which id we get from above response
  Scenario: Get above created id and fetch data
    Given I have the base URL set for getid
    When  I will hit endoint with path parameter
    Then  I should be receive status code

    #update product

  Scenario: Get above product and update details
    Given I have the base URL set for update
    When  I update product where id is 0
      | id | title  | price | description | category | image                     |
      | 0  | string | 0.3   | stringupdate      | stringupd   | http://example123.com        |
    Then  I should receive successful status





