package Converter;

import calculator.IntegerValue;
import calculator.MyExp;
import calculator.MyNumber;
import calculator.MyPolar;

/**
 * This class converts a complex number from one form to another
 */
public class ComplexConverter {
    //converter from polar to cartesian
    //make javadoc

    /**
     * Converts a polar number to a cartesian number
     * @param pol polar form
     * @return a cartesian number
     */
    public static MyNumber polarToCartesian(MyPolar pol) {
        int r = pol.getR();
        int theta = pol.getTheta();
        int a = (int) (r * Math.cos(theta));
        int b = (int) (r * Math.sin(theta));
        return new MyNumber(new IntegerValue(a), new IntegerValue(b)); //TODO: Float management
    }

    /**
     * Converts a exponential number to a cartesian number
     * @param exp exponential form
     * @return a cartesian number
     */
    public static MyNumber exponentialToCartesian(MyExp exp) {
        return polarToCartesian(exp);
    }


}
