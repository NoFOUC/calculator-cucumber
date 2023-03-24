package Converter;

import calculator.MyExp;
import calculator.MyNumber;
import calculator.MyPolar;

public class ComplexConverter {
    //converter from polar to cartesian
    public static MyNumber polarToCartesian(MyPolar pol) {
        int r = pol.getR();
        int theta = pol.getTheta();
        int a = (int) (r * Math.cos(theta));
        int b = (int) (r * Math.sin(theta));
        return new MyNumber(a, b);
    }

    public static MyNumber exponentialToCartesian(MyExp exp) {
        return polarToCartesian(exp);
    }


}
