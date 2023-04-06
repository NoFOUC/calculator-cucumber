package calculator;

//Import Junit5 libraries for unit testing:
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class TestPlus {

	private final int value1 = 8;
	private final int imaginary1 = 3;
	private final int value2 = 6;
	private final int imaginary2 = 2;
	private Plus op, op2;
	private List<Expression> params1;
	private List<Expression> params2;

	@BeforeEach
	void setUp() {
		  params1 = new ArrayList<>(Arrays.asList(new MyNumber(value1),new MyNumber(value2)));
		  params2 = new ArrayList<>(Arrays.asList(new MyNumber(value1, imaginary1),new MyNumber(value2, imaginary2)));
		  try { op = new Plus(params1);
			  op2 = new Plus(params2);
		  }
		  catch(IllegalConstruction e) { fail(); }
	}

	@Test
	void testConstructor1() {
		// It should not be possible to create a Plus expression without null parameter list
		assertThrows(IllegalConstruction.class, () -> op1 = new Plus(null));
	}

	@SuppressWarnings("AssertBetweenInconvertibleTypes")
	@Test
	void testConstructor2() {
		// A Times expression should not be the same as a Plus expression
		try {
			assertNotSame(op, new Times(new ArrayList<>()));
			assertNotSame(op, new PrimeNumbers(new ArrayList<>()));
			assertNotSame(op, new LessThan(new ArrayList<>()));
			assertNotSame(op, new General_Exponential(new ArrayList<>()));
			assertNotSame(op, new Factorial(new ArrayList<>()));
			assertNotSame(op, new BiggerThan(new ArrayList<>()));
			assertNotSame(op, new Modulo(new ArrayList<>()));
			assertNotSame(op, new Minus(new ArrayList<>()));
			assertNotSame(op, new Times(new ArrayList<>()));
			assertNotSame(op, new Divides(new ArrayList<>()));
		} catch (IllegalConstruction e) {
			fail();
		}
	}

	@Test
	void testEquals() {
		// Two similar expressions, constructed separately (and using different constructors) should be equal
		ArrayList<Expression> p1 = new ArrayList<>(Arrays.asList(new MyNumber(value1), new MyNumber(value2)));
		try {
			Plus e = new Plus(p1, Notation.INFIX);
			assertEquals(op, e);
			assertEquals(e, e);
			assertNotEquals(e, new Plus(new ArrayList<>(Arrays.asList(new MyNumber(5), new MyNumber(4))), Notation.INFIX));
		}
		catch(IllegalConstruction e) { fail(); }
	}

	@Test
	void testComplexEquals(){
		ArrayList<Expression> p1 = new ArrayList<>(Arrays.asList(new MyNumber(value1, imaginary1), new MyNumber(value2, imaginary2)));
		try {
			Plus e = new Plus(p1, Notation.INFIX);
			assertEquals(op2, e);
			assertEquals(e, e);
			assertNotEquals(e, new Plus(new ArrayList<>(Arrays.asList(new MyNumber(5, 2), new MyNumber(4, 1))), Notation.INFIX));
		}
		catch(IllegalConstruction e) { fail(); }
	}

	@SuppressWarnings("ConstantConditions")
	@Test
	void testNull() {
			assertDoesNotThrow(() -> op==null);
			assertDoesNotThrow(() -> op2==null); // Direct way to to test if the null case is handled.
	}

	@Test
	void testHashCode() {
		// Two similar expressions, constructed separately (and using different constructors) should have the same hashcode
		ArrayList<Expression> p = new ArrayList<>(Arrays.asList(new MyNumber(value1), new MyNumber(value2)));
		try {
			Plus e = new Plus(p, Notation.INFIX);
			assertEquals(e.hashCode(), op.hashCode());
		}
		catch(IllegalConstruction e) { fail(); }
	}

	@Test
	void testComplexHashCode() {
		ArrayList<Expression> p = new ArrayList<>(Arrays.asList(new MyNumber(value1, imaginary1), new MyNumber(value2, imaginary2)));
		try {
			Plus e = new Plus(p, Notation.INFIX);
			assertEquals(e.hashCode(), op2.hashCode());
		}
		catch(IllegalConstruction e) { fail(); }
	}
	@Test
	void testNullParamList() {
		params1 = null;
		assertThrows(IllegalConstruction.class, () -> op = new Plus(params1));
		params2 = null;
		assertThrows(IllegalConstruction.class, () -> op2 = new Plus(params2));
	}

}
