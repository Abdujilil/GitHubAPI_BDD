Feature: Create user repos


  Scenario: create repo with minimum requirement
    Given request for creating repo with "minimum" requirement
    And retrieve created repo information
    Then verify the minimum repo information matches expected


  Scenario: create repo with partial information
    Given request for creating repo with "partial" requirement
    And retrieve created repo information
    Then verify the partial repo information matches expected


  Scenario: create repo with full requirement and verify basic repo information
    Given request for creating repo with "full" requirement
    And retrieve created repo information
    Then the basic repo information matches expected


  Scenario: create repo with full requirement and verify repo navigation tab settings
    Given request for creating repo with "full" requirement
    And retrieve created repo information
    Then the repo navigation tab settings matches expected


  Scenario: create repo with full requirement and verify repo pull request settings
    Given request for creating repo with "full" requirement
    And retrieve created repo information
    Then the repo pull request settings matches expected


  Scenario: create repo with full requirement and verify repo timestamps
    Given request for creating repo with "full" requirement
    And retrieve created repo information
    Then the repo timestamps matches expected


  Scenario: create repo with full requirement and verify repo owner information
    Given request for creating repo with "full" requirement
    And retrieve created repo information
    Then the repo owner information matches expected


  Scenario: create repo with full requirement and verify repo permissions settings
    Given request for creating repo with "full" requirement
    And retrieve created repo information
    Then the repo permissions settings matches expected


  Scenario: update repo name and verify the new repo name and timestamp
    Given request for creating repo with "minimum" requirement
    When request for updating repo name
    And retrieve created repo information
    Then the repo name and timestamp matches expected


  Scenario: delete repo and verify it is deleted
    Given request for creating repo with "minimum" requirement
    When request for deleting the repo
    Then verify the repo is deleted


  Scenario: transfer repo and verify it is transferred
    Given request for creating repo with "minimum" requirement
    When request for transferring the repo to "newOwnerName"
    Then verify the repo is transferred