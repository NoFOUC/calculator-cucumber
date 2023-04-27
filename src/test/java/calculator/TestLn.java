package calculator;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
class TestLn {

    private final int value1 = 8;
    private final int value2 = 6;


    private final int denominator1 = 2;
    private final int denominator3 = 4;

    private final BigDecimal value3BD = new BigDecimal("8.278397");

    private final BigDecimal value4BD = new BigDecimal("6.187802");

    private Ln op;
    private Ln op3;
    private Ln op5;
    private List<Expression> params;

    @BeforeEach
    void setUp() {
        params = Arrays.asList(new MyNumber(value1), new MyNumber(value2));
        List<Expression> params3 = Arrays.asList(new MyNumber(
                        new RationalValue(new IntegerValue(value1), new IntegerValue(denominator1))),
                new MyNumber(
                        new RationalValue(new IntegerValue(value2), new IntegerValue(denominator3))));
        List<Expression> params5 = Arrays.asList(new MyNumber(new RealValue(value3BD)), new MyNumber(new RealValue(value4BD)));

        try {
            op = new Ln(params);
            op3 = new Ln(params3);
            op5 = new Ln(params5);
        } catch (IllegalConstruction e) {
            fail();
        }
    }

    @Test
    void testConstructor1() {
        // It should not be possible to create an expression without null parameter list
        assertThrows(IllegalConstruction.class, () -> op = new Ln(null));
    }

    @SuppressWarnings("AssertBetweenInconvertibleTypes")
    @Test
    void testConstructor2() {
        // An other than Ln expression should not be the same as a Ln expression
        try {
            assertNotSame(op, new Plus(new ArrayList<>()));
            assertNotSame(op, new Minus(new ArrayList<>()));
            assertNotSame(op, new Times(new ArrayList<>()));
            assertNotSame(op, new Divides(new ArrayList<>()));
            assertNotSame(op, new Modulo(new ArrayList<>()));
            assertNotSame(op, new PrimeNumbers(new ArrayList<>()));
            assertNotSame(op, new BiggerThan(new ArrayList<>()));
            assertNotSame(op, new LessThan(new ArrayList<>()));
            assertNotSame(op, new General_Exponential(new ArrayList<>()));
            assertNotSame(op, new Sqrt(new ArrayList<>()));
            assertNotSame(op, new Inverse(new ArrayList<>()));
            assertNotSame(op, new Factorial(new ArrayList<>()));
            assertNotSame(op, new Cosinus(new ArrayList<>()));
            assertNotSame(op, new Sinus(new ArrayList<>()));
            assertNotSame(op, new Tan(new ArrayList<>()));
            assertNotSame(op, new Cot(new ArrayList<>()));
            assertNotSame(op, new ArcCos(new ArrayList<>()));
            assertNotSame(op, new ArcSin(new ArrayList<>()));
            assertNotSame(op, new ArcTan(new ArrayList<>()));
            assertNotSame(op, new ArcCot(new ArrayList<>()));
        } catch (IllegalConstruction e) {
            fail();
        }
    }

    @Test
    void testError() throws IllegalConstruction {
        // It should throw an IllegalArgumentException for the Inverse function if the parameter is 0.
        List<Expression> p = Arrays.asList(new MyNumber(-1), new MyNumber(-2));

        Ln ln1 = new Ln(p);

        assertThrows(IllegalArgumentException.class, () -> ln1.op(new MyNumber(-3)));
    }

    @Test
    void testEquals() {
        // Two similar expressions, constructed separately (and using different constructors) should not be equal
        List<Expression> p = Arrays.asList(new MyNumber(value1), new MyNumber(value2));
        try {
            Ln e = new Ln(p, Notation.INFIX);
            assertEquals(op, e);
        }
        catch(IllegalConstruction e) { fail(); }
    }

    @Test
    void testRationalEquals() {
        // Two similar expressions, constructed separately (and using different constructors) should not be equal
        List<Expression> p = Arrays.asList(new MyNumber(
                        new RationalValue(new IntegerValue(value1), new IntegerValue(denominator1))),
                new MyNumber(
                        new RationalValue(new IntegerValue(value2), new IntegerValue(denominator3))));
        try {
            Ln e = new Ln(p, Notation.INFIX);
            assertEquals(op3, e);
        } catch (IllegalConstruction e) {
            fail();
        }
    }

    @Test
    void testRealEquals() {
        // Two similar expressions, constructed separately (and using different constructors) should not be equal
        List<Expression> p = Arrays.asList(new MyNumber(new RealValue(value3BD)),new MyNumber(new RealValue(value4BD)));
        try {
            Ln e = new Ln(p, Notation.INFIX);
            assertEquals(op5, e);
        } catch (IllegalConstruction e) {
            fail();
        }
    }

    @Test
    void testNull() {
        assertDoesNotThrow(() -> op==null); // Direct way to to test if the null case is handled.
    }

    @Test
    void testHashCode() {
        // Two similar expressions, constructed separately (and using different constructors) should have the same hashcode
        List<Expression> p = Arrays.asList(new MyNumber(value1), new MyNumber(value2));
        try {
            Ln e = new Ln(p, Notation.INFIX);
            assertEquals(e.hashCode(), op.hashCode());
        }
        catch(IllegalConstruction e) { fail(); }
    }

    @Test
    void testRationalHashCode() {
        // Two similar expressions, constructed separately (and using different constructors) should have the same hashcode
        List<Expression> p = Arrays.asList(new MyNumber(
                        new RationalValue(new IntegerValue(value1), new IntegerValue(denominator1))),
                new MyNumber(
                        new RationalValue(new IntegerValue(value2), new IntegerValue(denominator3))));
        try {
            Ln e = new Ln(p, Notation.INFIX);
            assertEquals(e.hashCode(), op3.hashCode());
        } catch (IllegalConstruction e) {
            fail();
        }
    }

    @Test
    void testRealHashCode() {
        // Two similar expressions, constructed separately (and using different constructors) should have the same hashcode
        List<Expression> p = Arrays.asList(new MyNumber(new RealValue(value3BD)),new MyNumber(new RealValue(value4BD)));
        try {
            Ln e = new Ln(p, Notation.INFIX);
            assertEquals(e.hashCode(), op5.hashCode());
        } catch (IllegalConstruction e) {
            fail();
        }
    }

    @Test
    void testNullParamList() {
        params = null;
        assertThrows(IllegalConstruction.class, () -> op = new Ln(params));
    }
}
