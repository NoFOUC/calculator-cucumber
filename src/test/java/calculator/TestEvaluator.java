package calculator;

//Import Junit5 libraries for unit testing:
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.Arrays;
import java.util.List;

class TestEvaluator {

    private Calculator calc;
    private int value1, value2, value3;

    @BeforeEach
    void setUp() {
        calc = new Calculator();
        value1 = 3;
        value2 = 4;
        value3 = 5;
    }

    @Test
    void testEvaluatorMyNumber() {
        assertEquals( new IntegerValue(value1), calc.eval(new IntegerValue(value1)));
    }

    @ParameterizedTest
    @ValueSource(strings = {"*", "+", "/", "-"})
    void testIntegerEvaluateOperations(String symbol) {
        List<Expression> params = Arrays.asList(new IntegerValue(value1),new IntegerValue(value2));
        try {
            //construct another type of operation depending on the input value
            //of the parameterised test
            switch (symbol) {
                case "+"	->	assertEquals( new IntegerValue(value1 + value2), calc.eval(new Plus(params)));
                case "-"	->	assertEquals( new IntegerValue(value1 - value2), calc.eval(new Minus(params)));
                case "*"	->	assertEquals( new IntegerValue(value1 * value2), calc.eval(new Times(params)));
                case "/"	->	assertEquals( new IntegerValue(value1 / value2), calc.eval(new Divides(params)));
                default		->	fail();
            }
        } catch (IllegalConstruction e) {
            fail();
        }
    }

    @ParameterizedTest
    @ValueSource(strings = {"*", "+", "/", "-"})
    void testRealEvaluateOperations(String symbol) {

        RationalValue number1 = new RationalValue(new IntegerValue(value1),new IntegerValue(value2));
        RationalValue number2 = new RationalValue(new IntegerValue(value3),new IntegerValue(value2));
        RationalValue number3 = new RationalValue(new IntegerValue(value2),new IntegerValue(value3));

        List<Expression> params1 = Arrays.asList(number1, number2);
        List<Expression> params2 = Arrays.asList(number2, number3);
        try {
            //construct another type of operation depending on the input value
            //of the parameterised test
            switch (symbol) {
                case "+"	->	assertEquals(
                        new RationalValue( new IntegerValue(value1 + value3), new IntegerValue(value2)),
                        calc.eval(new Plus(params1))
                );
                case "-"	->	assertEquals(
                        new RationalValue( new IntegerValue(value1 - value3), new IntegerValue(value2)),
                        calc.eval(new Minus(params1))
                );
                case "*"	->	assertEquals( new IntegerValue(1), calc.eval(new Times(params2)));
                case "/"	->	assertEquals(
                        new RationalValue(new IntegerValue(value3*value3), new IntegerValue(value2*value2)),
                        calc.eval(new Divides(params2)));
                default		->	fail();
            }
        } catch (IllegalConstruction e) {
            fail();
        }
    }

}
