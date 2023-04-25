package parser;

import static org.junit.jupiter.api.Assertions.*;

import calculator.IntegerValue;
import calculator.MyNumber;
import calculator.RationalValue;
import calculator.RealValue;
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

    }

    @Test
    public void testreturn (){

        calcul.add(new ArrayList<Object>(Arrays.asList("√", "1", "+", "2")));
        calcul.add("+");
        calcul.add("1");
        calcul.add("i");


        try {
            MyNumber a = Parser.main(calcul);
            assertEquals("1.7320508075 + 1i" ,a.toString());

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
            assertEquals("8.8999999999 + 6.8999999999i",a.toString());

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
            assertEquals("0.6420926159", a.toString());

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
            assertEquals("1.5707963267", a.toString());

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
            assertEquals("1.5707963267", a.toString(10));

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



}
