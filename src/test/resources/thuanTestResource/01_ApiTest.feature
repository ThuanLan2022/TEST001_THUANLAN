Feature: GitHub API Repositories Test
  Verify repositories data from GitHub API for SeleniumHQ
@apiTest
  Scenario: Verify open issues, sorting, and watchers count
    Given Call the GitHub API for SeleniumHQ repositories
    When Retrieve all repositories
    Then Should see the total number of open issues
    And Should see repositories sorted by last updated date
    And Should see the repository with the most watchers
