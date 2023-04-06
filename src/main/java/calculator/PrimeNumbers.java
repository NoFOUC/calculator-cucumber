package calculator;

import java.util.List;

/** This class represents the arithmetic operation "PRIME".
 * The class extends an abstract superclass Operation.
 * Other subclasses of Operation represent other arithmetic operations.
 * @see Operation
 * @see Plus
 * @see Times
 * @see Divides
 * @see Factorial
 * @see General_Exponential
 * @see Modulo
 * @see Minus
 * @see BiggerThan
 * @see LessThan
 */
public final class PrimeNumbers extends Operation {

    /**
     * Class constructor specifying a number of Expressions to PRIME.
     *
     * @param elist The list of Expressions to PRIME
     * @throws IllegalConstruction    If an empty list of expressions if passed as parameter
     * @see #PrimeNumbers(List<Expression>,Notation)
     */
    public PrimeNumbers(List<Expression> elist) throws IllegalConstruction {
        this(elist, null);
    }

    /**
     * Class constructor specifying a number of Expressions to PRIME,
     * as well as the Notation used to represent the operation.
     *
     * @param elist The list of Expressions to PRIME
     * @param n The Notation to be used to represent the operation
     * @throws IllegalConstruction    If an empty list of expressions if passed as parameter
     * @see #PrimeNumbers(List<Expression>)
     * @see Operation#Operation(List<Expression>,Notation)
     */
    public PrimeNumbers(List<Expression> elist, Notation n) throws IllegalConstruction {
        super(elist, n);
        symbol = "PRIME";
        neutral = 2;
    }

    /**
     * The actual computation of the PRIME of an integer
     * @param l The integer
     * @return The integer that is the result of the PRIME
     */
    public MyNumber op(MyNumber l) {

        if (l.isComplex()) {
            throw new IllegalArgumentException("PRIME of complex numbers is not defined");
        }
        else {

            for (int i = 2; i * i <= l.getValue(); i++) {
                if (l.getValue() % i == 0) {
                    return new MyNumber(0);
                }
            }
            return new MyNumber(1);
        }
    }

    /**
     * PRIME operation does not support two arguments
     * @param l The integer
     * @param r The integer
     */
    public MyNumber op(MyNumber l, MyNumber r) {
        throw new UnsupportedOperationException("PRIME operation does not support two arguments");
    }
}

