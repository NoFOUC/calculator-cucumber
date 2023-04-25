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

    private double value7 = 2.0;

    private int precision = 5;


    private RealValue real1, real2, real3, real4, real5, real6, real7;

    private MyNumber number1, number2, number3, number4, number5, number6, number7;

    @BeforeEach
    void setUp() {
        real1 = new RealValue(value1);
        real2 = new RealValue(value2);
        real3 = new RealValue(value3);
        real4 = new RealValue(value4, precision);
        real5 = new RealValue(value5);
        real6 = new RealValue(value6);
        real7 = new RealValue(value7);


        number1 = new MyNumber(real1);
        number2 = new MyNumber(real2);
        number3 = new MyNumber(real3);
        number4 = new MyNumber(real4);
        number5 = new MyNumber(real5);
        number6 = new MyNumber(real6);
        number7 = new MyNumber(real7);
    }

    @Test
    void testEquals() {
        // Two values containing a distinct value should not be equal:
        assertNotEquals(number1, number2);
        assertNotEquals(number1, number3);
        assertNotEquals(number1, number4);
        assertNotEquals(number1, number5);
        assertNotEquals(number1, number6);
        assertNotEquals(number1, number7);

        assertNotEquals(number2, number3);
        assertNotEquals(number2, number4);
        assertNotEquals(number2, number5);
        assertNotEquals(number2, number6);
        assertNotEquals(number2, number7);

        assertEquals(number3, number5);
        assertNotEquals(number3, number4);
        assertEquals(number3, number6);
        assertNotEquals(number3, number7);


        assertNotEquals(number4, number5);
        assertNotEquals(number4, number6);
        assertNotEquals(number4, number7);

        assertEquals(number5, number6);
        assertNotEquals(number5, number7);

        assertNotEquals(number6, number7);

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
        assertEquals("8.2839747999", number1.toString());
        assertEquals("6.1838730000", number2.toString());
        assertEquals("3", number3.toString());
        assertEquals("52", number4.toString());
    }






}
