package calculator;

//Import Junit5 libraries for unit testing:
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class TestTimes {

	private final int value1 = 8;
	private final int value2 = 6;

	private final int imaginary1 = 3;

	private final int imaginary2 = 2;

	private final int denominator1 = 2;

	private final int denominator2 = 3;

	private final int denominator3 = 4;

	private final int denominator4 = 5;
	private Times op, op2, op3, op4;
	private List<Expression> params, params2, params3, params4;

	@BeforeEach
	void setUp() {
		  params = Arrays.asList(new MyNumber(value1),new MyNumber(value2));
		  params2 = Arrays.asList(new MyNumber(value1, imaginary1),new MyNumber(value2, imaginary2));
		  params3 = Arrays.asList(new MyNumber(new RationalValue(new IntegerValue(value1), new IntegerValue(denominator1))),
				  new MyNumber(new RationalValue(new IntegerValue(value2), new IntegerValue(denominator3))));
		  params4 = Arrays.asList(new MyNumber(new RationalValue(new IntegerValue(value1), new IntegerValue(denominator1)),
		  				  new RationalValue(new IntegerValue(imaginary1), new IntegerValue(denominator2))),
				  new MyNumber(new RationalValue(new IntegerValue(value2), new IntegerValue(denominator3)),
						  new RationalValue(new IntegerValue(imaginary2), new IntegerValue(denominator4))));
		  try { op = new Times(params);
		  		op2 = new Times(params2);
		  		op3 = new Times(params3);
		  		op4 = new Times(params4);
		  }
		  catch(IllegalConstruction e) { fail(); }
	}

	@Test
	void testConstructor1() {
		// It should not be possible to create an expression without null parameter list
		assertThrows(IllegalConstruction.class, () -> op = new Times(null));
	}

	@Test
	void testConstructor2() {
		// A Plus expression should not be the same as a Times expression
		try {
			assertNotSame(op, new PrimeNumbers(new ArrayList<>()));
			assertNotSame(op, new LessThan(new ArrayList<>()));
			assertNotSame(op, new General_Exponential(new ArrayList<>()));
			assertNotSame(op, new Factorial(new ArrayList<>()));
			assertNotSame(op, new BiggerThan(new ArrayList<>()));
			assertNotSame(op, new Plus(new ArrayList<>()));
			assertNotSame(op, new Minus(new ArrayList<>()));
			assertNotSame(op, new Modulo(new ArrayList<>()));
			assertNotSame(op, new Divides(new ArrayList<>()));
		} catch (IllegalConstruction e) {
			fail();
		}
	}

	@Test
	void testEquals() {
		// Two similar expressions, constructed separately (and using different constructors) should not be equal
		List<Expression> p = Arrays.asList(new MyNumber(value1), new MyNumber(value2));
		try {
			Times e = new Times(p, Notation.INFIX);
			assertEquals(op, e);
		}
		catch(IllegalConstruction e) { fail(); }
	}

	@Test
	void testComplexEquals() {
		// Two similar expressions, constructed separately (and using different constructors) should not be equal
		List<Expression> p = Arrays.asList(new MyNumber(value1, imaginary1), new MyNumber(value2, imaginary2));
		try {
			Times e = new Times(p, Notation.INFIX);
			assertEquals(op2, e);
		}
		catch(IllegalConstruction e) { fail(); }
	}

	@Test
	void testRationalEquals() {
		// Two similar expressions, constructed separately (and using different constructors) should not be equal
		List<Expression> p = Arrays.asList(new MyNumber(new RationalValue(new IntegerValue(value1), new IntegerValue(denominator1))),
				  new MyNumber(new RationalValue(new IntegerValue(value2), new IntegerValue(denominator3))));
		try {
			Times e = new Times(p, Notation.INFIX);
			assertEquals(op3, e);
		}
		catch(IllegalConstruction e) { fail(); }
	}

	@Test
	void testComplexRationalEquals() {
		// Two similar expressions, constructed separately (and using different constructors) should not be equal
		List<Expression> p = Arrays.asList(new MyNumber(new RationalValue(new IntegerValue(value1), new IntegerValue(denominator1)),
		  				  new RationalValue(new IntegerValue(imaginary1), new IntegerValue(denominator2))),
				  new MyNumber(new RationalValue(new IntegerValue(value2), new IntegerValue(denominator3)),
						  new RationalValue(new IntegerValue(imaginary2), new IntegerValue(denominator4))));
		try {
			Times e = new Times(p, Notation.INFIX);
			assertEquals(op4, e);
		}
		catch(IllegalConstruction e) { fail(); }
	}

	@Test
	void testNull() {
		assertDoesNotThrow(() -> op==null); // Direct way to test if the null case is handled.
	}

	@Test
	void testHashCode() {
		// Two similar expressions, constructed separately (and using different constructors) should have the same hashcode
		List<Expression> p = Arrays.asList(new MyNumber(value1), new MyNumber(value2));
		try {
			Times e = new Times(p, Notation.INFIX);
			assertEquals(e.hashCode(), op.hashCode());
		}
		catch(IllegalConstruction e) { fail(); }
	}

	@Test
	void testComplexHashCode() {
		// Two similar expressions, constructed separately (and using different constructors) should have the same hashcode
		List<Expression> p = Arrays.asList(new MyNumber(value1, imaginary1), new MyNumber(value2, imaginary2));
		try {
			Times e = new Times(p, Notation.INFIX);
			assertEquals(e.hashCode(), op2.hashCode());
		}
		catch(IllegalConstruction e) { fail(); }
	}

	@Test
	void testRationalHashCode() {
		// Two similar expressions, constructed separately (and using different constructors) should have the same hashcode
		List<Expression> p = Arrays.asList(new MyNumber(new RationalValue(new IntegerValue(value1), new IntegerValue(denominator1))),
				  new MyNumber(new RationalValue(new IntegerValue(value2), new IntegerValue(denominator3))));
		try {
			Times e = new Times(p, Notation.INFIX);
			assertEquals(e.hashCode(), op3.hashCode());
		}
		catch(IllegalConstruction e) { fail(); }
	}

	@Test
	void testComplexRationalHashCode() {
		// Two similar expressions, constructed separately (and using different constructors) should have the same hashcode
		List<Expression> p = Arrays.asList(new MyNumber(new RationalValue(new IntegerValue(value1), new IntegerValue(denominator1)),
		  				  new RationalValue(new IntegerValue(imaginary1), new IntegerValue(denominator2))),
				  new MyNumber(new RationalValue(new IntegerValue(value2), new IntegerValue(denominator3)),
						  new RationalValue(new IntegerValue(imaginary2), new IntegerValue(denominator4))));
		try {
			Times e = new Times(p, Notation.INFIX);
			assertEquals(e.hashCode(), op4.hashCode());
		}
		catch(IllegalConstruction e) { fail(); }
	}

	@Test
	void testNullParamList() {
		params = null;
		assertThrows(IllegalConstruction.class, () -> op = new Times(params));
	}

}
