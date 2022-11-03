@TodoMvcTest
Feature: Test the Todo Mvc application

  Scenario: Verify adding tasks in Todo MVC and check the count
    Given I am in todo MVC page
    When I add the tasks in todoMVC page
      | Join standup call |
      | Complete training |
      | Retest the defect |
    Then Verify the count of toDoList added is "3"

  Scenario: Verify completing the added task and clear completed
    Given I am in todo MVC page
    When I add the tasks in todoMVC page
      | Join standup call |
      | Complete training |
      | Retest the defect |
    And I complete all active tasks
    Then Verify the count of toDoList added is "0"
    And Clear the completed tasks

  Scenario: Verify select all and deselect all button to mark the all the items completed/Active
    Given I am in todo MVC page
    When I add the tasks in todoMVC page
      | Task1 |
      | Task2 |
      | Task3 |
    Then I "select" all the items in todoLList by clicking select all button
    And I "deselect" all the items in todoLList by clicking select all button