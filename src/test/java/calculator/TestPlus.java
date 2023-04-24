package calculator;

//Import Junit5 libraries for unit testing:
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class TestPlus {

	private final int value1 = 8;
	private final int imaginary1 = 3;
	private final int value2 = 6;
	private final int imaginary2 = 2;

	private final int denominator1 = 2;

	private final int denominator2 = 3;

	private final int denominator3 = 4;

	private final int denominator4 = 5;

	private final BigDecimal value3BD = new BigDecimal("8.278397");

	private final BigDecimal value4BD = new BigDecimal("6.187802");

	private final BigDecimal value5BD = new BigDecimal("14.466199");

	private final BigDecimal value6BD = new BigDecimal("8.466199");

	private Plus op, op2, op3, op4, op5, op6;
	private List<Expression> params1, params2;

	@BeforeEach
	void setUp() {
		  params1 = new ArrayList<>(Arrays.asList(new MyNumber(value1),new MyNumber(value2)));
		  params2 = new ArrayList<>(Arrays.asList(new MyNumber(value1, imaginary1),new MyNumber(value2, imaginary2)));
		  List<Expression> params3 = new ArrayList<>(Arrays.asList(new MyNumber(new RationalValue(new IntegerValue(value1), new IntegerValue(denominator1))),
				  new MyNumber(new RationalValue(new IntegerValue(value2), new IntegerValue(denominator3)))));
		  List<Expression> params4 = new ArrayList<>(Arrays.asList(new MyNumber(new RationalValue(new IntegerValue(value1), new IntegerValue(denominator1)),
				  new RationalValue(new IntegerValue(imaginary1), new IntegerValue(denominator2))),
				  new MyNumber(new RationalValue(new IntegerValue(value2), new IntegerValue(denominator3)),
						  new RationalValue(new IntegerValue(imaginary2), new IntegerValue(denominator4)))));
		  List<Expression> params5 = new ArrayList<>(Arrays.asList(new MyNumber(new RealValue(value3BD)),new MyNumber(new RealValue(value4BD))));
		  List<Expression> params6 = new ArrayList<>(Arrays.asList(new MyNumber(new RealValue(value3BD), new RealValue(value5BD)),
				  new MyNumber(new RealValue(value4BD), new RealValue(value6BD))));

		  try { op = new Plus(params1);
			  op2 = new Plus(params2);
			  op3 = new Plus(params3);
			  op4 = new Plus(params4);
			  op5 = new Plus(params5);
			  op6 = new Plus(params6);
		  }
		  catch(IllegalConstruction e) { fail(); }
	}

	@Test
	void testConstructor1() {
		// It should not be possible to create a Plus expression without null parameter list
		assertThrows(IllegalConstruction.class, () -> op = new Plus(null));
	}

	@SuppressWarnings("AssertBetweenInconvertibleTypes")
	@Test
	void testConstructor2() {
		// A Times expression should not be the same as a Plus expression
		try {
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

	@Test
	void testRationalEquals() {
		ArrayList<Expression> p1 = new ArrayList<>(Arrays.asList(new MyNumber(new RationalValue(new IntegerValue(value1), new IntegerValue(denominator1))),
				new MyNumber(new RationalValue(new IntegerValue(value2), new IntegerValue(denominator3)))));
		try {
			Plus e = new Plus(p1, Notation.INFIX);
			assertEquals(op3, e);
			assertEquals(e, e);
			assertNotEquals(e, new Plus(new ArrayList<>(Arrays.asList(new MyNumber(5, 2), new MyNumber(4, 1))), Notation.INFIX));
		}
		catch(IllegalConstruction e) { fail(); }
	}

	@Test
	void testComplexRationalEquals() {
		ArrayList<Expression> p1 = new ArrayList<>(Arrays.asList(new MyNumber(new RationalValue(new IntegerValue(value1), new IntegerValue(denominator1)),
						new RationalValue(new IntegerValue(imaginary1), new IntegerValue(denominator2))),
				new MyNumber(new RationalValue(new IntegerValue(value2), new IntegerValue(denominator3)),
						new RationalValue(new IntegerValue(imaginary2), new IntegerValue(denominator4)))));
		try {
			Plus e = new Plus(p1, Notation.INFIX);
			assertEquals(op4, e);
			assertEquals(e, e);
			assertNotEquals(e, new Plus(new ArrayList<>(Arrays.asList(new MyNumber(5, 2), new MyNumber(4, 1))), Notation.INFIX));
		}
		catch(IllegalConstruction e) { fail(); }
	}

	@Test
	void testRealEquals(){
		ArrayList<Expression> p1 = new ArrayList<>(Arrays.asList(new MyNumber(new RealValue(value3BD)), new MyNumber(new RealValue(value4BD))));
		try {
			Plus e = new Plus(p1, Notation.INFIX);
			assertEquals(op5, e);
			assertEquals(e, e);
			assertNotEquals(e, new Plus(new ArrayList<>(Arrays.asList(new MyNumber(5, 2), new MyNumber(4, 1))), Notation.INFIX));
		}
		catch(IllegalConstruction e) { fail(); }
	}

	@Test
	void testComplexRealEquals(){
		ArrayList<Expression> p1 = new ArrayList<>(Arrays.asList(new MyNumber(new RealValue(value3BD), new RealValue(value5BD)),
				new MyNumber(new RealValue(value4BD), new RealValue(value6BD))));
		try {
			Plus e = new Plus(p1, Notation.INFIX);
			assertEquals(op6, e);
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
	void testRationalHashCode() {
		ArrayList<Expression> p = new ArrayList<>(Arrays.asList(new MyNumber(new RationalValue(new IntegerValue(value1), new IntegerValue(denominator1))),
				new MyNumber(new RationalValue(new IntegerValue(value2), new IntegerValue(denominator3)))));
		try {
			Plus e = new Plus(p, Notation.INFIX);
			assertEquals(e.hashCode(), op3.hashCode());
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
	void testComplexRationalHashCode() {
		ArrayList<Expression> p = new ArrayList<>(Arrays.asList(new MyNumber(new RationalValue(new IntegerValue(value1), new IntegerValue(denominator1)),
						new RationalValue(new IntegerValue(imaginary1), new IntegerValue(denominator2))),
				new MyNumber(new RationalValue(new IntegerValue(value2), new IntegerValue(denominator3)),
						new RationalValue(new IntegerValue(imaginary2), new IntegerValue(denominator4)))));
		try {
			Plus e = new Plus(p, Notation.INFIX);
			assertEquals(e.hashCode(), op4.hashCode());
		}
		catch(IllegalConstruction e) { fail(); }
	}

	@Test
	void testRealHashCode() {
		ArrayList<Expression> p = new ArrayList<>(Arrays.asList(new MyNumber(new RealValue(value3BD)), new MyNumber(new RealValue(value4BD))));
		try {
			Plus e = new Plus(p, Notation.INFIX);
			assertEquals(e.hashCode(), op5.hashCode());
		}
		catch(IllegalConstruction e) { fail(); }
	}

	@Test
	void testComplexRealHashCode() {
		ArrayList<Expression> p = new ArrayList<>(Arrays.asList(new MyNumber(new RealValue(value3BD), new RealValue(value5BD)),
				new MyNumber(new RealValue(value4BD), new RealValue(value6BD))));
		try {
			Plus e = new Plus(p, Notation.INFIX);
			assertEquals(e.hashCode(), op6.hashCode());
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
