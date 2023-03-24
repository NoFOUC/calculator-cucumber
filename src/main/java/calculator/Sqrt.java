package calculator;

import java.util.List;

public class Sqrt extends Operation {

    public Sqrt (List<Expression> elist) throws IllegalConstruction {
        this(elist, null);
    }

    public Sqrt (List<Expression> elist, Notation n) throws IllegalConstruction {
        super(elist, n);
        symbol = "sqrt";
        neutral = 1;
    }

    @Override
    public MyNumber op(MyNumber l, MyNumber r) {
        return op(l);
    }

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
