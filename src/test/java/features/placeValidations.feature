@Place
Feature: place apis
@AddPlace
  Scenario Outline: Add place
    Given add place payload with "<name>" "<language>" "<address>"
    When user calls "AddPlaceApi" with "post" http request
    Then api call is success with status code 200
    And "status" in response body is "OK"
    And "scope" in response body is "APP"
    And verify place id created maps to "<name>" using "GetPlaceApi"

    Examples: 
      | name   | language | address |
      | ceeide | english  | test    |
     # | hello | english  | langley beach    |
@DeletePlace
  Scenario: Delete Place
    Given deleteplace payload
    When user calls "DeletePlaceApi" with "post" http request
    Then api call is success with status code 200
    And "status" in response body is "OK"
