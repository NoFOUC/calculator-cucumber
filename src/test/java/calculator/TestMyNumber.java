package calculator;

//Import Junit5 libraries for unit testing:
import static org.junit.jupiter.api.Assertions.*;

import io.cucumber.java.Before;
import org.junit.jupiter.api.*;

import java.util.ArrayList;

class TestMyNumber {

	private final int value =8;
	private final int imaginary = 5;
	private MyNumber number;
	private MyNumber complexnumber;
	
	@BeforeEach
	void setUp() {
		number = new MyNumber(value);
		complexnumber = new MyNumber(value, imaginary);
	}

	@Test
	void testEquals() {
		// Two distinct MyNumber, constructed separately (using a different constructor) but containing the same value should be equal
		assertEquals(new MyNumber(value), number);
		// Two MyNumbers containing a distinct value should not be equal:
		int otherValue = 7;
		assertNotEquals(new MyNumber(otherValue),number);
		assertEquals(number, number); // Identity check (for coverage, as this should always be true)
		assertNotEquals(number, value); // number is of type MyNumber, while value is of type int, so not equal
		try {
			assertNotEquals(new Times(new ArrayList<>()), number);
		}
		catch (IllegalConstruction e) {fail();}
	}


	@Test
	void testComplexEquals() {
		// Two distinct MyNumber, constructed separately (using a different constructor) but containing the same value should be equal
		assertEquals(new MyNumber(value, imaginary), complexnumber);
		// Two MyNumbers containing a distinct value should not be equal:
		int otherValue = 7;
		int otherImaginary = 3;
		assertNotEquals(new MyNumber(otherValue, otherImaginary),complexnumber);
		assertEquals(complexnumber, complexnumber); // Identity check (for coverage, as this should always be true)
		assertNotEquals(complexnumber, value); // number is of type MyNumber, while value is of type int, so not equal
		try {
			assertNotEquals(new Times(new ArrayList<>()), complexnumber);
		}
		catch (IllegalConstruction e) {fail();}
	}
	@Test
	void testToString() {
		assertEquals(Integer.toString(value), number.toString());
		assertEquals(Integer.toString(value) + " + " + Integer.toString(imaginary) + "i", complexnumber.toString());
	}

}
