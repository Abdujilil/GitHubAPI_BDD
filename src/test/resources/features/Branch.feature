Feature: Branch


  Scenario: create new branch and verify new branch information
    Given request for creating repo with "minimum" requirement
    And request for creating new branch
    Then the branch information matches expected

  @wip
  Scenario: rename a branch and verify new branch name
    Given request for creating repo with "minimum" requirement
    And request for creating new branch
    And request for updating branch name
    Then the branch information matches expected