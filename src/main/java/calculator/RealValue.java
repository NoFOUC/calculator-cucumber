package calculator;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * RealValue is a concrete class that represents real numbers.
 */
public class RealValue extends AbstractValue {


    private final BigDecimal value;
    private int precision;

    @Override
    public BigDecimal getRawValue() {
        return value;
    }

    /**
     * Constructor method
     * @param value
     * @param precision
     */
    public RealValue(BigDecimal value, int precision) {
        this.value = value;
        this.precision = precision;
    }

    /**
     * Constructor method
     * @param value
     */
    public RealValue(BigDecimal value) {
        this.value = value;
        this.precision = 12;
    }

    /**
     * Constructor method
     * @param value
     * @param precision
     */
    public RealValue(double value, int precision) {
        this.value = BigDecimal.valueOf(value);
        this.precision = precision;
    }

    /**
     * Constructor method
     * @param value
     */
    public RealValue(double value) {
        this.value = BigDecimal.valueOf(value);
        this.precision = 12;
    }

    /**
     * Constructor method
     * @param value
     * @param precision
     */
    public RealValue(int value, int precision) {
        this.value = new BigDecimal(value);
        this.precision = precision;
    }

    /**
     * Constructor method
     * @param value
     */
    public RealValue(int value) {
        this.value = new BigDecimal(value);
        this.precision = 12;
    }

    /**
     * Constructor method
     * @param value
     * @param precision
     */
    public RealValue(IntegerValue value, int precision) {
        this.value = new BigDecimal(value.getValue());
        this.precision = precision;
    }

    /**
     * Constructor method
     * @param value
     */
    public RealValue(IntegerValue value) {
        this.value = new BigDecimal(value.getValue());
        this.precision = 12;
    }

    /**
     * Getter method to obtain the precision of the value
     * @return The precision of the value
     */
    public int getPrecision() {
        return precision;
    }

    /**
     * Setter method to set the precision of the value
     * @param precision The precision of the value
     */
    public void setPrecision(int precision) {
        this.precision = precision;
    }

    @Override
    public AbstractValue add(AbstractValue other) {
        if (other instanceof IntegerValue) {
            other = new RealValue((IntegerValue) other, this.precision);
        }
        if (other instanceof RationalValue) {
            other = new RealValue(other.getRawValue(), this.precision);
        }
        return new RealValue(this.value.add(other.getRawValue()), this.precision);
    }

    @Override
    public AbstractValue sub(AbstractValue other) {
        if (other instanceof IntegerValue) {
            other = new RealValue((IntegerValue) other, this.precision);
        }
        if (other instanceof RationalValue) {
            other = new RealValue(other.getRawValue(), this.precision);
        }
        return new RealValue(this.value.subtract(other.getRawValue()), this.precision);
    }

    @Override
    public AbstractValue mul(AbstractValue other) {
        if (other instanceof IntegerValue) {
            other = new RealValue((IntegerValue) other, this.precision);
        }
        if (other instanceof RationalValue) {
            other = new RealValue(other.getRawValue(), this.precision);
        }
        return new RealValue(this.value.multiply(other.getRawValue()), this.precision);
    }

    @Override
    public AbstractValue div(AbstractValue other) {
        if (other instanceof IntegerValue) {
            other = new RealValue((IntegerValue) other, this.precision);
        }
        if (other instanceof RationalValue) {
            other = new RealValue(other.getRawValue(), this.precision);
        }
        return new RealValue(this.value.divide(other.getRawValue(), RoundingMode.HALF_UP), this.precision);
    }

    @Override
    public RealValue toReal() {
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null) {
            return false;
        }
        if (o == this) {
            return true;
        }

        if (o instanceof IntegerValue || o instanceof RationalValue) {
            return value.equals(((AbstractValue) o).getRawValue());
        }

        if (o instanceof RealValue other) {
            return this.value.equals(other.value);
        }
        return false;
    }

    @Override
    public String toString() {
        // return the value or the scientific notation of the value if it's too big

        if (this.value.compareTo(new BigDecimal(precision)) > 0) {
            return this.value.toEngineeringString();
        } else {
            return this.value.toString();
        }
    }

    @Override
    public int hashCode() {
        return this.value.hashCode();
    }
}