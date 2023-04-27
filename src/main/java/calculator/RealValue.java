package calculator;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.text.DecimalFormat;

/**
 * RealValue is a concrete class that represents real numbers.
 */
public class RealValue extends AbstractValue {


    private final BigDecimal value;

    private static int globalPrecisionLimit = 5;

    @Override
    public BigDecimal getRawValue() {
        return value;
    }

    /**
     * Constructor method
     * @param value
     */
    public RealValue(BigDecimal value) {
        this.value = value;
    }

    /**
     * Constructor method
     * @param value
     */
    public RealValue(double value) {
        this.value = BigDecimal.valueOf(value);
    }

    /**
     * Constructor method
     * @param value
     */
    public RealValue(int value) {
        this.value = new BigDecimal(value);//NOSONAR
    }

    /**
     * Constructor method
     * @param value
     */
    public RealValue(IntegerValue value) {
        this.value = new BigDecimal(value.getValue());
    }

    @Override
    public AbstractValue add(AbstractValue other) {
        if (other instanceof IntegerValue) {
            other = new RealValue((IntegerValue) other);
        }
        if (other instanceof RationalValue) {
            other = new RealValue(other.getRawValue());
        }
        return new RealValue(this.value.add(other.getRawValue()));
    }

    @Override
    public AbstractValue sub(AbstractValue other) {
        if (other instanceof IntegerValue) {
            other = new RealValue((IntegerValue) other);
        }
        if (other instanceof RationalValue) {
            other = new RealValue(other.getRawValue());
        }
        return new RealValue(this.value.subtract(other.getRawValue()));
    }

    @Override
    public AbstractValue mul(AbstractValue other) {
        if (other instanceof IntegerValue) {
            other = new RealValue((IntegerValue) other);
        }
        if (other instanceof RationalValue) {
            other = new RealValue(other.getRawValue());
        }
        return new RealValue(this.value.multiply(other.getRawValue()));
    }

    @Override
    public AbstractValue div(AbstractValue other) {
        if (other instanceof IntegerValue) {
            other = new RealValue((IntegerValue) other);
        }
        if (other instanceof RationalValue) {
            other = new RealValue(other.getRawValue());
        }
        return new RealValue(this.value.divide(other.getRawValue(), RoundingMode.HALF_UP));
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
            return value.compareTo(((AbstractValue) o).getRawValue()) == 0;
        }

        if (o instanceof RealValue other) {
            return this.value.compareTo(other.value) == 0;
        }
        return false;
    }

    @Override
    public String toString() {
        // return the value or the scientific notation of the value if it's too big
        if (this.value.abs().compareTo(new BigDecimal(10).pow( globalContractionLimit)) > 0 ||
            this.value.abs().compareTo(new BigDecimal("0.1").pow(globalContractionLimit)) < 0) {
            if (this.value.compareTo(BigDecimal.ZERO) == 0) return "0"; // Kind of hack-y but that's the least ugly way of doing it
            String formatPattern = "0." + "#".repeat(Math.max(0, globalPrecisionLimit)) + "E0";
            DecimalFormat df = new DecimalFormat(formatPattern);
            return df.format(this.value);
        } else {
            return this.value.setScale(globalPrecisionLimit, RoundingMode.HALF_UP).stripTrailingZeros().toString();
        }
    }

    @Override
    public int hashCode() {
        return this.value.hashCode();
    }

    public static void setGlobalPrecisionLimit(int limit) {
        globalPrecisionLimit = limit;
    }

}