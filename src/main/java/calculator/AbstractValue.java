package calculator;

import java.math.BigDecimal;

/**
An abstract generalized representation of a number serving
 as a baseline for concrete implementations to be stored in MyNumber

 @see IntegerValue
 @see RationalValue
 @see RealValue

 @see MyNumber
 */
public abstract class AbstractValue {

  protected static int globalContractionLimit = 6;


  /**
   * Method for returning the raw value of the number
   *
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

  /**
   * Method for individualized configuration of the comparison operation
   *
   * @param other The other member of the operation
   * @return The result of the comparison in the same format as java's compare() methods
   */
  public int comp(AbstractValue other) {
    return this.sub(other).getRawValue().compareTo(BigDecimal.ZERO);
  }

    //methods that return a RealValue and traduct IntegerValue to RealValue
    public abstract RealValue toReal() throws IllegalConstruction;

    public static void setGlobalContractionLimit(int breakpoint) {
    globalContractionLimit = breakpoint;
  }

}