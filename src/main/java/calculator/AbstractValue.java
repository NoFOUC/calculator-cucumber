package calculator;

import visitor.Visitor;

/**
 * MyNumber is a concrete class that represents arithmetic numbers,
 * which are a special kind of Expressions, just like operations are.
 *
 * @see Expression
 * @see Operation
 */
public abstract class AbstractValue implements Expression {

  /**
   * accept method to implement the visitor design pattern to traverse arithmetic expressions.
   * Each number will pass itself to the visitor object to get processed by the visitor.
   *
   * @param v The visitor object
   */
  public void accept(Visitor v) {
    v.visit(this);
  }


  /**
   * The depth of a number expression is always 0
   *
   * @return The depth of a number expression
   */
  public int countDepth() {
    return 0;
  }

  /**
   * The number of operations contained in a number expression is always 0
   *
   * @return The number of operations contained in a number expression
   */
  public int countOps() {
    return 0;
  }

  /**
   * The number of numbers contained in a number expression is always 1
   *
   * @return The number of numbers contained in  a number expression
   */
  public int countNbs() {
    return 1;
  }

  // TODO: This may be too bulky in a long term, but as of now I really don't see a prettier solution for this mess

  /**
   * Method for individualized configuration of the addition operation
   *
   * @param other The other member of the operation
   * @return The result of the operation
   */
  public abstract AbstractValue add(AbstractValue other);

  /**
   * Method for individualized configuration of the subtraction operation
   *
   * @param other The other member of the operation
   * @return The result of the operation
   */
  public abstract AbstractValue sub(AbstractValue other);

  /**
   * Method for individualized configuration of the multiplication operation
   *
   * @param other The other member of the operation
   * @return The result of the operation
   */
  public abstract AbstractValue mul(AbstractValue other);

  /**
   * Method for individualized configuration of the division operation
   *
   * @param other The other member of the operation
   * @return The result of the operation
   */
  public abstract AbstractValue div(AbstractValue other);
}