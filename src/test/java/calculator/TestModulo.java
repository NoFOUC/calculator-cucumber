package calculator;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
public class TestModulo {
    private final int value1 = 8;
    private final int value2 = 6;

    private Modulo op;
    private List<Expression> params;

    @BeforeEach
    void setUp() {
        params = Arrays.asList(new MyNumber(value1), new MyNumber(value2));
        try {
            op = new Modulo(params);
            op.notation = Notation.INFIX; // reset the notation to infix (which is the default) before each test
        }
        catch(IllegalConstruction e) { fail(); }
    }

    @Test
    void testConstructor1() {
        // It should not be possible to create an expression without null parameter list
        assertThrows(IllegalConstruction.class, () -> op = new Modulo(null));
    }

    @SuppressWarnings("AssertBetweenInconvertibleTypes")
    @Test
    void testConstructor2() {
        // An other expression than Modulo should not be the same as a Divides expression
        try {
            assertNotSame(op, new Plus(new ArrayList<>()));
            assertNotSame(op, new Minus(new ArrayList<>()));
            assertNotSame(op, new Times(new ArrayList<>()));
            assertNotSame(op, new Divides(new ArrayList<>()));
            assertNotSame(op, new PrimeNumbers(new ArrayList<>()));
            assertNotSame(op, new BiggerThan(new ArrayList<>()));
            assertNotSame(op, new LessThan(new ArrayList<>()));
            assertNotSame(op, new General_Exponential(new ArrayList<>()));
            assertNotSame(op, new Sqrt(new ArrayList<>()));
            assertNotSame(op, new Inverse(new ArrayList<>()));
            assertNotSame(op, new Factorial(new ArrayList<>()));
            assertNotSame(op, new Ln(new ArrayList<>()));
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
    void testEquals() {
        // Two similar expressions, constructed separately (and using different constructors) should be equal
        List<Expression> p = Arrays.asList(new MyNumber(value1), new MyNumber(value2));
        try {
            Modulo d = new Modulo(p, Notation.INFIX);
            assertEquals(op, d);
        }
        catch(IllegalConstruction e) { fail(); }
    }

    @SuppressWarnings("ConstantConditions")
    @Test
    void testNull() {
        assertDoesNotThrow(() -> op==null); // Direct way to to test if the null case is handled.
    }

    @Test
    void testHashCode() {
        // Two similar expressions, constructed separately (and using different constructors) should have the same hashcode
        List<Expression> p = Arrays.asList(new MyNumber(value1), new MyNumber(value2));
        try {
            Modulo e = new Modulo(p, Notation.INFIX);
            assertEquals(e.hashCode(), op.hashCode());
        }
        catch(IllegalConstruction e) { fail(); }
    }

    @Test
    void testNullParamList() {
        params = null;
        assertThrows(IllegalConstruction.class, () -> op = new Modulo(params));
    }
}
