package calculator;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
class TestDeg {

    private final int value1 = 8;

    private final int denominator1 = 2;

    private final BigDecimal value3BD = new BigDecimal("8.278397");

    private Deg op, op2, op3;
    private List<Expression> params, params2, params3;

    @BeforeEach
    void setUp() {
        params = Arrays.asList(new MyNumber(value1));
        params2 = Arrays.asList(new MyNumber(new RealValue(value3BD)));
        params3 = Arrays.asList(new MyNumber(
                        new RationalValue(new IntegerValue(value1), new IntegerValue(denominator1))));

        try {
            op = new Deg(params);
            op2 = new Deg(params2);
            op3 = new Deg(params3);
        } catch (IllegalConstruction e) {
            fail();
        }
    }

    @Test
    void testConstructor1() {
        // It should not be possible to create an expression without null parameter list
        assertThrows(IllegalConstruction.class, () -> op = new Deg(null));
    }

    @SuppressWarnings("AssertBetweenInconvertibleTypes")
    @Test
    void testConstructor2() {
        // An other than Deg expression should not be the same as a Deg expression
        try {
            assertNotSame(op, new Deg(new ArrayList<>()));
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
            assertNotSame(op, new Ln(new ArrayList<>()));
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
    void testEquals() {
        // Two similar expressions, constructed separately (and using different constructors) should not be equal
        List<Expression> p = Arrays.asList(new MyNumber(value1));
        try {
            Deg e = new Deg(p, Notation.INFIX);
            assertEquals(op, e);
        }
        catch(IllegalConstruction e) { fail(); }
    }

    @Test
    void testRationalEquals() {
        // Two similar expressions, constructed separately (and using different constructors) should not be equal
        List<Expression> p = Arrays.asList(new MyNumber(
                        new RationalValue(new IntegerValue(value1), new IntegerValue(denominator1))));
        try {
            Deg e = new Deg(p, Notation.INFIX);
            assertEquals(op3, e);
        } catch (IllegalConstruction e) {
            fail();
        }
    }

    @Test
    void testRealEquals() {
        // Two similar expressions, constructed separately (and using different constructors) should not be equal
        List<Expression> p = Arrays.asList(new MyNumber(new RealValue(value3BD)));
        try {
            Deg e = new Deg(p, Notation.INFIX);
            assertEquals(op2, e);
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
        List<Expression> p = Arrays.asList(new MyNumber(value1));
        try {
            Deg e = new Deg(p, Notation.INFIX);
            assertEquals(e.hashCode(), op.hashCode());
        } catch (IllegalConstruction e) {
            fail();
        }
    }

    @Test
    void testRationalHashCode() {
        // Two similar expressions, constructed separately (and using different constructors) should have the same hashcode
        List<Expression> p = Arrays.asList(new MyNumber(
                        new RationalValue(new IntegerValue(value1), new IntegerValue(denominator1))));
        try {
            Deg e = new Deg(p, Notation.INFIX);
            assertEquals(e.hashCode(), op3.hashCode());
        } catch (IllegalConstruction e) {
            fail();
        }
    }

    @Test
    void testRealHashCode() {
        // Two similar expressions, constructed separately (and using different constructors) should have the same hashcode
        List<Expression> p = Arrays.asList(new MyNumber(new RealValue(value3BD)));
        try {
            Deg e = new Deg(p, Notation.INFIX);
            assertEquals(e.hashCode(), op2.hashCode());
        } catch (IllegalConstruction e) {
            fail();
        }
    }

    @Test
    void testNullParamList() {
        params = null;
        assertThrows(IllegalConstruction.class, () -> op = new Deg(params));
    }
}
