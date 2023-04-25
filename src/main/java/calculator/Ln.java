package calculator;

import java.math.BigDecimal;
import java.util.List;

/** This class represents the arithmetic operation "log".
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
public final class Ln extends Operation
{

    /**
     * Class constructor specifying a number of Expressions to logarithm.
     *
     * @param elist The list of Expressions to logarithm
     * @throws IllegalConstruction    If an empty list of expressions if passed as parameter
     * @see #Ln(List<Expression>,Notation)
     */
    public /*constructor*/ Ln(List<Expression> elist) throws IllegalConstruction {
        this(elist, null);
    }

    /**
     * Class constructor specifying a number of Expressions to logarithm,
     * as well as the Notation used to represent the operation.
     *
     * @param elist The list of Expressions to logarithm
     * @param n The Notation to be used to represent the operation
     * @throws IllegalConstruction    If an empty list of expressions if passed as parameter
     * @see #Ln(List<Expression>)
     * @see Operation#Operation(List<Expression>,Notation)
     */
    public Ln(List<Expression> elist, Notation n) throws IllegalConstruction {
        super(elist,n);
        symbol = "ln";
        neutral = 0;
    }

    /**
     * The actual computation of the (unary) arithmetic logarithm
     * @param l The first integer
     * @return The integer that is the result of the subtraction
     */
    public MyNumber op(MyNumber l) {
        boolean b = l.isComplex();
        if (b) {
            throw new IllegalArgumentException("Logarithm of complex numbers is not defined in this calculator");
        } else {
            BigDecimal left = (l.getValue()).getRawValue();
            return new MyNumber(new RealValue(new BigDecimal(Math.log(left.doubleValue()))));
        }
    }

    @Override
    public MyNumber op(MyNumber l, MyNumber r) throws IllegalArgumentException {
        throw new IllegalArgumentException("Logarithm of two numbers is not defined");
    }
}