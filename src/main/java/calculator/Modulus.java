package calculator;

import java.util.List;

public class Modulus extends Operation{

    public Modulus(List<Expression> elist) throws IllegalConstruction {
        this(elist, null);
    }
    public Modulus (List<Expression> elist, Notation n) throws IllegalConstruction {
        super(elist, Notation.PREFIX);
        symbol = "||";
        neutral = 1;
    }



    public MyNumber op(MyNumber l) {
        int a1 = l.getValue();
        int b1 = l.getImaginary();

        int val = (int) Math.sqrt(a1*a1 + b1*b1);
        return new MyNumber(val, 0);
    }

    @Override
    public MyNumber op(MyNumber l, MyNumber r) {
        return op(l);
    }
}
