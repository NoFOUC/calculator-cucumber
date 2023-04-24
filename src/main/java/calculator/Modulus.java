package calculator;

import java.util.List;

/**
 * The modulus operation of complex numbers
 */
public class Modulus extends Operation{

    /**
     * Class constructor specifying a number of Expressions to subtract.
     * @param elist The list of Expressions to subtract
     * @throws IllegalConstruction   If an empty list of expressions if passed as parameter
     * @see #Modulus(List<Expression>,Notation)
     */
    public Modulus(List<Expression> elist) throws IllegalConstruction {
        this(elist, null);
    }

    /**
     * @param elist The list of Expressions to subtract
     * @param n The Notation to be used to represent the operation
     * @throws IllegalConstruction  If an empty list of expressions if passed as parameter
     * @see #Modulus(List<Expression>)
     * @see Operation#Operation(List<Expression>,Notation)
     */
    public Modulus (List<Expression> elist, Notation n) throws IllegalConstruction {
        super(elist, Notation.PREFIX);
        symbol = "||";
        neutral = 1;
    }


    /**
     * The actual computation of the modulus of a complex number
     * @param l The first integer
     * @return the modulus of the complex number
     */
    public MyNumber op(MyNumber l) {
        AbstractValue a1 = l.getValue();
        AbstractValue b1 = l.getImaginary();

        double val = Math.sqrt(a1.mul(a1).add(b1.mul(b1)).getRawValue().doubleValue());

        if (l.isComplex()){
            return new MyNumber(new RealValue(val), new RealValue(0));
        }
        else {
            return new MyNumber(new IntegerValue((int) val), new IntegerValue(0));
        }
    }

    /**
     * The actual computation of the modulus of a complex number
     * @param l The first integer
     * @param r The second integer not used in this operation
     * @return the modulus of the complex number
     */
    @Override
    public MyNumber op(MyNumber l, MyNumber r) {
        return op(l);
    }
}
