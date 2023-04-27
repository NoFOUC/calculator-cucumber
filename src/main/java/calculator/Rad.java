package calculator;

import java.math.BigDecimal;
import java.util.List;

/** This class represents the arithmetic sum operation "+".
 * The class extends an abstract superclass Operation.
 * Other subclasses of Operation represent other arithmetic operations.
 * @see Operation
 * @see Deg
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
public final class Rad extends Operation
{

    /**
     * Class constructor specifying a number in degree and convert it to rad.
     *
     * @param elist The list of Expressions to convert
     * @throws IllegalConstruction    If an empty list of expressions if passed as parameter
     * @see #Rad(List<Expression>,Notation)
     */
    public /*constructor*/ Rad(List<Expression> elist) throws IllegalConstruction {
        this(elist, null);
    }

    /**
     * Class constructor specifying a number in degree and convert it to rad.
     * @param elist The list of Expressions to convert
     * @param n The Notation to be used to represent the operation
     * @throws IllegalConstruction   If an empty list of expressions if passed as parameter
     * @see #Rad(List<Expression>)
     */
    public Rad(List<Expression> elist, Notation n) throws IllegalConstruction {
        super(elist,n);
        symbol = "RAD";
        neutral = 0;
    }

    /**
     * The actual computation of the rad conversion to degree.
     * @param l The left operand
     * @param r The right operand (not used here)
     * @return The result of the conversion
     */
    public MyNumber op(MyNumber l, MyNumber r){
        if (l.isComplex()) {
            throw new IllegalArgumentException("Conversion of degree to radian is not defined for complex numbers");
        }
        else {
            return new MyNumber(l.getValue().mul(new RealValue(new BigDecimal(Math.PI/180))));
        }
    }

    @Override
    public MyNumber op(MyNumber l) {
        return this.op(l, new MyNumber(0));
    }
}
