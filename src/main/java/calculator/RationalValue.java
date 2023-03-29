package calculator;

import visitor.Visitor;

/**
 * MyNumber is a concrete class that represents arithmetic numbers,
 * which are a special kind of Expressions, just like operations are.
 *
 * @see Expression
 * @see Operation
 */
public class RationalValue extends AbstractValue {
    private final IntegerValue numerator;
    private final IntegerValue denominator;

    // TODO Manage division by zero and zero at the numerator

    public /*constructor*/ RationalValue(IntegerValue numerator, IntegerValue denominator) {
        int gcd = euclidAlgorithm(numerator.getValue(), denominator.getValue());
        this.numerator = new IntegerValue(numerator.getValue() / gcd);
        this.denominator = new IntegerValue(denominator.getValue() / gcd);
    }

    public /*constructor*/ RationalValue(IntegerValue numerator) {
        this.numerator = new IntegerValue(numerator.getValue());
        this.denominator = new IntegerValue(1);
    }

    /**
     * Euclid's Algorithm for finding the greatest common algorithm
     */
    private int euclidAlgorithm(int a, int b) {
        if (b == 0) {
            return a;
        }
        return euclidAlgorithm(b, a % b);
    }

    public IntegerValue getNumerator() {
        return numerator;
    }

    public IntegerValue getDenominator() {
        return denominator;
    }

    // TODO: Minor code duplication, dunno is it's worthwhile to try to fix
    @Override
    public AbstractValue add(AbstractValue other) {
        if (other instanceof IntegerValue) {
            other = new RationalValue((IntegerValue) other);
        }
        int n1 = numerator.getValue();
        int n2 = ((RationalValue) other).getNumerator().getValue();
        int d1 = denominator.getValue();
        int d2 = ((RationalValue) other).getDenominator().getValue();

        return new RationalValue(new IntegerValue(n1 * d2 + n2 * d1), new IntegerValue(d1 * d2));
    }

    @Override
    public AbstractValue sub(AbstractValue other) {
        if (other instanceof IntegerValue) {
            other = new RationalValue((IntegerValue) other);
        }
        int n1 = numerator.getValue();
        int n2 = ((RationalValue) other).getNumerator().getValue();
        int d1 = denominator.getValue();
        int d2 = ((RationalValue) other).getDenominator().getValue();

        return new RationalValue(new IntegerValue(n1 * d2 - n2 * d1), new IntegerValue(d1 * d2));
    }

    @Override
    public AbstractValue mul(AbstractValue other) {
        if (other instanceof IntegerValue) {
            other = new RationalValue((IntegerValue) other);
        }
        int n1 = numerator.getValue();
        int n2 = ((RationalValue) other).getNumerator().getValue();
        int d1 = denominator.getValue();
        int d2 = ((RationalValue) other).getDenominator().getValue();

        return new RationalValue(new IntegerValue(n1 * n2), new IntegerValue(d1 * d2));
    }

    @Override
    public AbstractValue div(AbstractValue other) {
        if (other instanceof IntegerValue) {
            other = new RationalValue((IntegerValue) other);
        }
        return mul(((RationalValue) other).inverse());
    }


    public RationalValue inverse() {
        return new RationalValue(denominator, numerator);
    }


    /**
     * Convert a number into a String to allow it to be printed.
     *
     * @return The String that is the result of the conversion.
     */
    @Override
    public String toString() {
        if (denominator.getValue() == 1) {
            return numerator.toString();
        }
        return "(" + numerator + "/" + denominator + ")";
    }

    /**
     * Two MyNumber expressions are equal if the values they contain are equal
     *
     * @param o The object to compare to
     * @return A boolean representing the result of the equality test
     */
    @Override
    public boolean equals(Object o) {
        // No object should be equal to null (not including this check can result in an exception if a MyNumber is tested against null)
        if (o == null) return false;

        // If the object is compared to itself then return true
        if (o == this) {
            return true;
        }

        if (o instanceof IntegerValue & denominator.getValue() == 1 & o.equals(numerator)) {
            return true;
        }

        // If the object is of another type then return false
        if (o instanceof RationalValue) {
            return ((RationalValue) o).denominator.equals(denominator)
                    & ((RationalValue) o).numerator.equals(numerator);
        }

        return false;
    }

    /**
     * The method hashCode needs to be overridden it the equals method is overridden;
     * otherwise there may be problems when you use your object in hashed collections
     * such as HashMap, HashSet, LinkedHashSet.
     *
     * @return The result of computing the hash.
     */
    @Override
    public int hashCode() {
        // An imperfect solution, but I really didn't find anything much better on the internet
        return Double.hashCode((double)numerator.getValue() / (double)denominator.getValue());
    }

}
