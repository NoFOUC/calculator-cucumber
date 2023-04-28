package parser;

import static org.junit.jupiter.api.Assertions.*;

import calculator.*;
import io.cucumber.java.bs.A;
import org.junit.jupiter.api.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TestParser {

    ArrayList <Object> calcul = new ArrayList<>();

    ArrayList <Object> b = new ArrayList<>();

    String s;



    @BeforeEach
    public void setUp() {

        calcul = new ArrayList<>();
        RealValue.setGlobalPrecisionLimit(10);

    }

    @Test
    public void testreturn (){

        calcul.add(new ArrayList<Object>(Arrays.asList("√", "1", "+", "2")));
        calcul.add("+");
        calcul.add("1");
        calcul.add("i");


        try {
            MyNumber a = Parser.main(calcul);
            assertEquals("1.7320508076 + 1i" ,a.toString());

        } catch (Exception e) {
            fail();
        }

    }

    @Test
    public void testmultipleParsing () {

        calcul.add(new ArrayList<Object>(Arrays.asList("exp", "0")));
        calcul.add("*");
        calcul.add(new ArrayList<Object>(Arrays.asList("||", "1", "i")));
        calcul.add("*");
        calcul.add("2");
        calcul.add(".");
        calcul.add("3");
        calcul.add("*");
        calcul.add(new ArrayList<Object>(Arrays.asList("1", "*", "3", "-", "0")));
        calcul.add("+");
        calcul.add(new ArrayList<Object>(Arrays.asList("√", "1")));
        calcul.add("*");
        calcul.add("2");
        calcul.add(".");
        calcul.add("3");
        calcul.add("i");
        calcul.add("*");
        calcul.add("3");
        calcul.add("+");
        calcul.add("2");
        calcul.add("%");
        calcul.add("3");

        try {
            MyNumber a = Parser.main(calcul);
            assertEquals("8.9 + 6.9i",a.toString());

        } catch (Exception e) {
            fail();
        }


    }

    @Test
    public void testBiggerThanParsing(){

        calcul.add("1");
        calcul.add(">");
        calcul.add("2");

        try {
            MyNumber a = Parser.main(calcul);
            assertEquals("0",a.toString());

        } catch (Exception e) {
            fail();
        }

    }

    @Test
    public void testLessThanParsing(){

        calcul.add("1");
        calcul.add("<");
        calcul.add("2");

        try {
            MyNumber a = Parser.main(calcul);
            assertEquals("1",a.toString());

        } catch (Exception e) {
            fail();
        }

    }

    @Test
    public void testSin () {

            calcul.add("sin");
            calcul.add("0");
            /*
            calcul.add("3");
            calcul.add(".");
            calcul.add("1");
            calcul.add("4");
            calcul.add("1");
            calcul.add("5");
            calcul.add("9");
            calcul.add("2");
            calcul.add("6");
            calcul.add("5");
            calcul.add("3");
            calcul.add("5");
            calcul.add("8");
            calcul.add("9");
            calcul.add("7");
            calcul.add("9");
            calcul.add("3");
            calcul.add("2");
            calcul.add("3");
            calcul.add("8");
            */


            try {
                MyNumber a = Parser.main(calcul);
                assertEquals("0",a.toString());

            } catch (Exception e) {
                fail();
            }

    }

    @Test
    public void testCos () {

        calcul.add("cos");
        calcul.add("0");

        try {
            MyNumber a = Parser.main(calcul);
            assertEquals("1", a.toString());

        } catch (Exception e) {
            fail();
        }
    }

    @Test
    public void testTan () {

        calcul.add("tan");
        calcul.add("0");

        try {
            MyNumber a = Parser.main(calcul);
            assertEquals("0", a.toString());

        } catch (Exception e) {
            fail();
        }
    }

    @Test
    public void testCot () {

        calcul.add("cot");
        calcul.add("1");

        try {
            MyNumber a = Parser.main(calcul);
            assertEquals("57.2899616308", a.toString());

        } catch (Exception e) {
            fail();
        }
    }

    @Test

    public void testSec () {

        calcul.add("sec");
        calcul.add("0");

        try {
            MyNumber a = Parser.main(calcul);
            assertEquals("0", a.toString());

        } catch (Exception e) {
            fail();
        }
    }

    @Test

    public void testAsin () {

        calcul.add("asin");
        calcul.add("0");

        try {
            MyNumber a = Parser.main(calcul);
            assertEquals("0", a.toString());

        } catch (Exception e) {
            fail();
        }
    }

    @Test

    public void testAcos () {

        calcul.add("acos");
        calcul.add("0");

        try {
            MyNumber a = Parser.main(calcul);
            assertEquals("9E+1", a.toString());

        } catch (Exception e) {
            fail();
        }
    }

    @Test

    public void testAtan () {

        calcul.add("atan");
        calcul.add("0");

        try {
            MyNumber a = Parser.main(calcul);
            assertEquals("0", a.toString());

        } catch (Exception e) {
            fail();
        }
    }

    @Test

    public void testAcot () {

        calcul.add("acot");
        calcul.add("0");

        try {
            MyNumber a = Parser.main(calcul);
            assertEquals("9E+1", a.toString());

        } catch (Exception e) {
            fail();
        }
    }

    @Test

    public void testLn () {

            calcul.add("ln");
            calcul.add("1");

            try {
                MyNumber a = Parser.main(calcul);
                assertEquals("0", a.toString());

            } catch (Exception e) {
                fail();
            }
    }

    @Test
    public void testPlus () {

        calcul.add("1");
        calcul.add("+");
        calcul.add("2");

        try {
            MyNumber a = Parser.main(calcul);
            assertEquals("3", a.toString());

        } catch (Exception e) {
            fail();
        }
    }

    @Test
    public void testMinus () {

        calcul.add("1");
        calcul.add("-");
        calcul.add("2");

        try {
            MyNumber a = Parser.main(calcul);
            assertEquals("-1", a.toString());

        } catch (Exception e) {
            fail();
        }
    }

    @Test
    public void testDeg () {

            calcul.add("DEG");
            calcul.add("1");

            try {
                MyNumber a = Parser.main(calcul);
                assertEquals("57.2957795131", a.toString());

            } catch (Exception e) {
                fail();
            }
    }

    @Test
    public void testRad () {

            calcul.add("RAD");
            calcul.add("1");
            calcul.add("8");
            calcul.add("0");

            try {
                MyNumber a = Parser.main(calcul);
                assertEquals("3.1415926536", a.toString());

            } catch (Exception e) {
                fail();
            }
    }

    @Test
    public void testGen() {

        calcul.add("RNG");
        calcul.add("1");
        calcul.add("0");
        calcul.add("0");

        try {
            MyNumber a = Parser.main(calcul);

            double aDouble = Double.parseDouble(a.toString());

            assertTrue(100.0 >= aDouble, "Error, random is too high");
            assertTrue(0.0 <= aDouble, "Error, random is too low");

        } catch (Exception e) {
            fail();
        }

    }

    @Test
    public void testGenReal (){

        calcul.add("RNG");
        calcul.add("1");
        calcul.add("0");
        calcul.add("0");
        calcul.add(".");
        calcul.add("1");

        try {
            MyNumber a = Parser.main(calcul);

            double aDouble = Double.parseDouble(a.toString());

            assertTrue(100.1 >= aDouble, "Error, random is too high");
            assertTrue(0.0 <= aDouble, "Error, random is too low");

        } catch (Exception e) {
            fail();
        }

    }

    @Test
    public void testGenRational (){

        calcul.add("RNG");
        calcul.add(new ArrayList<Object>(Arrays.asList("2", "0", "1", "/", "2")));

        try {
            MyNumber a = Parser.main(calcul);

            String aString = a.toString().replace("(", "").replace(")", "");
            double aDouble = 0.0;
            if (a.toString().split("/").length < 2) {
                aDouble = Double.parseDouble(a.toString());
            }
            else {
                double numDouble = Double.parseDouble(aString.split("/")[0]+".0");
                double denDouble = Double.parseDouble(aString.split("/")[1]+".0");

                aDouble = numDouble / denDouble;
            }


            assertTrue(100.5 >= aDouble, "Error, random is too high");
            assertTrue(0.0 <= aDouble, "Error, random is too low");

        } catch (Exception e) {
            fail();
        }

    }

}
