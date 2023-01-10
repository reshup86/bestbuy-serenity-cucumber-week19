Feature: Run CRUD operation on BestBuy API Application
  User should be able create, read, update and delete store details

  Scenario Outline: User can run successful CRUD operation on store details
    When    User creates new store record with "<name>", "<type>", "<address>", "<address2>", "<city>", "<state>", "<zip>", "<lat>", "<lng>", "<hours>"
    And     User can search new record using store "<name>"
    And     User can update new record using storeID, name, "<type>", "<address>", "<address2>", "<city>", "<state>", "<zip>", "<lat>", "<lng>", "<hours>"
    And     User can delete new record using storeID
    Then    User is able to run successful CRUD operation on store details
    Examples:
      | name            | type   | address         | address2 | city   | state | zip   | lat       | lng       | hours                                                                         |
      | Ohm Technology  | BigBox | 12 1st Street A | abc      | London | MN    | 25687 | 44.879314 | 93.077156 | Mon: 10-9; Tue: 10-9; Wed: 10-9; Thurs: 10-9; Fri: 10-9; Sat: 10-9; Sun: 10-8 |