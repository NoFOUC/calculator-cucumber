package calculator;

//Import Junit5 libraries for unit testing:

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class TestRationalValue {

	private final int value1 =8;
	private final int value2 =7;
	private final int value3 =2;
	private final int value4 =4;

	private RationalValue rational1, rational2, rational3;

	private MyNumber number1, number2, number3;
	
	@BeforeEach
	void setUp() {
		rational1 = new RationalValue(new IntegerValue(value1), new IntegerValue(value2));
		rational2 = new RationalValue(new IntegerValue(value1), new IntegerValue(value3));
		rational3 = new RationalValue(new IntegerValue(value3), new IntegerValue(value1));

		number1 = new MyNumber(rational1);
		number2 = new MyNumber(rational2);
		number3 = new MyNumber(rational3);
	}

	@Test
	void testEquals() {
		// Check whether the rational is simplified properly
		assertEquals(new MyNumber(new RationalValue(new IntegerValue(value4))), number2);
		// Two values containing a distinct value should not be equal:
		assertNotEquals(number1, number2);
		assertEquals(number1, number1); // Identity check (for coverage, as this should always be true)
		assertNotEquals(number2, value4); // number is of type MyNumber, while value is of type int, so not equal
		try {
			assertNotEquals(new Times(new ArrayList<>()), rational1);
		}
		catch (IllegalConstruction e) {fail();}
	}

	@Test
	void testToString() {
		assertEquals("(8/7)", number1.toString());
		assertEquals("4", number2.toString());
		assertEquals("(1/4)", number3.toString());
	}

	@Test
	void testToReal () throws IllegalConstruction {
		RationalValue rationalValue = new RationalValue(new IntegerValue(8), new IntegerValue(7));

		assertEquals(new RealValue(new BigDecimal("1.142857142857")), rational1.toReal());
		assertEquals(new RealValue(4.000000000000), rational2.toReal());
		assertEquals(new RealValue(0.25), rational3.toReal());

		//assertEquals(rationalValue.toReal(), rational1.toReal());
	}


}
