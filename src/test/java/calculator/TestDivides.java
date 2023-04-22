package calculator;

//Import Junit5 libraries for unit testing:
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class TestDivides {

    private final int value1 = 8;
    private final int value2 = 6;

    private final int imaginary1 = 3;

    private final int imaginary2 = 2;

    private final int denominator1 = 2;

    private final int denominator2 = 3;

    private final int denominator3 = 4;

    private final int denominator4 = 5;

    private final BigDecimal value3BD = new BigDecimal("8.278397");

    private final BigDecimal value4BD = new BigDecimal("6.187802");

    private Divides op, op2, op3, op4, op5;
    private List<Expression> params, params2, params3, params4, params5;

    @BeforeEach
    void setUp() {
        params = Arrays.asList(new MyNumber(value1), new MyNumber(value2));
        params2 = Arrays.asList(new MyNumber(value1, imaginary1), new MyNumber(value2, imaginary2));
        params3 = Arrays.asList(new MyNumber(new RationalValue(new IntegerValue(value1), new IntegerValue(denominator1))),
                new MyNumber(new RationalValue(new IntegerValue(value2), new IntegerValue(denominator3))));
        params4 = Arrays.asList(new MyNumber(new RationalValue(new IntegerValue(value1), new IntegerValue(denominator1)),
                new RationalValue(new IntegerValue(imaginary1), new IntegerValue(denominator2))),
                new MyNumber(new RationalValue(new IntegerValue(value2), new IntegerValue(denominator3)),
                        new RationalValue(new IntegerValue(imaginary2), new IntegerValue(denominator4))));
        params5 = Arrays.asList(new MyNumber(new RealValue(value3BD)), new MyNumber(new RealValue(value4BD)));
        try {
            op = new Divides(params);
            op.notation = Notation.INFIX; // reset the notation to infix (which is the default) before each test
            op2 = new Divides(params2, Notation.INFIX);
            op3 = new Divides(params3, Notation.INFIX);
            op4 = new Divides(params4, Notation.INFIX);
            op5 = new Divides(params5, Notation.INFIX);
        }
        catch(IllegalConstruction e) { fail(); }
    }

    @Test
    void testConstructor1() {
        // It should not be possible to create an expression without null parameter list
        assertThrows(IllegalConstruction.class, () -> op = new Divides(null));
    }

    @SuppressWarnings("AssertBetweenInconvertibleTypes")
    @Test
    void testConstructor2() {
        // An other expression than Divides should not be the same as a Divides expression
        try {
            assertNotSame(op, new PrimeNumbers(new ArrayList<>()));
            assertNotSame(op, new LessThan(new ArrayList<>()));
            assertNotSame(op, new General_Exponential(new ArrayList<>()));
            assertNotSame(op, new Factorial(new ArrayList<>()));
            assertNotSame(op, new BiggerThan(new ArrayList<>()));
            assertNotSame(op, new Plus(new ArrayList<>()));
            assertNotSame(op, new Minus(new ArrayList<>()));
            assertNotSame(op, new Times(new ArrayList<>()));
            assertNotSame(op, new Modulo(new ArrayList<>()));
        } catch (IllegalConstruction e) {
            fail();
        }
    }

    @Test
    void testEquals() {
        // Two similar expressions, constructed separately (and using different constructors) should be equal
        List<Expression> p = Arrays.asList(new MyNumber(value1), new MyNumber(value2));
        try {
            Divides d = new Divides(p, Notation.INFIX);
            assertEquals(op, d);
        }
        catch(IllegalConstruction e) { fail(); }
    }


    @Test
    void testComplexEquals() {
        // Two similar expressions, constructed separately (and using different constructors) should be equal
        List<Expression> p = Arrays.asList(new MyNumber(value1, imaginary1), new MyNumber(value2, imaginary2));
        try {
            Divides d = new Divides(p, Notation.INFIX);
            assertEquals(op2, d);
        }
        catch(IllegalConstruction e) { fail(); }
    }

    @Test
    void testRationnalEquals() {
        // Two similar expressions, constructed separately (and using different constructors) should be equal
        List<Expression> p = Arrays.asList(
                new MyNumber(new RationalValue (new IntegerValue(value1), new IntegerValue(denominator1))),
                new MyNumber(new RationalValue (new IntegerValue(value2), new IntegerValue(denominator3))));
        try {
            Divides d = new Divides(p, Notation.INFIX);
            assertEquals(op3, d);
        }
        catch(IllegalConstruction e) { fail(); }
    }

    @Test
    void testRationnalComplexEquals(){
        // Two similar expressions, constructed separately (and using different constructors) should be equal
        List<Expression> p = Arrays.asList(
                new MyNumber(new RationalValue (new IntegerValue(value1), new IntegerValue(denominator1)),
                        new RationalValue (new IntegerValue(imaginary1), new IntegerValue(denominator2))),
                new MyNumber(new RationalValue (new IntegerValue(value2), new IntegerValue(denominator3)),
                        new RationalValue (new IntegerValue(imaginary2), new IntegerValue(denominator4))));
        try {
            Divides d = new Divides(p, Notation.INFIX);
            assertEquals(op4, d);
        }
        catch(IllegalConstruction e) { fail(); }
    }

    @Test
    void testRealEquals(){
        // Two similar expressions, constructed separately (and using different constructors) should be equal
        List<Expression> p = Arrays.asList(
                new MyNumber(new RealValue(value3BD)),
                new MyNumber(new RealValue(value4BD)));
        try {
            Divides d = new Divides(p, Notation.INFIX);
            assertEquals(op5, d);
        }
        catch(IllegalConstruction e) { fail(); }
    }

    @SuppressWarnings("ConstantConditions")
    @Test
    void testNull() {
        assertDoesNotThrow(() -> op==null); // Direct way to test if the null case is handled.
    }

    @Test
    void testHashCode() {
        // Two similar expressions, constructed separately (and using different constructors) should have the same hashcode
        List<Expression> p = Arrays.asList(new MyNumber(value1), new MyNumber(value2));
        try {
            Divides e = new Divides(p, Notation.INFIX);
            assertEquals(e.hashCode(), op.hashCode());
        }
        catch(IllegalConstruction e) { fail(); }
    }

    @Test
    void testComplexHashCode() {
        // Two similar expressions, constructed separately (and using different constructors) should have the same hashcode
        List<Expression> p = Arrays.asList(new MyNumber(value1, imaginary1), new MyNumber(value2, imaginary2));

        try {
            Divides e = new Divides(p, Notation.INFIX);
            assertEquals(e.hashCode(), op2.hashCode());
        }
        catch(IllegalConstruction e) { fail(); }

    }

    @Test
    void testRationnalHashCode() {
        // Two similar expressions, constructed separately (and using different constructors) should have the same hashcode
        List<Expression> p = Arrays.asList(
                new MyNumber(new RationalValue (new IntegerValue(value1), new IntegerValue(denominator1))),
                new MyNumber(new RationalValue (new IntegerValue(value2), new IntegerValue(denominator3))));
        try {
            Divides e = new Divides(p, Notation.INFIX);
            assertEquals(e.hashCode(), op3.hashCode());
        }
        catch(IllegalConstruction e) { fail(); }
    }

    @Test
    void testRationnalComplexHashCode(){
        // Two similar expressions, constructed separately (and using different constructors) should have the same hashcode
        List<Expression> p = Arrays.asList(
                new MyNumber(new RationalValue (new IntegerValue(value1), new IntegerValue(denominator1)),
                        new RationalValue (new IntegerValue(imaginary1), new IntegerValue(denominator2))),
                new MyNumber(new RationalValue (new IntegerValue(value2), new IntegerValue(denominator3)),
                        new RationalValue (new IntegerValue(imaginary2), new IntegerValue(denominator4))));
        try {
            Divides e = new Divides(p, Notation.INFIX);
            assertEquals(e.hashCode(), op4.hashCode());
        }
        catch(IllegalConstruction e) { fail(); }
    }

    @Test
    void testRealHashCode(){
        // Two similar expressions, constructed separately (and using different constructors) should have the same hashcode
        List<Expression> p = Arrays.asList(
                new MyNumber(new RealValue(value3BD)),
                new MyNumber(new RealValue(value4BD)));
        try {
            Divides e = new Divides(p, Notation.INFIX);
            assertEquals(e.hashCode(), op5.hashCode());
        }
        catch(IllegalConstruction e) { fail(); }
    }

    @Test
    void testNullParamList() {
        params = null;
        assertThrows(IllegalConstruction.class, () -> op = new Divides(params));
    }

}
