package calculator;

import java.util.List;

/** This class represents the factorial operation "!".
 * The class extends an abstract superclass Operation.
 * Other subclasses of Operation represent other arithmetic operations.
 * @see Operation
 * @see Minus
 * @see Times
 * @see Plus
 * @see Divides
 * @see General_Exponential
 * @see Modulo
 * @see PrimeNumbers
 * @see BiggerThan
 * @see LessThan
 */
public final class Factorial extends Operation {

    /**
     * Class constructor specifying a number of Expressions to factorial.
     *
     * @param elist The list of Expressions to factorial
     * @throws IllegalConstruction    If an empty list of expressions if passed as parameter
     * @see #Factorial(List<Expression>,Notation)
     */
    public Factorial(List<Expression> elist) throws IllegalConstruction {
        this(elist, null);
    }

    /**
     * Class constructor specifying a number of Expressions to factorial,
     * as well as the Notation used to represent the operation.
     *
     * @param elist The list of Expressions to factorial
     * @param n The Notation to be used to represent the operation
     * @throws IllegalConstruction    If an empty list of expressions if passed as parameter
     * @see #Factorial(List<Expression>)
     * @see Operation#Operation(List<Expression>,Notation)
     */
    public Factorial(List<Expression> elist, Notation n) throws IllegalConstruction {
        super(elist, n);
        symbol = "!";
        neutral = 0;
    }

    /**
     * The actual computation of the factorial of an integer
     * @param l The integer
     * @return The integer that is the result of the factorial
     */
    @Override
    public MyNumber op(MyNumber l) {
        if (l.isComplex()) {
            throw new IllegalArgumentException("Factorial of complex numbers is not defined");
        }
        else if (!(l.getValue() instanceof IntegerValue) ) {
            throw new IllegalArgumentException("Factorial of non integer values is not defined");
        }
        else{

            if (l.getValue().equals(new IntegerValue(0))) {
                return new MyNumber(1);
            } else if (l.getValue().equals(new IntegerValue(1))) {
                return new MyNumber(1);
            } else {
                return new MyNumber(l.getValue().mul(l.getValue().sub(new IntegerValue(1))));
            }
        }
    }

    /**
     * Factorial operation does not support two arguments
     * @param l The integer
     * @param r The integer
     * */
    public MyNumber op(MyNumber l, MyNumber r) {
        throw new UnsupportedOperationException("Factorial operation does not support two arguments");
    }
}
