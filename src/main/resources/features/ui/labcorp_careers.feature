Feature: LabCorp Careers Job Validation
  As a job seeker
  I want to verify job details on LabCorp website
  So that I can ensure the information is accurate

  Scenario: Verify job details for QA Automation position
    Given I navigate to LabCorp homepage
    When I click on Careers link
    And I search for "QA Test Automation Developer" position
    And I select the first matching position
    Then I should see the correct job details:
      | Field               | Value                                      |
      | Job Title           | QA Test Automation Developer               |
      | Job Location        | Burlington, North Carolina, United States  |
      | Job ID              | 23-XXXXX                                   |
      | Description Text    | "Develop and maintain automated test scripts using Selenium" |
      | Requirement1        | "5+ years of QA automation experience"     |
      | Requirement2        | "Strong knowledge of BDD frameworks"       |
    When I click on Apply Now button
    Then I should see the same job details on the application page
    When I click to return to job search
    Then I should be back to the search results page