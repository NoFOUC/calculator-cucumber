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
public final class Inverse extends Operation
{

    /**
     * Class constructor specifying a number of Expressions to make the inverse of.
     *
     * @param elist The list of Expressions to make the inverse of
     * @throws IllegalConstruction    If an empty list of expressions if passed as parameter
     * @see #Inverse(List<Expression>,Notation)
     */
    public /*constructor*/ Inverse(List<Expression> elist) throws IllegalConstruction {
        this(elist, null);
    }

    /**
     * Class constructor specifying a number of Expressions to make the inverse of,
     * as well as the Notation used to represent the operation.
     *
     * @param elist The list of Expressions to make the inverse of
     * @param n The Notation to be used to represent the operation
     * @throws IllegalConstruction    If an empty list of expressions if passed as parameter
     * @see #Inverse(List<Expression>)
     * @see Operation#Operation(List<Expression>,Notation)
     */
    public Inverse(List<Expression> elist, Notation n) throws IllegalConstruction {
        super(elist,n);
        symbol = "inverse";
        neutral = 1;
    }

    /**
     * The actual computation of the (unary) arithmetic inverse
     * @param l The first integer
     * @return The integer that is the result of the subtraction
     */
    public MyNumber op(MyNumber l) {
        boolean b = l.isComplex();
        if (b) {
            throw new IllegalArgumentException("Inverse of complex numbers is not defined in this calculator");
        } else {
            BigDecimal left = (l.getValue()).getRawValue();
            return new MyNumber(new RealValue(new BigDecimal(Math.pow(left.doubleValue(), -1))));
        }
    }

    @Override
    public MyNumber op(MyNumber l, MyNumber r) throws IllegalArgumentException {
        throw new IllegalArgumentException("Inverse of two numbers is not defined");
    }
}
