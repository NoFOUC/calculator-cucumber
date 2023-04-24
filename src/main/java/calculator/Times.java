package calculator;

import java.math.BigDecimal;
import java.util.List;

/** This class represents the arithmetic multiplication operation "*".
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
public final class Times extends Operation
 {
  /**
   * Class constructor specifying a number of Expressions to multiply.
   *
   * @param elist The list of Expressions to multiply
   * @throws IllegalConstruction    If an empty list of expressions if passed as parameter
   * @see #Times(List<Expression>,Notation)
   */
  public /*constructor*/ Times(List<Expression> elist) throws IllegalConstruction {
  	this(elist, null);
  }

  /**
   * Class constructor specifying a number of Expressions to multiply,
   * as well as the Notation used to represent the operation.
   *
   * @param elist The list of Expressions to multiply
   * @param n The Notation to be used to represent the operation
   * @throws IllegalConstruction    If an empty list of expressions if passed as parameter
   * @see #Times(List<Expression>)
   * @see Operation#Operation(List<Expression>,Notation)
   */
  public Times(List<Expression> elist, Notation n) throws IllegalConstruction {
  	super(elist,n);
  	symbol = "*";
  	neutral = 1;
  }

  /**
   * The actual computation of the (binary) arithmetic multiplication of two integers
   * @param l The first integer
   * @param r The second integer that should be multiplied with the first
   * @return The integer that is the result of the multiplication
   */
  public MyNumber op(MyNumber l, MyNumber r)
    {
        AbstractValue a1 = l.getValue();
        AbstractValue b1 = l.getImaginary();
        AbstractValue a2 = r.getValue();
        AbstractValue b2 = r.getImaginary();

        AbstractValue a = a1.mul(a2).sub(b1.mul(b2));
        AbstractValue b = a1.mul(b2).add(b1.mul(a2));

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
