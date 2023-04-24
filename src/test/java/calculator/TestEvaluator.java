package calculator;

//Import Junit5 libraries for unit testing:
import static java.lang.Math.sqrt;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.util.Arrays;
import java.util.List;

class TestEvaluator {

    private Calculator calc;
    private int value1, value2;

    private int imaginary1, imaginary2;

    private int denominator1, denominator2, denominator3, denominator4;

    private BigDecimal value3;
    private BigDecimal value4;

    @BeforeEach
    void setUp() {
        calc = new Calculator();
        value1 = 8;
        value2 = 6;
        imaginary1 = 2;
        imaginary2 = 3;
        denominator1 = 3;
        denominator2 = 4;
        denominator3 = 5;
        denominator4 = 6;
        value3 = new BigDecimal("8.278397");
        value4 = new BigDecimal("6.187802");

    }

    @Test
    void testEvaluatorMyNumber() throws IllegalConstruction {
        assertEquals(new MyNumber(value1), calc.eval(new MyNumber(value1)));
    }

    @Test
    void testEvaluatorMyComplexNumber() throws IllegalConstruction {
        assertEquals(new MyNumber(value1, imaginary1), calc.eval(new MyNumber(value1, imaginary1)));
    }

    @Test
    void testEvaluatorMyRationalNumber() throws IllegalConstruction {
        assertEquals(new MyNumber(new RationalValue(new IntegerValue(value1), new IntegerValue(denominator1))),
                calc.eval(new MyNumber(new RationalValue(new IntegerValue(value1), new IntegerValue(denominator1)))));
    }

    @Test
    void testEvaluatorMyComplexRationalNumber() throws IllegalConstruction {
        assertEquals(new MyNumber(new RationalValue(new IntegerValue(value1), new IntegerValue(denominator1)),
                        new RationalValue(new IntegerValue(imaginary1), new IntegerValue(denominator2))),
                calc.eval(new MyNumber(new RationalValue(new IntegerValue(value1), new IntegerValue(denominator1)),
                        new RationalValue(new IntegerValue(imaginary1), new IntegerValue(denominator2)))));
    }

    @Test
    void testEvaluatorMyRealNumber() throws IllegalConstruction {
        assertEquals(new MyNumber(new RealValue(value3)),
                calc.eval(new MyNumber(new RealValue(value3))));
    }

    @ParameterizedTest
    @ValueSource(strings = {"*", "+", "/", "-", "sqrt", "||"})
    void testEvaluateOperations(String symbol) {
        List<Expression> params = Arrays.asList(new MyNumber(value1), new MyNumber(value2));
        List<Expression> params2 = Arrays.asList(new MyNumber(value1));

        try {
            //construct another type of operation depending on the input value
            //of the parameterised test

            switch (symbol) {

                case "+" -> assertEquals(new MyNumber(value1 + value2), calc.eval(new Plus(params)));
                case "-" -> assertEquals(new MyNumber(value1 - value2), calc.eval(new Minus(params)));
                case "*" -> assertEquals(new MyNumber(value1 * value2), calc.eval(new Times(params)));
                case "/" -> assertEquals(new MyNumber(value1 / value2), calc.eval(new Divides(params)));
                case "sqrt" -> assertEquals(new MyNumber(new RealValue (sqrt(value1))), calc.eval(new Sqrt(params2)));
                case "||" -> assertEquals(new MyNumber(new IntegerValue((int) sqrt(value1 * value1))), calc.eval(new Modulus(params2)));
                default -> fail();
            }
        } catch (IllegalConstruction e) {
            fail();
        }
    }

