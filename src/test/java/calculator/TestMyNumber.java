package calculator;

//Import Junit5 libraries for unit testing:
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.*;

import java.math.BigDecimal;
import java.util.ArrayList;

class TestMyNumber {

	private final int value =8;
	private final int imaginary = 5;

	private final int denominator1 = 3;

	private final int denominator2 = 4;

	private final BigDecimal valueBD1 = new BigDecimal("8.278397");

	private final BigDecimal valueBD2 = new BigDecimal("6.187802");


	private MyNumber number;
	private MyNumber complexNumber;

	private MyNumber rationalNumber;

	private MyNumber rationalComplexNumber;

	private MyNumber realNumber;

	private MyNumber realComplexNumber;
	
	@BeforeEach
	void setUp() {
		number = new MyNumber(value);
		complexNumber = new MyNumber(value, imaginary);
		rationalNumber = new MyNumber(new RationalValue (new IntegerValue(value), new IntegerValue(denominator1)));
		rationalComplexNumber = new MyNumber(new RationalValue (new IntegerValue(value), new IntegerValue(denominator1)), new RationalValue (new IntegerValue(imaginary), new IntegerValue(denominator2)));
		realNumber = new MyNumber(new RealValue(valueBD1));
		realComplexNumber = new MyNumber(new RealValue(valueBD1), new RealValue(valueBD2));
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
		assertEquals(new MyNumber(value, imaginary), complexNumber);
		// Two MyNumbers containing a distinct value should not be equal:
		int otherValue = 7;
		int otherImaginary = 3;
		assertNotEquals(new MyNumber(otherValue, otherImaginary),complexNumber);
		assertEquals(complexNumber, complexNumber); // Identity check (for coverage, as this should always be true)
		assertNotEquals(complexNumber, value); // number is of type MyNumber, while value is of type int, so not equal
		try {
			assertNotEquals(new Times(new ArrayList<>()), complexNumber);
		}
		catch (IllegalConstruction e) {fail();}
	}

	@Test
	void testRationnalEquals() {
		// Two distinct MyNumber, constructed separately (using a different constructor) but containing the same value should be equal
		assertEquals(new MyNumber(new RationalValue (new IntegerValue(value), new IntegerValue(denominator1))), rationalNumber);
		// Two MyNumbers containing a distinct value should not be equal:
		int otherValue = 7;
		assertNotEquals(new MyNumber(new RationalValue (new IntegerValue(otherValue), new IntegerValue(denominator1))),rationalNumber);
		assertEquals(rationalNumber, rationalNumber); // Identity check (for coverage, as this should always be true)
		assertNotEquals(rationalNumber, value); // number is of type MyNumber, while value is of type int, so not equal
		try {
			assertNotEquals(new Times(new ArrayList<>()), rationalNumber);
		}
		catch (IllegalConstruction e) {fail();}
	}

	@Test
	void testRationalComplexEquals() {
		// Two distinct MyNumber, constructed separately (using a different constructor) but containing the same value should be equal
		assertEquals(new MyNumber(new RationalValue (new IntegerValue(value), new IntegerValue(denominator1)), new RationalValue (new IntegerValue(imaginary), new IntegerValue(denominator2))), rationalComplexNumber);
		// Two MyNumbers containing a distinct value should not be equal:
		int otherValue = 7;
		int otherImaginary = 3;
		assertNotEquals(new MyNumber(new RationalValue (new IntegerValue(otherValue), new IntegerValue(denominator1)), new RationalValue (new IntegerValue(otherImaginary), new IntegerValue(denominator2))),rationalComplexNumber);
		assertEquals(rationalComplexNumber, rationalComplexNumber); // Identity check (for coverage, as this should always be true)
		assertNotEquals(rationalComplexNumber, value); // number is of type MyNumber, while value is of type int, so not equal
		try {
			assertNotEquals(new Times(new ArrayList<>()), rationalComplexNumber);
		}
		catch (IllegalConstruction e) {fail();}
	}

	@Test
	void testRealEquals(){
		// Two distinct MyNumber, constructed separately (using a different constructor) but containing the same value should be equal
		assertEquals(new MyNumber(new RealValue(valueBD1)), realNumber);
		// Two MyNumbers containing a distinct value should not be equal:
		BigDecimal otherValue = new BigDecimal("7.278397");
		assertNotEquals(new MyNumber(new RealValue(otherValue)),realNumber);
		assertEquals(realNumber, realNumber); // Identity check (for coverage, as this should always be true)
		assertNotEquals(realNumber, value); // number is of type MyNumber, while value is of type int, so not equal
		try {
			assertNotEquals(new Times(new ArrayList<>()), realNumber);
		}
		catch (IllegalConstruction e) {fail();}
	}

	@Test
	void testRealComplexEquals(){
		// Two distinct MyNumber, constructed separately (using a different constructor) but containing the same value should be equal
		assertEquals(new MyNumber(new RealValue(valueBD1), new RealValue(valueBD2)), realComplexNumber);
		// Two MyNumbers containing a distinct value should not be equal:
		BigDecimal otherValue = new BigDecimal("7.278397");
		BigDecimal otherImaginary = new BigDecimal("5.187802");
		assertNotEquals(new MyNumber(new RealValue(otherValue), new RealValue(otherImaginary)),realComplexNumber);
		assertEquals(realComplexNumber, realComplexNumber); // Identity check (for coverage, as this should always be true)
		assertNotEquals(realComplexNumber, value); // number is of type MyNumber, while value is of type int, so not equal
		try {
			assertNotEquals(new Times(new ArrayList<>()), realComplexNumber);
		}
		catch (IllegalConstruction e) {fail();}
	}

	@Test
	void testGetR () {

		assertEquals(new MyNumber(new RealValue(Math.sqrt(value*value+ imaginary*imaginary))).toString(8), new MyNumber(complexNumber.getR()).toString(8));

	}

	@Test
	void testGetTheta () {

		assertEquals(new MyNumber(new RealValue(Math.atan( (double) imaginary/(double)value))).toString(5), new MyNumber(complexNumber.getTheta()).toString(5));

	}

	@Test
	void testToString() {
		assertEquals(Integer.toString(value), number.toString());
		assertEquals(Integer.toString(value) + " + " + Integer.toString(imaginary) + "i", complexNumber.toString());
	}

}
