package calculator;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Arrays;
import java.util.List;

public class TestCalculator {


    int value1 = 8;
    int value2 = 6;

    List<Expression> params;

    Plus op;

    Calculator calc;
    private final PrintStream standardOut = System.out;
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();


    @BeforeEach
    void setup () {
        System.setOut(new PrintStream(outputStreamCaptor));
        calc = new Calculator();
        params = Arrays.asList(new MyNumber(value1), new MyNumber(value2));

        try {
            op = new Plus(params);
        } catch (IllegalConstruction e) {
            throw new RuntimeException(e);
        }
    }


    @Test
    void testPrint() throws IllegalConstruction {

        assertEquals("The result of evaluating expression calculator.Plus@24fcf\n" +
                "is: 14.", calc.getExpressionResult(op).trim());

    }

    @Test
    void testPrintExpressionDetails () throws IllegalConstruction {
        assertEquals("The result of evaluating expression calculator.Plus@24fcf\n" +
                "is: 14.\n" +
                "\n" +
                "It contains 1 levels of nested expressions, 1 operations and 2 numbers.",
                calc.getExpressionDetails(op).trim());

    }


    @AfterEach
    public void tearDown() {
        System.setOut(standardOut);
    }


}
