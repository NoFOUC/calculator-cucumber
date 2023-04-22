package calculator;

import java.math.BigDecimal;

/**
 * MyNumber is a concrete class that represents arithmetic numbers,
 * which are a special kind of Expressions, just like operations are.
 *
 * @see Expression
 * @see Operation
 */
public abstract class AbstractValue {

  /**
   * Method for returning the raw value of the number
   * @return The raw value of the number
   */
  public abstract BigDecimal getRawValue();

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

  /**
   * Method for individualized configuration of the comparison operation
   *
   * @param other The other member of the operation
   * @return The result of the operation
   */

  public int comp(AbstractValue other) {
    return this.sub(other).getRawValue().compareTo(BigDecimal.ZERO);
  }

}