    @ParameterizedTest
    @ValueSource(strings = {"*", "+", "/", "-", "sqrt", "||"})
    void testEvaluateOperationsOnComplex(String symbol) {
        List<Expression> params = Arrays.asList(new MyNumber(value1, imaginary1), new MyNumber(value2, imaginary2));
        try {
            //construct another type of operation depending on the input value
            //of the parameterised test
            switch (symbol) {
                case "+" -> assertEquals(new MyNumber(value1 + value2, imaginary1 + imaginary2), calc.eval(new Plus(params)));
                case "-" -> assertEquals(new MyNumber(value1 - value2, imaginary1 - imaginary2), calc.eval(new Minus(params)));
                case "*" ->
                        assertEquals(new MyNumber(value1 * value2 - imaginary1 * imaginary2, value1 * imaginary2 + value2 * imaginary1), calc.eval(new Times(params)));
                case "/" ->
                        assertEquals(new MyNumber((value1 * value2 + imaginary1 * imaginary2) / ((value2 * value2) + (imaginary2 * imaginary2)), ((imaginary1 * value2) - (value1 * imaginary2)) / ((value2 * value2) + (imaginary2 * imaginary2))), calc.eval(new Divides(params)));
                case "sqrt" ->
                        assertEquals(new MyNumber(new RealValue(Math.sqrt(((value1 * value1 + imaginary1 * imaginary1) + value1) / 2)), new RealValue(Math.sqrt((((value1 * value1 + imaginary1 * imaginary1) - value1) / 2) * imaginary1 / Math.abs(imaginary1)))), calc.eval(new Sqrt(params)));
                case "||" -> assertEquals(new MyNumber(new RealValue(sqrt(value1 * value1 + imaginary1 * imaginary1))), calc.eval(new Modulus(params)));
                default -> fail();
            }
        } catch (IllegalConstruction e) {
            fail();
        }
    }

    @ParameterizedTest
    @ValueSource(strings = {"*", "+", "/", "-"})
    void testEvaluateOperationOnRational(String symbol) {
        List<Expression> params = Arrays.asList(
                new MyNumber(new RationalValue(new IntegerValue(value1), new IntegerValue(denominator1))),
                new MyNumber(new RationalValue(new IntegerValue(value2), new IntegerValue(denominator2))));
        try {
            //construct another type of operation depending on the input value
            //of the parameterised test
            switch (symbol) {
                case "+" ->
                        assertEquals(new MyNumber(new RationalValue(new IntegerValue(value1 * denominator2 + value2 * denominator1), new IntegerValue(denominator1 * denominator2))), calc.eval(new Plus(params)));
                case "-" ->
                        assertEquals(new MyNumber(new RationalValue(new IntegerValue(value1 * denominator2 - value2 * denominator1), new IntegerValue(denominator1 * denominator2))), calc.eval(new Minus(params)));
                case "*" ->
                        assertEquals(new MyNumber(new RationalValue(new IntegerValue(value1 * value2), new IntegerValue(denominator1 * denominator2))), calc.eval(new Times(params)));
                case "/" ->
                        assertEquals(new MyNumber(new RationalValue(new IntegerValue(value1 * denominator2), new IntegerValue(denominator1 * value2))), calc.eval(new Divides(params)));
                // case "sqrt" ->  assertEquals( new MyNumber(new RationalValue (new IntegerValue(Math.sqrt(value1)), new IntegerValue((int) Math.sqrt(denominator1)))), calc.eval(new Sqrt(params)));
                // case "||"   ->  assertEquals(new MyNumber(new RationalValue (new IntegerValue((int) Math.sqrt(value1*value1)), new IntegerValue((int) Math.sqrt(denominator1*denominator1)))), calc.eval(new Modulus(params)));
                default -> fail();
            }
        } catch (IllegalConstruction e) {
            fail();
        }
    }

