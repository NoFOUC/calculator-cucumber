package calculator;

//Import Junit5 libraries for unit testing:

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class TestRationalValue {

	private final int value1 =8;
	private final int value2 =7;
	private final int value3 =2;
	private final int value4 =4;

	private RationalValue number1, number2, number3;
	
	@BeforeEach
	void setUp() {
		number1 = new RationalValue(new IntegerValue(value1), new IntegerValue(value2));
		number2 = new RationalValue(new IntegerValue(value1), new IntegerValue(value3));
		number3 = new RationalValue(new IntegerValue(value3), new IntegerValue(value1));
	}

	@Test
	void testEquals() {
		// Check whether the rational is simplified properly
		assertEquals(new RationalValue(new IntegerValue(value4)), number2);
		// Two values containing a distinct value should not be equal:
		assertNotEquals(number1, number2);
		assertEquals(number1, number1); // Identity check (for coverage, as this should always be true)
		assertNotEquals(number2, value4); // number is of type MyNumber, while value is of type int, so not equal
		assertEquals(number2, number3.inverse()); // Check if inverting a rational works
		try {
			assertNotEquals(new Times(new ArrayList<>()), number1);
		}
		catch (IllegalConstruction e) {fail();}
	}

	@Test
	void testToString() {
		assertEquals("(8/7)", number1.toString());
		assertEquals("4", number2.toString());
		assertEquals("(1/4)", number3.toString());
	}

}
