package calculator;

import java.math.BigDecimal;
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
public final class Cosinus extends Operation
{
    /**
     * Class constructor specifying a number of Expressions to make the cosinus of.
     *
     * @param elist The list of Expressions to make the cosinus of
     * @throws IllegalConstruction    If an empty list of expressions if passed as parameter
     * @see #Cosinus(List<Expression>,Notation)
     */
    public /*constructor*/ Cosinus(List<Expression> elist) throws IllegalConstruction {
        this(elist, null);
    }

    /**
     * Class constructor specifying a number of Expressions to make the cosinus of,
     * as well as the Notation used to represent the operation.
     *
     * @param elist The list of Expressions to make the cosinus of
     * @param n The Notation to be used to represent the operation
     * @throws IllegalConstruction    If an empty list of expressions if passed as parameter
     * @see #Cosinus(List<Expression>)
     * @see Operation#Operation(List<Expression>,Notation)
     */
    public Cosinus(List<Expression> elist, Notation n) throws IllegalConstruction {
        super(elist,n);
        symbol = "cos";
        neutral = 0;
    }

    /**
     * The actual computation of the (unary) arithmetic cosinus
     * @param l The first integer
     * @return The integer that is the result of the subtraction
     */
    public MyNumber op(MyNumber l) {
        boolean b = l.isComplex();
        if (b) {
            throw new IllegalArgumentException("Cosinus of complex numbers is not defined in this calculator");
        } else {
            BigDecimal left = (l.getValue()).getRawValue();
            return new MyNumber(new RealValue(new BigDecimal(Math.cos(Math.toRadians(left.doubleValue()))))); //NOSONAR
        }
    }

    @Override
    public MyNumber op(MyNumber l, MyNumber r) throws IllegalArgumentException {
        throw new IllegalArgumentException("Cosinus of two numbers is not defined");
    }
}
