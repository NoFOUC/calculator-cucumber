package converter;

import calculator.MyExpPol;
import calculator.MyNumber;
import calculator.RealValue;

public class ComplexConverter {

    public static MyNumber exponentialToCartesian (double r, double theta){
        // convert exponential form with r and theta to cartesian form
        //calcul the real part
        double real = r * Math.cos(theta);
        //calcul the imaginary part
        double imaginary = r * Math.sin(theta);

        return new MyNumber(new RealValue(real), new RealValue(imaginary));
    }

    public static MyNumber exponentialToCartesian (MyExpPol exp){
        // convert exponential form with r and theta to cartesian form
        //calcul the real part
        double real = exp.getR() * Math.cos(exp.getTheta());
        //calcul the imaginary part
        double imaginary = exp.getR() * Math.sin(exp.getTheta());

        return new MyNumber(new RealValue(real), new RealValue(imaginary));
    }

    public static MyNumber polarToCartesian (double r, double theta){
        // convert exponential form with r and theta to cartesian form
        //calcul the real part
        double real = r * Math.cos(theta);
        //calcul the imaginary part
        double imaginary = r * Math.sin(theta);

        return new MyNumber(new RealValue(real), new RealValue(imaginary));

    }

    public static MyNumber polarToCartesian (MyExpPol exp){
        // convert exponential form with r and theta to cartesian form
        //calcul the real part
        double real = exp.getR() * Math.cos(exp.getTheta());
        //calcul the imaginary part
        double imaginary = exp.getR() * Math.sin(exp.getTheta());

        return new MyNumber(new RealValue(real), new RealValue(imaginary));

    }

    public static MyExpPol cartesianToExponential (double real, double imaginary){
        // convert exponential form with r and theta to cartesian form
        //calcul the radius
        double r = Math.sqrt(Math.pow(real, 2) + Math.pow(imaginary, 2));
        //calcul the angle
        double theta = Math.atan(imaginary/real);

        return new MyExpPol(r, theta);

    }

    public static MyExpPol cartesianToExponential (MyNumber number){
        // convert exponential form with r and theta to cartesian form
        //calcul the radius
        double r = Math.sqrt(Math.pow(number.getValue().getRawValue().doubleValue(), 2) + Math.pow(number.getImaginary().getRawValue().doubleValue(), 2));
        //calcul the angle
        double theta = Math.atan(number.getImaginary().getRawValue().doubleValue()/number.getValue().getRawValue().doubleValue());

        return new MyExpPol(r, theta);

    }

    public static MyExpPol cartesianToPolar (double real, double imaginary){
        // convert exponential form with r and theta to cartesian form
        //calcul the radius
        double r = Math.sqrt(Math.pow(real, 2) + Math.pow(imaginary, 2));
        //calcul the angle
        double theta = Math.atan(imaginary/real);

        return new MyExpPol(r, theta);

    }

    public static MyExpPol cartesianToPolar (MyNumber number){
        // convert exponential form with r and theta to cartesian form
        //calcul the radius


        return cartesianToExponential(number);
    }

    public static MyExpPol polarToExponential (double r, double theta){

        return new MyExpPol(r, theta);

    }

    public static MyExpPol polarToExponential (MyExpPol exp){

        return new MyExpPol(exp.getR(), exp.getTheta());

    }
    public static MyExpPol exponentialToPolar (double r, double theta){

        return new MyExpPol(r, theta);
    }

    public static MyExpPol exponentialToPolar (MyExpPol exp){

        return new MyExpPol(exp.getR(), exp.getTheta());
    }

    public static String exponentialString (double r, double theta){
        return r + "e^(" + theta + "i)";
    }


    public static String exponentialString (MyExpPol exp){
        return exp.getR() + "e^(" + exp.getTheta() + "i)";
    }


    public static String polarString (double r, double theta){
        return r + "(cos(" + theta + ") + i sin(" + theta + "))";
    }

    public static String polarString (MyExpPol exp){
        return exp.getR() + "(cos(" + exp.getTheta() + ") + i sin(" + exp.getTheta() + "))";
    }

    public static String cartesianString (double real, double imaginary){
        return real + " + " + imaginary + "i";
    }

    public static String cartesianString (MyNumber number){
        return number.getValue().getRawValue() + " + " + number.getImaginary().getRawValue() + "i";
    }

}
