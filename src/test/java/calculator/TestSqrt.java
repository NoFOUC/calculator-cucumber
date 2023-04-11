package calculator;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertNotSame;

public class TestSqrt {

    private final int value1 = 8;
    private final int imaginary1 = 3;
    private final int value2 = 6;
    private final int imaginary2 = 2;
    private Sqrt op1, op2;
    private List<Expression> params1;
    private List<Expression> params2;

    @BeforeEach
    void setUp() {
        params1 = new ArrayList<>(Arrays.asList(new MyNumber(value1),new MyNumber(value2)));
        params2 = new ArrayList<>(Arrays.asList(new MyNumber(value1, imaginary1),new MyNumber(value2, imaginary2)));
        try { op1 = new Sqrt(params1);
            op2 = new Sqrt(params2);
        }
        catch(IllegalConstruction e) { fail(); }

    }

    @Test
    void testConstructor1() {
        // It should not be possible to create a Sqrt expression without null parameter list
        assertThrows(IllegalConstruction.class, () -> op1 = new Sqrt(null));
    }

    @Test
    void testConstructor2() {
        // A Times expression should not be the same as a Sqrt expression
        try {
            assertNotSame(op1, new Times(new ArrayList<>()));
            assertNotSame(op2, new Times(new ArrayList<>()));
        } catch (IllegalConstruction e) {
            fail();
        }
    }

    @Test
    void testEquals() {
        // Two similar expressions, constructed separately (and using different constructors) should be equal
        ArrayList<Expression> p1 = new ArrayList<>(Arrays.asList(new MyNumber(value1), new MyNumber(value2)));
        try {
            Sqrt e = new Sqrt(p1, Notation.INFIX);
            assertEquals(op1, e);
            assertEquals(e, e);
            assertNotEquals(e, new Sqrt(new ArrayList<>(Arrays.asList(new MyNumber(5), new MyNumber(4))), Notation.INFIX));
        }
        catch(IllegalConstruction e) { fail(); }
    }

    @Test
    void testComplexEquals(){
        ArrayList<Expression> p1 = new ArrayList<>(Arrays.asList(new MyNumber(value1, imaginary1), new MyNumber(value2, imaginary2)));
        try {
            Sqrt e = new Sqrt(p1, Notation.INFIX);
            assertEquals(op2, e);
            assertEquals(e, e);
            assertNotEquals(e, new Sqrt(new ArrayList<>(Arrays.asList(new MyNumber(5, 2), new MyNumber(4, 1))), Notation.INFIX));
        }
        catch(IllegalConstruction e) { fail(); }
    }

    @SuppressWarnings("ConstantConditions")
    @Test
    void testNull() {
        assertDoesNotThrow(() -> op1==null);
        assertDoesNotThrow(() -> op2==null); // Direct way to to test if the null case is handled.
    }

    @Test
    void testHashCode() {
        // Two similar expressions, constructed separately (and using different constructors) should have the same hashcode
        ArrayList<Expression> p = new ArrayList<>(Arrays.asList(new MyNumber(value1), new MyNumber(value2)));
        try {
            Sqrt e = new Sqrt(p, Notation.INFIX);
            assertEquals(e.hashCode(), op1.hashCode());
        }
        catch(IllegalConstruction e) { fail(); }
    }

    @Test
    void testComplexHashCode() {
        ArrayList<Expression> p = new ArrayList<>(Arrays.asList(new MyNumber(value1, imaginary1), new MyNumber(value2, imaginary2)));
        try {
            Sqrt e = new Sqrt(p, Notation.INFIX);
            assertEquals(e.hashCode(), op2.hashCode());
        }
        catch(IllegalConstruction e) { fail(); }
    }
    @Test
    void testNullParamList() {
        params1 = null;
        assertThrows(IllegalConstruction.class, () -> op1 = new Sqrt(params1));
        params2 = null;
        assertThrows(IllegalConstruction.class, () -> op2 = new Sqrt(params2));
    }
}
