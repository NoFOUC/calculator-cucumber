package calculator;

import java.util.List;

/** This class represents the arithmetic operation "<=".
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
 * @see PrimeNumbers
 * @see BiggerThan
 */
public final class LessThan extends Operation {

    /**
     * Class constructor specifying a number of Expressions to compare.
     *
     * @param elist The list of Expressions to subtract
     * @throws IllegalConstruction    If an empty list of expressions if passed as parameter
     * @see #LessThan(List<Expression>,Notation)
     */
    public LessThan(List<Expression> elist) throws IllegalConstruction {
        this(elist, null);
    }

    /**
     * Class constructor specifying a number of Expressions to verify if it's smaller,
     * as well as the Notation used to represent the operation.
     *
     * @param elist The list of Expressions to verify if it's the minimum
     * @param n The Notation to be used to represent the operation
     * @throws IllegalConstruction    If an empty list of expressions if passed as parameter
     * @see #LessThan(List<Expression>)
     * @see Operation#Operation(List<Expression>,Notation)
     */
    public LessThan(List<Expression> elist, Notation n) throws IllegalConstruction {
        super(elist, n);
        symbol = "<=";
        neutral = 0;
    }

    /**
     * The actual computation if the first integer is smaller than the second integer
     * @param l The first integer
     * @param r The second integer
     * @return 1 if the first integer is less than the second integer, 0 otherwise
     */
    @Override
    public MyNumber op(MyNumber l, MyNumber r) {

        //same as BiggerThan
        if (l.isComplex() || r.isComplex()) {
            throw new IllegalArgumentException("Comparison of complex numbers is not defined");
        }
        else{

            if (l.getValue() <= r.getValue()) {
                return new MyNumber(1);
            }
            return new MyNumber(0);
        }



    }

    @Override
    public MyNumber op(MyNumber l) {
        throw new IllegalArgumentException("Comparison of one number is not defined");
    }
}
