Feature: Sample search

  Scenario Outline: Basic search scenario
    Given I am on the "Search" page
    When I enter "<Destination>" text into the "Destination" field
    And click on the "Today's Date" element
    And click on the "<Type>" element
    And click on the "Search" button
    Then I should see the "Search Results" screen
    And the "Title" field has "<Destination>" text

    Examples: 
      | Destination | Type     |
      | Leeds       | Business |
      | Manchester  | Leisure  |
