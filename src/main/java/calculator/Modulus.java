package calculator;

import java.util.List;

public class Modulus extends Operation{

    public Modulus(List<Expression> elist) throws IllegalConstruction {
        super(elist, null);
    }
    public Modulus (List<Expression> elist, Notation n) throws IllegalConstruction {
        super(elist);
        symbol = "||";
        neutral = 0;
    }

    @Override
    public MyNumber op(MyNumber l, MyNumber r) {

        int a1 = l.getValue();
        int b1 = l.getImaginary();

        int val = (int) Math.sqrt(a1*a1 + b1*b1);
        return new MyNumber(val, 0);

    }

    @Override
    public MyNumber op(MyNumber l) {
        return op(l, new MyNumber(0,0));
    }


}