    @ParameterizedTest
    @ValueSource(strings = {"*", "+", "/", "-"})
    void testEvaluateOperationOnComplexRational(String symbol) {
        List<Expression> params = Arrays.asList(
                new MyNumber(new RationalValue(new IntegerValue(value1), new IntegerValue(denominator1)), new RationalValue(new IntegerValue(imaginary1), new IntegerValue(denominator3))),
                new MyNumber(new RationalValue(new IntegerValue(value2), new IntegerValue(denominator2)), new RationalValue(new IntegerValue(imaginary2), new IntegerValue(denominator4))));

        try {
            // construct another type of operation depending on the input value
            // of the parameterised test
            switch (symbol) {

                case "+" -> assertEquals(new MyNumber(new RationalValue(new IntegerValue(value1 * denominator2 + value2 * denominator1), new IntegerValue(denominator1 * denominator2)), new RationalValue(new IntegerValue(imaginary1 * denominator4 + imaginary2 * denominator3), new IntegerValue(denominator3 * denominator4))), calc.eval(new Plus(params)));
                case "-" -> assertEquals(new MyNumber(new RationalValue(new IntegerValue(value1 * denominator2 - value2 * denominator1), new IntegerValue(denominator1 * denominator2)), new RationalValue(new IntegerValue(imaginary1 * denominator4 - imaginary2 * denominator3), new IntegerValue(denominator3 * denominator4))), calc.eval(new Minus(params)));
                case "*" -> assertEquals(new MyNumber(new RationalValue(new IntegerValue(value1 * value2 * denominator3 * denominator4 - imaginary1 * imaginary2 * denominator1 * denominator2), new IntegerValue(denominator1 * denominator2 * denominator3 * denominator4)), new RationalValue(new IntegerValue(value1 * imaginary2 * denominator3 * denominator2 + value2 * imaginary1 * denominator1 * denominator4), new IntegerValue(denominator1 * denominator2 * denominator3 * denominator4))), calc.eval(new Times(params)));
                case "/" -> assertEquals(new MyNumber(new RationalValue(
                        new IntegerValue(((value1 * denominator3 * value2 * denominator4 + imaginary1 * denominator1 * imaginary2 * denominator2) * (denominator2 * denominator2 * denominator4 * denominator4))),
                        new IntegerValue(denominator1 * denominator2 * denominator3 * denominator4 * ((value2 * value2 * denominator4 * denominator4) + (imaginary2 * imaginary2 * denominator2 * denominator2)))),
                        new RationalValue(
                                new IntegerValue(((imaginary1 * denominator1 * value2 * denominator4 - value1 * denominator3 * imaginary2 * denominator2) * (denominator2 * denominator2 * denominator4 * denominator4))),
                                new IntegerValue(denominator1 * denominator2 * denominator3 * denominator4 * ((value2 * value2 * denominator4 * denominator4) + (imaginary2 * imaginary2 * denominator2 * denominator2))))), calc.eval(new Divides(params)));
                default -> fail();
            }
        } catch (IllegalConstruction e) {
            fail();
        }
    }

    @ParameterizedTest
    @ValueSource(strings = {"*", "+", "/", "-"})
    void testEvaluateOperationOnReal(String symbol) {
        List<Expression> params = Arrays.asList(
                new MyNumber(new RealValue(value3)),
                new MyNumber(new RealValue(value4)));

        try {
            System.out.println(new MyNumber(new RealValue(value3.divide(value4, new MathContext(13)))).getImaginary().getClass());
            System.out.println(calc.eval(new Divides(params)).getImaginary().getClass());
            // construct another type of operation depending on the input value
            // of the parameterised test
            switch (symbol) {
                case "+" -> assertEquals(new MyNumber(new RealValue(value3.add(value4))), calc.eval(new Plus(params)));
                case "-" -> assertEquals(new MyNumber(new RealValue(value3.subtract(value4))), calc.eval(new Minus(params)));
                case "/" -> assertEquals(new MyNumber(new RealValue(value3.divide(value4, 12, RoundingMode.HALF_UP))), calc.eval(new Divides(params)));
                case "*" -> assertEquals(new MyNumber(new RealValue(value3.multiply(value4))), calc.eval(new Times(params)));
            }

        } catch (IllegalConstruction e) {
            fail();
        }
    }

}