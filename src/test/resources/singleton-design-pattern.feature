Feature: Singleton Design Pattern
  Scenario: Only one instance of the Singleton class should be created
    Given I have not created an instance of the Singleton class
    When I create a new instance of the Singleton class
    Then I should get the same instance of the Singleton class every time I create a new instance

  Scenario: Multiple threads should not create multiple instances of the Singleton class
    Given I have not created an instance of the Singleton class
    When I create multiple instances of the Singleton class in different threads
    Then I should get only one instance of the Singleton class
