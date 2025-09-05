Feature: OpenWeather city search
@openWeatherTest
  Scenario: Search Los Angeles and verify info
    Given Open OpenWeather website
    When Search for city "Los Angeles, US"
    Then Should see the city name "Los Angeles, US"
    And Should see the current date
    And Should see the temperature as a number
