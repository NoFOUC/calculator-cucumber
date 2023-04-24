Feature: Integer Arithmetic Expressions
  This feature provides a range of scenarios corresponding to the
  intended external behaviour of arithmetic expressions on integers.

  # This is just a comment.
  # You can start with a Background: that will be run before executing each scenario.

  Background:
    Given I initialise a calculator

  # Each scenario can be seen as a test that can be executed with JUnit,
  # provided that each of the steps (Given, When, And and Then) are
  # implemented in a Java mapping file (CalculatorSteps.Java)

  Scenario: Adding two integer numbers
    Given an integer operation '+'
    When I provide a first number 4
    And I provide a second number 5
    Then the operation evaluates to 9

  Scenario: Adding two complex numbers
    Given a complex operation '+'
    When I provide a first complex number 4+2i
    And I provide a second complex number 5+2i
    Then the complex operation evaluates to 9+4i

  Scenario: Adding two rational numbers
    Given a rational operation '+'
    When I provide a first rational number 4/2
    And I provide a second rational number 5/2
    Then the rational operation evaluates to 9/2

  Scenario: Adding two complex rational numbers
    Given a complex rational operation '+'
    When I provide a first complex_rational number 4/2+2/3i
    And I provide a second complex_rational number 5/2+2/2i
    Then the complex_rational operation evaluates to 9/2+5/3i

  Scenario: Subtracting two integer numbers
    Given an integer operation '-'
    When I provide a first number 7
    And I provide a second number 5
    Then the operation evaluates to 2

  Scenario: Subtracting two complex numbers
    Given a complex operation '-'
    When I provide a first complex number 7+3i
    And I provide a second complex number 5+5i
    Then the complex operation evaluates to 2+-2i

  Scenario: Subtracting two rational numbers
    Given a rational operation '-'
    When I provide a first rational number 4/2
    And I provide a second rational number 5/2
    Then the rational operation evaluates to 1/-2

  Scenario: Subtracting two complex rational numbers
    Given a complex rational operation '-'
    When I provide a first complex_rational number 4/2+2/3i
    And I provide a second complex_rational number 5/2+2/2i
    Then the complex_rational operation evaluates to 1/-2+1/-3i

  Scenario: Multiplying two integer numbers
    Given an integer operation '*'
    When I provide a first number 7
    And I provide a second number 5
    Then the operation evaluates to 35

  Scenario: Multiplying two complex numbers
    Given an integer operation '*'
    When I provide a first complex number 7+3i
    And I provide a second complex number 5+5i
    Then the complex operation evaluates to 20+50i

  Scenario: Multiplying two rational numbers
    Given a rational operation '*'
    When I provide a first rational number 4/2
    And I provide a second rational number 5/2
    Then the rational operation evaluates to 20/4

  Scenario: Multiplying two complex rational numbers
    Given a complex rational operation '*'
    When I provide a first complex_rational number 4/2+2/3i
    And I provide a second complex_rational number 5/2+2/2i
    Then the complex_rational operation evaluates to 13/3+22/6i

  Scenario: Dividing two integer numbers
    Given an integer operation '/'
    When I provide a first number 7
    And I provide a second number 5
    Then the operation evaluates to 1

  Scenario: Dividing two complex numbers
    Given an integer operation '/'
    When I provide a first complex number 7+3i
    And I provide a second complex number 5+5i
    Then the complex operation evaluates to 1+0i

  Scenario: Dividing two rational numbers
    Given a rational operation '/'
    When I provide a first rational number 4/2
    And I provide a second rational number 5/2
    Then the rational operation evaluates to 8/10

  Scenario: Dividing two complex rational numbers
    Given a complex rational operation '/'
    When I provide a first complex_rational number 4/2+2/3i
    And I provide a second complex_rational number 5/2+2/2i
    Then the complex_rational operation evaluates to 68/87+4/-87i

  Scenario: Modulo of two integer numbers
    Given an integer operation '%'
    When I provide a first number 7
    And I provide a second number 5
    Then the operation evaluates to 2

  Scenario: Exponentiation of a integer numbers
    Given an integer operation '^'
    When I provide a first number 7
    Then the operation evaluates to 1096.6331584284583

  Scenario: Factorial of an integer number
    Given an integer operation '!'
    When I provide a first number 5
    Then the operation evaluates to 120

  Scenario: Factorial of 0 is 1
    Given an integer operation '!'
    When I provide a first number 0
    Then the operation evaluates to 1

  Scenario: Factorial of 1 is 1
    Given an integer operation '!'
    When I provide a first number 1
    Then the operation evaluates to 1

  Scenario: Check if a number is prime
    Given an integer operation 'PRIME'
    When I provide a first number 5
    Then the operation evaluates to 1

  Scenario: Check if a number is not prime
    Given an integer operation 'PRIME'
    When I provide a first number 6
    Then the operation evaluates to 0

  Scenario: Printing the sum of two integer numbers
    Given the sum of two numbers 8 and 6
    Then its INFIX notation is ( 8 + 6 )
    And its PREFIX notation is + (8, 6)
    And its POSTFIX notation is (8, 6) +

  Scenario: Printing the sum of two complex numbers
    Given the sum of two complex numbers 8+2i and 6+3i
    Then its INFIX notation is ( 8 + 2i + 6 + 3i )
    And its PREFIX notation is + (8 + 2i, 6 + 3i)
    And its POSTFIX notation is (8 + 2i, 6 + 3i) +

  Scenario: Printing the sum of two rational numbers
    Given the sum of two rational numbers 8/3 and 6/7
    Then its INFIX notation is ( (8/3) + (6/7) )
    And its PREFIX notation is + ((8/3), (6/7))
    And its POSTFIX notation is ((8/3), (6/7)) +

  Scenario: Printing the sum of two complex rational numbers
    Given the sum of two complex rational numbers 8/3+2/3i and 6/7+3/2i
    Then its INFIX notation is ( (8/3) + (2/3)i + (6/7) + (3/2)i )
    And its PREFIX notation is + ((8/3) + (2/3)i, (6/7) + (3/2)i)
    And its POSTFIX notation is ((8/3) + (2/3)i, (6/7) + (3/2)i) +



  # This is an example of a scenario in which we provide a list of numbers as input.
  # (In fact, this is not entirely true, since what is given as input is a table of
  # strings. In this case, the table is of dimension 1 * 3 (1 line and three columns).
  Scenario: Evaluation arithmetic operations over a list of integer numbers
    Given the following list of integer numbers
      | 8 | 2 | 2 |
    Then the sum is 12
    And the product is 32
    And the difference is 4
    And the quotient is 2

  Scenario: Evaluation arithmetic operations over a list of complex numbers
    Given the following list of complex numbers
      | 8+2i | 2+8i | 5+5i |
    Then the sum is 15 + 15i
    And the product is -340 + 340i
    And the difference is 1 + -11i
    And the quotient is 0

  Scenario: Evaluation arithmetic operations over a list of rational numbers
    Given the following list of rational numbers
      | 8/2 | 2/8 | 5/5 |
    #the the sum of the three is
    Then the sum is 40/8
    And the product is 20/80
    And the difference is 0
    And the quotient is 1

  Scenario: Evaluation arithmetic operations over a list of complex rational numbers
    Given the following list of complex rational numbers
      | 8/2+2/3i | 2/8+3/2i | 5/5+5/5i |
    Then the sum is 40/8+10/3i
    And the product is 20/80+20/30i
    And the difference is 0
    And the quotient is 1

  # A scenario outline (or template) is a scenario that is parameterised
  # with different values. The outline comes with a set of examples.
  # The scenario will be executed with each of the provided inputs.
  Scenario Outline: Adding two integer numbers
    Given an integer operation '+'
    When I provide a first number <n1>
    And I provide a second number <n2>
    Then the operation evaluates to <result>

    Examples:
      |n1|n2|result|
      |4|5|9|
      |5|3|8|

  Scenario Outline: Dividing two integer numbers
    Given an integer operation '/'
    When I provide a first number <n1>
    And I provide a second number <n2>
    Then the operation evaluates to <result>

    Examples:
      |n1|n2|result|
      |35|5|7|
      |7|5|1|
      |5|7|0|

  Scenario Outline: Evaluating arithmetic operations with two integer parameters
    Given an integer operation <op>
    When I provide a first number <n1>
    And I provide a second number <n2>
    Then the operation evaluates to <result>

    Examples:
      | op  |n1|n2|result|
      | "+" | 4| 5|     9|
      | "-" | 8| 5|     3|
      | "*" | 7| 2|    14|
      | "/" | 6| 2|     3|
