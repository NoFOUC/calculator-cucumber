package calculator;

import java.math.BigDecimal;
import java.text.DecimalFormat;

/**
 * MyNumber is a concrete class that represents arithmetic numbers,
 * which are a special kind of Expressions, just like operations are.
 *
 * @see Expression
 * @see Operation
 */
public class IntegerValue extends AbstractValue {
    private final int value;

    /**
     * getter method to obtain the value contained in the object
     *
     * @return The integer number contained in the object
     */
    public Integer getValue() {
        return value;
    }

    public BigDecimal getRawValue() {
        return new BigDecimal(value); //NOSONAR
    }


    /**
     * Constructor method
     *
     * @param v The integer value to be contained in the object
     */
    public /*constructor*/ IntegerValue(int v) {
        value = v;
    }

    @Override
    public AbstractValue add(AbstractValue other) {
        if (other instanceof RationalValue) {
            return other.add(this);
        }
        if (other instanceof IntegerValue) {
            return new IntegerValue(value + ((IntegerValue) other).getValue());
        }
        return null;
    }

    @Override
    public AbstractValue sub(AbstractValue other) {
        if (other instanceof RationalValue) {
            return new RationalValue(this).sub(other);
        }
        if (other instanceof IntegerValue) {
            return new IntegerValue(value - ((IntegerValue) other).getValue());
        }
        return null;
    }

    @Override
    public AbstractValue mul(AbstractValue other) {
        if (other instanceof RationalValue) {
            return other.mul(this);
        }
        if (other instanceof IntegerValue) {
            return new IntegerValue(value * ((IntegerValue) other).getValue());
        }
        if (other instanceof RealValue) {
            return other.mul(this);
        }
        return null;
    }

    @Override
    public AbstractValue div(AbstractValue other) {
        if (other instanceof RationalValue) {
            return new RationalValue(this).div(other);
        }
        if (other instanceof IntegerValue) {
            return new RationalValue(new IntegerValue(value), ((IntegerValue) other));
        }
        if (other instanceof RealValue) {
            return new RealValue(value).div(other);
        }
        return null;
    }

    /**
     * Convert a number into a String to allow it to be printed.
     *
     * @return The String that is the result of the conversion.
     */
    @Override
    public String toString() {
//        System.out.println(Math.pow(10, globalContractionLimit) + "" + value);
        if (value > (Math.pow(10, globalContractionLimit))) {
            String formatPattern = "0." + "#".repeat(Math.max(0, 5)) + "E0";
            DecimalFormat df = new DecimalFormat(formatPattern);
            return df.format(this.value);
        }
        return Integer.toString(value);
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

        if (o instanceof RationalValue) {
            return o.equals(this);
        }

        // If the object is of another type then return false
        if (!(o instanceof IntegerValue)) {
            return false;
        }
        return this.value == ((IntegerValue) o).value;
        // Used == since the contained value is a primitive value
        // If it had been a Java object, .equals() would be needed
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
        return value;
    }

    /**
     * methods that return a RealValue and traduct IntegerValue to RealValue
     */
    //methods that return a RealValue and traduct IntegerValue to RealValue
    @Override
    public RealValue toReal () {

        RealValue real = new RealValue(value);

        return real;

    }

}
