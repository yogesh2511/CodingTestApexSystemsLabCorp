@api
Feature: API Request Validation
  As an API consumer
  I want to validate API responses
  So that I can ensure data integrity

  Scenario: Validate Get request response
  	When I send a GET request to "/sample-request?author=beeceptor"
    Then the response should have valid path
    Then the response should have valid ip
    Then the response should contain headers:
      | Header Name     | Expected Value                          |
      | Host            | echo.free.beeceptor.com                 |
      | User-Agent      | Apache-HttpClient/4.5.13 (Java/17.0.12) |
      | Accept          | */*                                     |
      | Accept-Encoding | gzip,deflate                            |


  