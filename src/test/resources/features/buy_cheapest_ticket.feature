Feature: Buy the cheapest ticket
  AS a tourist
  I want to buy to bui the cheapest ticket from Medellin to Bogota
  To see what the minimum price for a ticket is

  Scenario: Make a reservation for a roundtrip from today to tomorrow of the route Bogota - Medellin
    Given I am browsing the internet
    When I Book the cheapest flight
    Then The value of the flight should be the same at the end of the transaction