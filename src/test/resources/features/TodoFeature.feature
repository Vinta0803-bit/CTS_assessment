Feature: Users should be able to manage todos effectively

  Background:
    Given The user is logged in to todo application

  Scenario Outline: Add new todo task

    When The user adds <new_task> to todo list
    Then The task lists should be updated with the <new_task>
    And close the browser
    Examples:
      | new_task            |
      | check task addition |


  Scenario Outline: Complete a todo task

    Given Below tasks are added todo list
      | Task                    |
      | check task addition     |
      | check task completion   |
      | check task manipulation |
      | check task filters      |
    When The user clicks the completion checkbox for <task>
    Then The <task> is completed
    And close the browser

    Examples:
      | task                  |
      | check task completion |

  Scenario Outline: Edit a todo task

    Given The <task> is added to todo list
    When The user edits <task> to <new_task>
    Then The task lists should be updated with the <new_task>
    And close the browser
    Examples:
      | task       |  | new_task                |
      | check task |  | check task manipulation |

  Scenario Outline: Filter the todo tasks

    Given Below tasks are added todo list and few are marked as completed
      | Task                    |
      | check task addition     |
      | check task completion   |
      | check task manipulation |
      | check task filters      |

    When The user clicks the <filter> filter
    Then The tasks should be <filter>
    And close the browser

    Examples:
      | filter    |
      | Completed |
      | Active    |
      | All       |

  Scenario: Clear Completed todo task

    Given Below tasks are added todo list and few are marked as completed
      | Task                    |
      | check task addition     |
      | check task completion   |
      | check task manipulation |
      | check task filters      |
    When The user clicks the clear completed
    Then The active todo tasks should be listed ony
    And close the browser

