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

    public RealValue(BigDecimal value, int precision) {
        this.value = value;
        this.precision = precision;
    }

    public RealValue(BigDecimal value) {
        this.value = value;
        this.precision = 10;
    }

    public RealValue(double value, int precision) {
        this.value = new BigDecimal(value);
        this.precision = precision;
    }

    public RealValue(double value) {
        this.value = new BigDecimal(value);
        this.precision = 10;
    }

    public RealValue(int value, int precision) {
        this.value = new BigDecimal(value);
        this.precision = precision;
    }

    public RealValue(int value) {
        this.value = new BigDecimal(value);
        this.precision = 10;
    }

    public RealValue(IntegerValue value, int precision) {
        this.value = new BigDecimal(value.getValue());
        this.precision = precision;
    }

    public RealValue(IntegerValue value) {
        this.value = new BigDecimal(value.getValue());
        this.precision = 10;
    }

    public int getPrecision() {
        return precision;
    }

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
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (obj == this) {
            return true;
        }

        if (obj instanceof RealValue other) {
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