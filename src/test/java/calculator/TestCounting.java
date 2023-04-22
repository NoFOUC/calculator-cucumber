package calculator;

//Import Junit5 libraries for unit testing:
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;


class TestCounting {

    private int value1, value2;

    private int imaginary1, imaginary2;

    private int denominator1, denominator2, denominator3, denominator4;

    private BigDecimal value3BD, value4BD;

    private Expression e, e2, e3, e4, e5;

    @BeforeEach
    void setUp() {
        value1 = 8;
        value2 = 6;
        imaginary1 = 3;
        imaginary2 = 2;
        denominator1 = 2;
        denominator2 = 3;
        denominator3 = 4;
        denominator4 = 5;
        value3BD = new BigDecimal("8.278397");
        value4BD = new BigDecimal("6.187802");
        e = null;
        e2 = null;
        e3 = null;
        e4 = null;
        e5 = null;
    }

    @Test
    void testNumberCounting() {
        e = new MyNumber(value1);
        e2 = new MyNumber(value1, imaginary1);
        e3 = new MyNumber(new RationalValue(new IntegerValue(value1), new IntegerValue(denominator1)));
        e4 = new MyNumber(new RationalValue(new IntegerValue(value1), new IntegerValue(denominator1)),
                new RationalValue(new IntegerValue(imaginary1), new IntegerValue(denominator2)));
        e5 = new MyNumber(new RealValue(value3BD));
        //test whether a number has zero depth (i.e. no nested expressions)
        assertEquals( 0, e.countDepth());
        //test whether a number contains zero operations
        assertEquals(0, e.countOps());
        //test whether a number contains 1 number
        assertEquals(1, e.countNbs());

        //test whether a complex number has zero depth (i.e. no nested expressions)
        assertEquals( 0, e2.countDepth());
        //test whether a complex number contains zero operations
        assertEquals(0, e2.countOps());
        //test whether a complex number contains 1 number
        assertEquals(1, e2.countNbs());

        //test whether a rational number has zero depth (i.e. no nested expressions)
        assertEquals( 0, e3.countDepth());
        //test whether a rational number contains zero operations
        assertEquals(0, e3.countOps());
        //test whether a rational number contains 1 number
        assertEquals(1, e3.countNbs());

        //test whether a complex rational number has zero depth (i.e. no nested expressions)
        assertEquals( 0, e4.countDepth());
        //test whether a complex rational number contains zero operations
        assertEquals(0, e4.countOps());
        //test whether a complex rational number contains 1 number
        assertEquals(1, e4.countNbs());

        //test whether a real number has zero depth (i.e. no nested expressions)
        assertEquals( 0, e5.countDepth());
        //test whether a real number contains zero operations
        assertEquals(0, e5.countOps());
        //test whether a real number contains 1 number
        assertEquals(1, e5.countNbs());

    }

    @ParameterizedTest
    @ValueSource(strings = {"*", "+", "/", "-", "sqrt", "||", "<", ">"})
    void testOperationCounting(String symbol) {
        List<Expression> params = Arrays.asList(new MyNumber(value1),new MyNumber(value2));
        //Operation op = null;
        try {
            //construct another type of operation depending on the input value
            //of the parameterised test
            switch (symbol) {
                case "+"	->	e = new Plus(params);
                case "-"	->	e = new Minus(params);
                case "*"	->	e = new Times(params);
                case "/"	->	e = new Divides(params);
                case "sqrt"	->	e = new Sqrt(params);
                case "||"	->	e = new Modulus(params);
                case "<"	->	e = new LessThan(params);
                case ">"	->	e = new BiggerThan(params);
                default		->	fail();
            }
        } catch (IllegalConstruction e) {
            fail();
        }
        //test whether a binary operation has depth 1
        assertEquals(1, e.countDepth(),"counting depth of an Operation");
        //test whether a binary operation contains 1 operation
        assertEquals(1, e.countOps());
        //test whether a binary operation contains 2 numbers
        assertEquals(2, e.countNbs());
    }

    @ParameterizedTest
    @ValueSource(strings = {"*", "+", "/", "-", "sqrt", "||", "<", ">"})
    void testComplexOperationCounting (String symbol) {
        List<Expression> params = Arrays.asList(new MyNumber(value1, imaginary1),new MyNumber(value2, imaginary2));
        //Operation op = null;
        try {
            //construct another type of operation depending on the input value
            //of the parameterised test
            switch (symbol) {
                case "+"	->	e = new Plus(params);
                case "-"	->	e = new Minus(params);
                case "*"	->	e = new Times(params);
                case "/"	->	e = new Divides(params);
                case "sqrt"	->	e = new Sqrt(params);
                case "||"	->	e = new Modulus(params);
                case "<"	->	e = new LessThan(params);
                case ">"	->	e = new BiggerThan(params);
                default		->	fail();
            }
        } catch (IllegalConstruction e) {
            fail();
        }
        //test whether a binary operation has depth 1
        assertEquals(1, e.countDepth(),"counting depth of an Operation");
        //test whether a binary operation contains 1 operation
        assertEquals(1, e.countOps());
        //test whether a binary operation contains 2 numbers
        assertEquals(2, e.countNbs());

    }

