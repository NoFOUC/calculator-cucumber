package calculator;

import java.util.List;

/** This class represents the arithmetic operation "-".
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
public final class Minus extends Operation
 {

  /**
   * Class constructor specifying a number of Expressions to subtract.
   *
   * @param elist The list of Expressions to subtract
   * @throws IllegalConstruction    If an empty list of expressions if passed as parameter
   * @see #Minus(List<Expression>,Notation)
   */
  public /*constructor*/ Minus(List<Expression> elist) throws IllegalConstruction {
  	this(elist, null);
  }

  /**
   * Class constructor specifying a number of Expressions to subtract,
   * as well as the Notation used to represent the operation.
   *
   * @param elist The list of Expressions to subtract
   * @param n The Notation to be used to represent the operation
   * @throws IllegalConstruction    If an empty list of expressions if passed as parameter
   * @see #Minus(List<Expression>)
   * @see Operation#Operation(List<Expression>,Notation)
   */
  public Minus(List<Expression> elist, Notation n) throws IllegalConstruction {
  	super(elist,n);
  	symbol = "-";
  	neutral = 0;
  }

    /**
     * The actual computation of the (binary) arithmetic subtraction of two integers
     * @param l The first integer
     * @param r The second integer that should be subtracted from the first
     * @return The integer that is the result of the subtraction
     */
  public MyNumber op(MyNumber l, MyNumber r)
  {
  	AbstractValue real = l.getValue().sub(r.getValue());

    AbstractValue imaginary = l.getImaginary().sub(r.getImaginary());
    return new MyNumber(real, imaginary);

  }

     @Override
     public MyNumber op(MyNumber l) {
         return l;
     }
 }
