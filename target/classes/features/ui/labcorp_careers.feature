Feature: LabCorp Careers Job Validation
  As a job seeker
  I want to verify job details on LabCorp website
  So that I can ensure the information is accurate

  @ui
  Scenario: Verify job details for QA Automation position
    Given I navigate to LabCorp homepage
    When I click on Careers link
    And I search for "QA Test Automation Developer" position
    And I select the first matching position
    Then I should see the correct job details:
      | Field            | Value                       |
      | Job Title        | True                        |
      | Job Location     | True                        |
      | Job ID           | True                        |
      | Description Text | Postman							       |
      | Qualifications   | B.Tech                      |
      | Responsibilities | web-based automation				 |
      | Requirement1     | BDD                         |
      | Requirement2     | Git					               |
      | Requirement3     | Selenium                    |
    When I click on Apply Now button
    Then I should see the same "Senior QA Automation Engineer" on the application page
    When I click to return to job search
    Then I should be back to the search results page
