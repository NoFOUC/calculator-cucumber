package calculator;

import java.util.List;

/**
 * This class represents the modulo operation "%".
 * The class extends an abstract superclass Operation.
 * Other subclasses of Operation represent other arithmetic operations.
 *
 * @see Operation
 * @see Minus
 * @see Times
 * @see Plus
 * @see Divides
 * @see General_Exponential
 * @see Factorial
 * @see PrimeNumbers
 * @see LessThan
 * @see BiggerThan
 */
public final class Modulo extends Operation {

    /**
     * Class constructor specifying a number of Expressions to modulo.
     *
     * @param elist The list of Expressions to modulo
     * @throws IllegalConstruction If an empty list of expressions if passed as parameter
     * @see #Modulo(List<Expression>,Notation)
     */
    public Modulo(List<Expression> elist) throws IllegalConstruction {
        this(elist, null);
    }

    /**
     * Class constructor specifying a number of Expressions to modulo,
     * as well as the Notation used to represent the operation.
     *
     * @param elist The list of Expressions to modulo
     * @param n     The Notation to be used to represent the operation
     * @throws IllegalConstruction If an empty list of expressions if passed as parameter
     * @see #Modulo(List<Expression>)
     * @see Operation#Operation(List<Expression>,Notation)
     */
    public Modulo(List<Expression> elist, Notation n) throws IllegalConstruction {
        super(elist, n);
        symbol = "%";
        neutral = 1;
    }

    /**
     * The actual computation of the modulo of two integers
     *
     * @param l The first integer
     * @param r The second integer that should be modulo from the first
     * @return The integer that is the result of the modulo
     */
    public MyNumber op(MyNumber l, MyNumber r) {
        boolean b = l.isComplex() || r.isComplex();
        if (b) {
            throw new IllegalArgumentException("Modulo of complex numbers is not defined");
        }
        else if (!(l.getValue() instanceof IntegerValue) ) {
            throw new IllegalArgumentException("Modulo of non integer values is not defined");
        }
        else {
            int left = ((IntegerValue) l.getValue()).getValue();
            int right = ((IntegerValue) r.getValue()).getValue();
            return new MyNumber(left % right);
        }
    }

    /**
     * The actual computation of the modulo of one integer
     *
     * @param l The integer
     */
    public MyNumber op(MyNumber l) {
        throw new IllegalArgumentException("Modulo of one number is not defined");
    }

}
