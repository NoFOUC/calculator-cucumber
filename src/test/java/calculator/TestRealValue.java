package calculator;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class TestRealValue {



    private BigDecimal value1 = new BigDecimal(8.2839748);

    private BigDecimal value2 = new BigDecimal(6.183873);

    private int value3 = 3;

    private int value4 = 52;


    private IntegerValue value5 = new IntegerValue(3);

    private double value6 = 3.0;


    private RealValue real1, real2, real3, real4, real5, real6;

    private MyNumber number1, number2, number3, number4, number5, number6;

    @BeforeEach
    void setUp() {
        real1 = new RealValue(value1);
        real2 = new RealValue(value2);
        real3 = new RealValue(value3);
        real4 = new RealValue(value4);
        real5 = new RealValue(value5);
        real6 = new RealValue(value6);

        number1 = new MyNumber(real1);
        number2 = new MyNumber(real2);
        number3 = new MyNumber(real3);
        number4 = new MyNumber(real4);
        number5 = new MyNumber(real5);
        number6 = new MyNumber(real6);
    }

    @Test
    void testEquals() {
        // Two values containing a distinct value should not be equal:
        assertNotEquals(number1, number2);
        assertNotEquals(number1, number3);
        assertNotEquals(number1, number4);
        assertNotEquals(number1, number5);
        assertNotEquals(number1, number6);

        assertNotEquals(number2, number3);
        assertNotEquals(number2, number4);
        assertNotEquals(number2, number5);
        assertNotEquals(number2, number6);

        assertEquals(number3, number5);
        assertNotEquals(number3, number4);
        assertNotEquals(number3, number6);


        assertNotEquals(number4, number5);

        assertEquals(number1, number1);
        assertEquals(number2, number2);
        assertEquals(number3, number3); // Identity check (for coverage, as this should always be true)
        assertEquals(number4, number4);
        assertEquals(number5, number5);

        assertNotEquals(number1, value1); // number is of type MyNumber, while value is of type BigDecimal, so not equal
        assertNotEquals(number2, value2); // number is of type MyNumber, while value is of type BigDecimal, so not equal
        assertNotEquals(number3, value3); // number is of type MyNumber, while value is of type int, so not equal
        assertNotEquals(number4, value4); // number is of type MyNumber, while value is of type int, so not equal

        try {
            assertNotEquals(new Times(new ArrayList<>()), real1);
        }
        catch (IllegalConstruction e) {fail();}
    }

    @Test
    void testToString() {
        assertEquals("8.283974799999999305555320461280643939971923828125 + 0i", number1.toString());
        assertEquals("6.1838730000000001751914169290103018283843994140625 + 0i", number2.toString());
        assertEquals("3 + 0i", number3.toString());
        assertEquals("52 + 0i", number4.toString());
    }






}
