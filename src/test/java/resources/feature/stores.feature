Feature: Testing all the Stores Endpoints of the BeatBuy API application

  Background: User is on homepage of stores endpoint of Best Buy API Playground application
    Given User is on stores page endpoint

  Scenario: Check if the Stores data can be accessed by user
    When User sends a GET request to the stores endpoint
    Then User must get back a valid status response code 200 from stores endpoint

  Scenario: Check if user can CREATE a new store on stores endpoint
    When User sends a POST request with a valid payload to the stores endpoint
    Then User must get back a valid status response code 201 from create stores endpoint

  Scenario: Check if user can READ a newly created store on stores endpoint
    When User send a GET request to newly created store with store ID
    Then User must get back a valid status response code 201 from stores endpoint
    And User should verify if the store is created with correct details

  Scenario: Check if user can UPDATE a newly created store on stores endpoint
    When User send a PUT request to newly created store with store ID
    Then User must get back a valid status response code 201 from stores endpoint
    And User should verify if the stores details are updated with correct details

  Scenario: Check if user can delete a newly created store on stores end point
    When User send a DELETE request to newly created store with store ID
    Then User must get back a valid status response code 201 from stores endpoint
    And User should verify if the store is deleted