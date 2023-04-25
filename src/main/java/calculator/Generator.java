package calculator;

import java.util.List;
public class Generator extends Operation {

    public Generator(List<Expression> elist) throws IllegalConstruction {
        this(elist, null);
    }

    public Generator(List<Expression> elist, Notation n) throws IllegalConstruction {
        super(elist, n);
        symbol = "random";
        neutral = 0;
    }

    public MyNumber op(MyNumber l) {
        AbstractValue real = random(l.getValue());
        AbstractValue imaginary = random(l.getImaginary());
        return new MyNumber(real, imaginary);
    }

    public static AbstractValue random(AbstractValue v) {
        if (v instanceof RealValue) {
            return new RealValue(Math.random());
        } else if (v instanceof IntegerValue) {
            return new IntegerValue((int) (Math.random() * ((IntegerValue) v).getValue()));
        } else {
            return null;
        }
    }

    public MyNumber op(MyNumber l, MyNumber r) { return null; }

}
