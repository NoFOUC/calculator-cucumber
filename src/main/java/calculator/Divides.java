package calculator;

import java.math.BigDecimal;
import java.util.List;

/** This class represents the arithmetic division operation "/".
 * The class extends an abstract superclass Operation.
 * Other subclasses of Operation represent other arithmetic operations.
 * @see Operation
 * @see Plus
 * @see Minus
 * @see Times
 * @see Divides
 * @see Modulo
 * @see PrimeNumbers
 * @see BiggerThan
 * @see LessThan
 * @see General_Exponential
 * @see Sqrt
 * @see Inverse
 * @see Factorial
 * @see Ln
 * @see Cosinus
 * @see Sinus
 * @see Tan
 * @see Cot
 * @see ArcCos
 * @see ArcSin
 * @see ArcTan
 * @see ArcCot
 */
public final class Divides extends Operation
{

  /**
   * Class constructor specifying a number of Expressions to divide.
   *
   * @param elist The list of Expressions to divide
   * @throws IllegalConstruction    If an empty list of expressions if passed as parameter
   * @see #Divides(List<Expression>,Notation)
   */
  public /*constructor*/ Divides(List<Expression> elist) throws IllegalConstruction {
	this(elist, null);
  }

    /**
     * Class constructor specifying a number of Expressions to divide,
     * as well as the notation used to represent the operation.
     *
     * @param elist The list of Expressions to divide
     * @param n The Notation to be used to represent the operation
     * @throws IllegalConstruction  If an empty list of expressions if passed as parameter
     * @see #Divides(List<Expression>)
     * @see Operation#Operation(List<Expression>,Notation)
     */
  public Divides(List<Expression> elist, Notation n) throws IllegalConstruction {
	super(elist,n);
	symbol = "/";
	neutral = 1;
  }

    /**
     * The actual computation of the (binary) arithmetic division of two integers
     * @param l The first integer
     * @param r The second integer that should divide the first
     * @return The integer that is the result of the division
     */
  public MyNumber op(MyNumber l, MyNumber r)
    { AbstractValue a1 = l.getValue();
      AbstractValue b1 = l.getImaginary();
      AbstractValue a2 = r.getValue();
      AbstractValue b2 = r.getImaginary();
      AbstractValue a = a1.mul(a2).add(b1.mul(b2)).div(a2.mul(a2).add(b2.mul(b2)));
      AbstractValue b = b1.mul(a2).sub(a1.mul(b2)).div(a2.mul(a2).add(b2.mul(b2)));

        if (a instanceof RationalValue || a instanceof IntegerValue || b instanceof RationalValue || b instanceof IntegerValue) {
            return new MyNumber(a,b);
        }

      if (b instanceof RealValue && b.getRawValue() instanceof BigDecimal) {
          if (((BigDecimal) b.getRawValue()).compareTo(BigDecimal.ZERO) == 0) {
              b = new RealValue(BigDecimal.valueOf(0));
          }
      }

      return new MyNumber(a,b);
    }

    @Override
    public MyNumber op(MyNumber l) {
        return l;
    }
}
