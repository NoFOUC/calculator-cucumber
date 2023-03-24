package calculator;

import java.util.List;
import java.lang.Math;

/** This class represents the exponential operation "^".
 * The class extends an abstract superclass Operation.
 * Other subclasses of Operation represent other arithmetic operations.
 * @see Operation
 * @see Minus
 * @see Times
 * @see Plus
 * @see Divides
 * @see Factorial
 * @see Modulo
 * @see PrimeNumbers
 * @see BiggerThan
 * @see LessThan
 */
public final class General_Exponential extends Operation {

    /**
     * Class constructor specifying a number of Expressions to make exponential.
     *
     * @param elist The list of Expressions to make exponential
     * @throws IllegalConstruction    If an empty list of expressions if passed as parameter
     * @see #General_Exponential(List<Expression>,Notation)
     */
    public General_Exponential(List<Expression> elist) throws IllegalConstruction {
        this(elist, null);
    }

    /**
     * Class constructor specifying a number of Expressions to make exponential,
     * as well as the Notation used to represent the operation.
     *
     * @param elist The list of Expressions to make exponential
     * @param n The Notation to be used to represent the operation
     * @throws IllegalConstruction    If an empty list of expressions if passed as parameter
     * @see #General_Exponential(List<Expression>)
     * @see Operation#Operation(List<Expression>,Notation)
     */
    public General_Exponential(List<Expression> elist, Notation n) throws IllegalConstruction {
        super(elist, n);
        symbol = "^";
        neutral = 1;
    }

    /**
     * The actual computation of the exponential of two integers
     * @param l The first integer
     * @param r The second integer that should be exponential with the first
     * @return The integer that is the result of the exponential
     */
    public int op(int l, int r) {
        return (int) Math.pow(l, r);
    }

    /**
     * The actual computation of the exponential of one integer with the base e
     * @param l The integer
     * @return The integer that is the result of the exponential
     */
    public int op(int l) {
        return (int) Math.pow(Math.E, l);
    }
}

