package calculator;

import java.util.List;

/**
 * The square root operation of complex numbers
 */
public class Sqrt extends Operation {

    /**
     * Class constructor specifying a number of Expressions to square root.
     * @param elist The list of Expressions to square root
     * @throws IllegalConstruction   If an empty list of expressions if passed as parameter
     * @see #Sqrt(List<Expression>,Notation)
     */
    public Sqrt (List<Expression> elist) throws IllegalConstruction {
        this(elist, null);
    }

    /**
     * @param elist The list of Expressions to square root
     * @param n The Notation to be used to represent the operation
     * @throws IllegalConstruction  If an empty list of expressions if passed as parameter
     * @see #Sqrt(List<Expression>)
     * @see Operation#Operation(List<Expression>,Notation)
     */
    public Sqrt (List<Expression> elist, Notation n) throws IllegalConstruction {
        super(elist, n);
        symbol = "sqrt";
        neutral = 1;
    }

    /**
     * The actual computation of the square root of a complex number
     * @param l The first integer
     * @param r The second integer not used in this operation
     * @return the square root of the complex number
     */
    @Override
    public MyNumber op(MyNumber l, MyNumber r) {
        return op(l);
    }

    /**
     * The actual computation of the square root of a complex number
     * @param l The first integer
     * @return the square root of the complex number
     */
    public MyNumber op(MyNumber l)
    {
        int a1 = l.getValue();
        int b1 = l.getImaginary();

        if (l.isComplex()){
            int a = (int) Math.sqrt(((a1*a1 + b1*b1)+a1)/2);
            int b = (int) Math.sqrt(((a1*a1 + b1*b1)-a1)/2)* b1/Math.abs(b1);
            return new MyNumber(a,b);
        }
        else {
            if (a1<0){
                int b = (int) Math.sqrt(-a1);
                return new MyNumber(0,b);
            }
            else {
                int a = (int) Math.sqrt(a1);
                return new MyNumber(a,0);
            }
        }
    }

}
