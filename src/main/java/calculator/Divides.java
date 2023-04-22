package calculator;

import java.math.BigDecimal;
import java.util.List;

/** This class represents the arithmetic division operation "/".
 * The class extends an abstract superclass Operation.
 * Other subclasses of Operation represent other arithmetic operations.
 * @see Operation
 * @see Minus
 * @see Times
 * @see Plus
 * @see Factorial
 * @see General_Exponential
 * @see Modulo
 * @see PrimeNumbers
 * @see BiggerThan
 * @see LessThan
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

      if (b.equals(new RealValue(0))) {
          return new MyNumber(a);
      }


      return new MyNumber(a,b);
    }

    @Override
    public MyNumber op(MyNumber l) {
        return l;
    }
}
