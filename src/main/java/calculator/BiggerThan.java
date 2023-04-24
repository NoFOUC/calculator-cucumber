package calculator;

import java.util.List;

/** This class represents the arithmetic operation ">=".
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
 * @see LessThan
 */
public final class BiggerThan extends Operation {

    /**
     * Class constructor specifying a number of Expressions to compare.
     *
     * @param elist The list of Expressions to compare
     * @throws IllegalConstruction    If an empty list of expressions if passed as parameter
     * @see #BiggerThan(List<Expression>,Notation)
     */
    public BiggerThan(List<Expression> elist) throws IllegalConstruction {
        this(elist, null);
    }

    /**
     * Class constructor specifying a number of Expressions to verify if it's bigger,
     * as well as the Notation used to represent the operation.
     *
     * @param elist The list of Expressions to compare
     * @param n The Notation to be used to represent the operation
     * @throws IllegalConstruction    If an empty list of expressions if passed as parameter
     * @see #BiggerThan(List<Expression>)
     * @see Operation#Operation(List<Expression>,Notation)
     */
    public BiggerThan(List<Expression> elist, Notation n) throws IllegalConstruction {
        super(elist, n);
        symbol = ">=";
        neutral = 0;
    }

    /**
     * The actual computation if the first integer is bigger than the second integer
     * @param l The first integer
     * @param r The second integer
     * @return The integer that is the result of the comparison
     */
    @Override
    public MyNumber op(MyNumber l, MyNumber r) throws IllegalConstruction {
        if (l.isComplex() || r.isComplex()) {
            throw new IllegalArgumentException("Comparison of complex numbers is not defined");
        }
        else{

            if (l.getValue() instanceof RealValue && !(r.getValue() instanceof RealValue)) {

                if ((l.getValue().comp(r.getValue().toReal())) >= 0){
                    return new MyNumber(new IntegerValue(1));
                }
                return new MyNumber(new IntegerValue(0));
            }
            if (r.getValue() instanceof RealValue && !(l.getValue() instanceof RealValue)) {

                if ((l.getValue().toReal().comp(r.getValue())) >= 0){
                    return new MyNumber(new IntegerValue(1));
                }
                return new MyNumber(new IntegerValue(0));
            }
            if (l.getValue().comp(r.getValue()) >= 0) {
                return new MyNumber(new IntegerValue(1));
            }
            return new MyNumber(new IntegerValue(0));
        }
    }

    @Override
    public MyNumber op(MyNumber l) {
        throw new IllegalArgumentException("Comparison of one number is not defined");
    }
}
