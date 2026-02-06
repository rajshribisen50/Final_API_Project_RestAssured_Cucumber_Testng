Feature: Post API Testing

  Scenario: Get existing post by ID
    Given I have the base URL set for postsID
    When I GET the post with ID 1
    Then I should receive status code 200
    And the response should contain postId 1

  Scenario: Create a new post
    Given I have the base URL set for posts
    When I create a post with userId 1, title "API Automation", body "POST request using Rest Assured"
    Then I should receive status code 201
    And the response should contain title "API Automation"