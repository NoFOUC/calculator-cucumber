package calculator;

import java.math.BigDecimal;
import visitor.Visitor;


// implements real numbers as a special kind of numbers, just like integers are.
public class RealNumber implements Expression {

    private final BigDecimal value;
    private int precision;

    /**
     * Constructor method
     *
     * @param v The number value to be contained in the object
     */

    public RealNumber(int v) { value = new BigDecimal(v); }
    public RealNumber(BigDecimal v) { value = v; }

    /**
     * Constructor method
     *
     * @param v The number value to be contained in the object
     * @param precision The precision of the number
     */
    public RealNumber(BigDecimal v, int precision) { value = v; this.precision = precision; }

    /**
     * getter method to obtain the value contained in the object
     *
     * @return The number number contained in the object
     */
    public BigDecimal getValue() { return value; }

    /**
     * getter method to obtain the precision of the real number
     */
    public int getPrecision() { return precision; }

    /**
     * accept method to implement the visitor design pattern to traverse arithmetic expressions.
     * Each number will pass itself to the visitor object to get processed by the visitor.
     *
     * @param v The visitor object
     */
    public void accept(Visitor v) { v.visit(this); }

    /**
     * The depth of a number expression is always 0
     *
     * @return The depth of a number expression
     */
    public int countDepth() { return 0; }

    /**
     * The number of operations contained in a number expression is always 0
     *
     * @return The number of operations contained in a number expression
     */
    public int countOps() { return 0; }

    /**
     * The number of numbers contained in a number expression is always 1
     *
     * @return The number of numbers contained in  a number expression
     */
    public int countNbs() { return 1; }

    /**
     * Return a string representation of the real number, using scientific notation if the number contains too much zeroes
     */
    @Override
    public String toString() {
        String str = value.toString();
        if (str.length() > precision + 2) {
            return value.toEngineeringString();
        } else {
            return str;
        }
    }

}