    @ParameterizedTest
    @ValueSource(strings = {"*", "+", "/", "-", "sqrt", "||", "<", ">"})
    void testRationalOperationCounting (String symbol) {
        List<Expression> params = Arrays.asList(new MyNumber(new RationalValue(new IntegerValue(value1), new IntegerValue(denominator1)), new RationalValue(new IntegerValue(imaginary1), new IntegerValue(denominator2))),new MyNumber(new RationalValue(new IntegerValue(value2), new IntegerValue(denominator3)), new RationalValue(new IntegerValue(imaginary2), new IntegerValue(denominator4))));
        //Operation op = null;
        try {
            //construct another type of operation depending on the input value
            //of the parameterised test
            switch (symbol) {
                case "+"	->	e = new Plus(params);
                case "-"	->	e = new Minus(params);
                case "*"	->	e = new Times(params);
                case "/"	->	e = new Divides(params);
                case "sqrt"	->	e = new Sqrt(params);
                case "||"	->	e = new Modulus(params);
                case "<"	->	e = new LessThan(params);
                case ">"	->	e = new BiggerThan(params);
                default		->	fail();
            }
        } catch (IllegalConstruction e) {
            fail();
        }
        //test whether a binary operation has depth 1
        assertEquals(1, e.countDepth(),"counting depth of an Operation");
        //test whether a binary operation contains 1 operation
        assertEquals(1, e.countOps());
        //test whether a binary operation contains 2 numbers
        assertEquals(2, e.countNbs());

    }

    @ParameterizedTest
    @ValueSource(strings = {"*", "+", "/", "-","sqrt", "||", "<", ">"})
    void testComplexRationalOperationCounting (String symbol) {
        List<Expression> params = Arrays.asList(new MyNumber(new RationalValue(new IntegerValue(value1), new IntegerValue(denominator1)), new RationalValue(new IntegerValue(imaginary1), new IntegerValue(denominator2))),new MyNumber(new RationalValue(new IntegerValue(value2), new IntegerValue(denominator3)), new RationalValue(new IntegerValue(imaginary2), new IntegerValue(denominator4))));
        //Operation op = null;
        try {
            //construct another type of operation depending on the input value
            //of the parameterised test
            switch (symbol) {
                case "+"	->	e = new Plus(params);
                case "-"	->	e = new Minus(params);
                case "*"	->	e = new Times(params);
                case "/"	->	e = new Divides(params);
                case "sqrt"	->	e = new Sqrt(params);
                case "||"	->	e = new Modulus(params);
                case "<"	->	e = new LessThan(params);
                case ">"	->	e = new BiggerThan(params);
                default		->	fail();
            }
        } catch (IllegalConstruction e) {
            fail();
        }
        //test whether a binary operation has depth 1
        assertEquals(1, e.countDepth(),"counting depth of an Operation");
        //test whether a binary operation contains 1 operation
        assertEquals(1, e.countOps());
        //test whether a binary operation contains 2 numbers
        assertEquals(2, e.countNbs());

    }

    @ParameterizedTest
    @ValueSource(strings = {"*", "+", "/", "-", "sqrt", "||", "<", ">"})
    void testRealOperationCounting (String symbol) {
        List<Expression> params = Arrays.asList(new MyNumber(new RealValue(value3BD)),new MyNumber(new RealValue(value4BD)));
        //Operation op = null;
        try {
            //construct another type of operation depending on the input value
            //of the parameterised test
            switch (symbol) {
                case "+"	->	e = new Plus(params);
                case "-"	->	e = new Minus(params);
                case "*"	->	e = new Times(params);
                case "/"	->	e = new Divides(params);
                case "sqrt"	->	e = new Sqrt(params);
                case "||"	->	e = new Modulus(params);
                case "<"	->	e = new LessThan(params);
                case ">"	->	e = new BiggerThan(params);
                default		->	fail();
            }
        } catch (IllegalConstruction e) {
            fail();
        }
        //test whether a binary operation has depth 1
        assertEquals(1, e.countDepth(),"counting depth of an Operation");
        //test whether a binary operation contains 1 operation
        assertEquals(1, e.countOps());
        //test whether a binary operation contains 2 numbers
        assertEquals(2, e.countNbs());

    }
}
