#Author: your.email@your.domain.com
#Keywords Summary :
#Feature: List of scenarios.
#Scenario: Business rule through list of steps with arguments.
#Given: Some precondition step
#When: Some key actions
#Then: To observe outcomes or validation
#And,But: To enumerate more Given,When,Then steps
#Scenario Outline: List of steps for data-driven as an Examples and <placeholder>
#Examples: Container for s table
#Background: List of steps run before each of the scenarios
#""" (Doc Strings)
#| (Data Tables)
#@ (Tags/Labels):To group Scenarios
#<> (placeholder)
#""
## (Comments)
#Sample Feature Definition Template
@tag
Feature: Validating Pet API
  
  @AddPet
  
    Scenario Outline: Successfully add a new pet with valid details 
    Given I have a valid pet payload with <Id> "<Name>" "<Status>"
    When user sends "AddPetAPI" with "post" http request
    Then the response status code should be "200"
    And the response should contain the "name" and "<Name>"
    
   Examples:
   
  | Id | Name   | Status    |
  | 10 | doggie | available |
    
    
  @GetPet
  Scenario Outline: Successfully retrieve a pet by ID
    Given I have a valid pet ID <Id>
    When user sends "GetPetAPI" with "get" http request
    Then the response status code should be "200"
    And the response should contain the "name" and "<Name>"

    Examples:
      | Id | Name   |
      | 10 | doggie |

  @UpdatePet
Scenario Outline: Successfully update an existing pet
  Given a pet with ID <Id> exists with name "<Name>" and status "<Status>"
  And I have a valid pet payload with <Id> "<UpdatedName>" "<UpdatedStatus>" for update
  When user sends "UpdatePetAPI" with "put" http request
  Then the response status code should be "200"
  And the response should contain the "name" and "<UpdatedName>"

Examples:
  | Id | Name   | Status    | UpdatedName | UpdatedStatus |
  | 10 | doggie | available | dogzilla    | pending       |

  
    
   @DeletePet
  Scenario Outline: Verify if delete pet functionality is working
  Given I have a valid pet ID <Id>
  When user sends "DeletePetAPI" with "delete" http request
  Then the response status code should be "200"

  Examples:
    | Id  |
    | 10  |