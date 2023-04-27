package calculator;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TestGeneral_Exponential {
    private final int value1 = 8;
    private final int value2 = 6;

    private final int denominator1 = 2;

    private final int denominator2 = 3;

    private final BigDecimal value3BD = new BigDecimal("8.278397");

    private final BigDecimal value4BD = new BigDecimal("6.187802");

    private General_Exponential op, op2, op3;
    private List<Expression> params;



    @BeforeEach
    void setUp() {
        params = Arrays.asList(new MyNumber(value1), new MyNumber(value2));
        List<Expression> params2 = Arrays.asList(new MyNumber(new RationalValue(new IntegerValue(value1), new IntegerValue(denominator1))),
                new MyNumber(new RationalValue(new IntegerValue(value2), new IntegerValue(denominator2))));
        List<Expression> params3 = Arrays.asList(new MyNumber(new RealValue(value3BD)), new MyNumber(new RealValue(value4BD)));
        try {
            op = new General_Exponential(params);
            op.notation = Notation.INFIX; // reset the notation to infix (which is the default) before each test
            op2 = new General_Exponential(params2);
            op3 = new General_Exponential(params3);
        }
        catch(IllegalConstruction e) { fail(); }
    }

    @Test
    void testConstructor1() {
        // It should not be possible to create an expression without null parameter list
        assertThrows(IllegalConstruction.class, () -> op = new General_Exponential(null));
    }

    @SuppressWarnings("AssertBetweenInconvertibleTypes")
    @Test
    void testConstructor2() {
        // An other expression than General_Exponential should not be the same as a General_Exponential expression
        try {
            assertNotSame(op, new Plus(new ArrayList<>()));
            assertNotSame(op, new Minus(new ArrayList<>()));
            assertNotSame(op, new Times(new ArrayList<>()));
            assertNotSame(op, new Divides(new ArrayList<>()));
            assertNotSame(op, new Modulo(new ArrayList<>()));
            assertNotSame(op, new PrimeNumbers(new ArrayList<>()));
            assertNotSame(op, new BiggerThan(new ArrayList<>()));
            assertNotSame(op, new LessThan(new ArrayList<>()));
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
            General_Exponential d = new General_Exponential(p, Notation.INFIX);
            assertEquals(op, d);
        }
        catch(IllegalConstruction e) { fail(); }
    }

    @Test
    void testRationnalEquals(){
        // Two similar expressions, constructed separately (and using different constructors) should be equal
        List<Expression> p = Arrays.asList(new MyNumber(new RationalValue(new IntegerValue(value1), new IntegerValue(denominator1))),
                new MyNumber(new RationalValue(new IntegerValue(value2), new IntegerValue(denominator2))));
        try {
            General_Exponential d = new General_Exponential(p, Notation.INFIX);
            assertEquals(op2, d);
        } catch (IllegalConstruction e) {
            fail();
        }
    }

    @Test
    void testRealEquals(){
        // Two similar expressions, constructed separately (and using different constructors) should be equal
        List<Expression> p = Arrays.asList(new MyNumber(new RealValue(value3BD)), new MyNumber(new RealValue(value4BD)));
        try {
            General_Exponential d = new General_Exponential(p, Notation.INFIX);
            assertEquals(op3, d);
        } catch (IllegalConstruction e) {
            fail();
        }
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
            General_Exponential e = new General_Exponential(p, Notation.INFIX);
            assertEquals(e.hashCode(), op.hashCode());
        }
        catch(IllegalConstruction e) { fail(); }
    }

    @Test
    void testRationalHashCode(){
        // Two similar expressions, constructed separately (and using different constructors) should have the same hashcode
        List<Expression> p = Arrays.asList(new MyNumber(new RationalValue(new IntegerValue(value1), new IntegerValue(denominator1))),
                new MyNumber(new RationalValue(new IntegerValue(value2), new IntegerValue(denominator2))));
        try {
            General_Exponential e = new General_Exponential(p, Notation.INFIX);
            assertEquals(e.hashCode(), op2.hashCode());
        } catch (IllegalConstruction e) {
            fail();
        }
    }

    @Test
    void testRealHashCode(){
        // Two similar expressions, constructed separately (and using different constructors) should have the same hashcode
        List<Expression> p = Arrays.asList(new MyNumber(new RealValue(value3BD)), new MyNumber(new RealValue(value4BD)));
        try {
            General_Exponential e = new General_Exponential(p, Notation.INFIX);
            assertEquals(e.hashCode(), op3.hashCode());
        } catch (IllegalConstruction e) {
            fail();
        }
    }

    @Test
    void testNullParamList() {
        params = null;
        assertThrows(IllegalConstruction.class, () -> op = new General_Exponential(params));
    }


    @Test
    void testOp () {

        // The string representation should be "General_Exponential"

        assertEquals(new MyNumber(new RealValue(2.7182818285)).toString(), (op.op(new MyNumber(1))).toString());
        assertEquals(new MyNumber(new RealValue(1)).toString(), (op.op(new MyNumber(1), new MyNumber(2))).toString());


    }



}