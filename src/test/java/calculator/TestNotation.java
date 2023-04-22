package calculator;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;


class TestNotation {

    /* This is an auxilary method to avoid code duplication.
     */
	void testNotation(String s,Operation o,Notation n) {
		assertEquals(s, o.toString(n));
		o.notation = n;
		assertEquals(s, o.toString());
	}

	/* This is an auxilary method to avoid code duplication.
     */
	void testNotations(String symbol,int value1,int value2,Operation op) {
		//prefix notation:
		testNotation(symbol +" (" + value1 + ", " + value2 + ")", op, Notation.PREFIX);
		//infix notation:
		testNotation("( " + value1 + " " + symbol + " " + value2 + " )", op, Notation.INFIX);
		//postfix notation:
		testNotation("(" + value1 + ", " + value2 + ") " + symbol, op, Notation.POSTFIX);
	}

	void testComplexNotations(String symbol,int value1, int imaginary1, int value2, int imaginary2, Operation op) {
		//prefix notation:
		testNotation(symbol +" (" + value1 +" + " + imaginary1 +"i" + ", " + value2 + " + " + imaginary2 + "i" + ")", op, Notation.PREFIX);
		//infix notation:
		testNotation("( " + value1 +" + " + imaginary1 +"i" + " " + symbol + " " + value2 + " + " + imaginary2 + "i" + " )", op, Notation.INFIX);
		//postfix notation:
		testNotation("(" + value1 +" + " + imaginary1 +"i" + ", " + value2 + " + " + imaginary2 + "i" + ") " + symbol, op, Notation.POSTFIX);
	}
	@ParameterizedTest
	@ValueSource(strings = {"*", "+", "/", "-", "sqrt", "||"})
	void testOutput(String symbol) {
		int value1 = 8;
		int value2 = 6;
		Operation op = null;
		//List<Expression> params = new ArrayList<>(Arrays.asList(new MyNumber(value1),new MyNumber(value2)));
		List<Expression> params = Arrays.asList(new MyNumber(value1),new MyNumber(value2));
		try {
			//construct another type of operation depending on the input value
			//of the parameterised test
			switch (symbol) {
				case "+"	->	op = new Plus(params);
				case "-"	->	op = new Minus(params);
				case "*"	->	op = new Times(params);
				case "/"	->	op = new Divides(params);
				case "sqrt"	->	op = new Sqrt(params);
				case "||"	->	op = new Modulus(params);
				default		->	fail();
			}
		} catch (IllegalConstruction e) {
			fail();
		}
		testNotations(symbol, value1, value2, op);
	}

	@ParameterizedTest
	@ValueSource(strings = {"*", "+", "/", "-", "sqrt", "||"})
	void testComplexOutput(String symbol) {
		int value1 = 8;
		int imaginary1 = 6;
		int value2 = 6;
		int imaginary2 = 4;

		Operation op = null;
		//List<Expression> params = new ArrayList<>(Arrays.asList(new MyNumber(value1),new MyNumber(value2)));
		List<Expression> params = Arrays.asList(new MyNumber(value1, imaginary1),new MyNumber(value2, imaginary2));
		try {
			//construct another type of operation depending on the input value
			//of the parameterised test
			switch (symbol) {
				case "+"	->	op = new Plus(params);
				case "-"	->	op = new Minus(params);
				case "*"	->	op = new Times(params);
				case "/"	->	op = new Divides(params);
				case "sqrt"	->	op = new Sqrt(params);
				case "||"	->	op = new Modulus(params);
				default		->	fail();
			}
		} catch (IllegalConstruction e) {
			fail();
		}
		testComplexNotations(symbol, value1, value2, imaginary1, imaginary2, op);
	}

	@ParameterizedTest
	@ValueSource(strings = {"*", "+", "/", "-", "sqrt", "||"})
	void testRationalOutput(String symbol) {
		int value1 = 8;
		int value2 = 6;
		int denominator1 = 2;
		int denominator2 = 3;
		Operation op = null;
		List<Expression> params = Arrays.asList(new MyNumber(new RationalValue(new IntegerValue(value1), new IntegerValue(denominator1))),
				new MyNumber(new RationalValue(new IntegerValue(value2), new IntegerValue(denominator2))));
		try {
			//construct another type of operation depending on the input value
			//of the parameterised test
			switch (symbol) {
				case "+"	->	op = new Plus(params);
				case "-"	->	op = new Minus(params);
				case "*"	->	op = new Times(params);
				case "/"	->	op = new Divides(params);
				case "sqrt"	->	op = new Sqrt(params);
				case "||"	->	op = new Modulus(params);
				default		->	fail();
			}
		} catch (IllegalConstruction e) {
			fail();
		}
	}

	@ParameterizedTest
	@ValueSource(strings = {"*", "+", "/", "-", "sqrt", "||"})
	void testComplexRationalOutput (String symbol) {
		int value1 = 8;
		int imaginary1 = 6;
		int value2 = 6;
		int imaginary2 = 4;
		int denominator1 = 2;
		int denominator2 = 3;
		Operation op = null;
		List<Expression> params = Arrays.asList(new MyNumber(new RationalValue(new IntegerValue(value1), new IntegerValue(denominator1)), new RationalValue(new IntegerValue(imaginary1), new IntegerValue(denominator1))),
				new MyNumber(new RationalValue(new IntegerValue(value2), new IntegerValue(denominator2)), new RationalValue(new IntegerValue(imaginary2), new IntegerValue(denominator2))));
		try {
			//construct another type of operation depending on the input value
			//of the parameterised test
			switch (symbol) {
				case "+"	->	op = new Plus(params);
				case "-"	->	op = new Minus(params);
				case "*"	->	op = new Times(params);
				case "/"	->	op = new Divides(params);
				case "sqrt"	->	op = new Sqrt(params);
				case "||"	->	op = new Modulus(params);
				default		->	fail();
			}
		} catch (IllegalConstruction e) {
			fail();
		}
	}

	@ParameterizedTest
	@ValueSource(strings = {"*", "+", "/", "-", "sqrt", "||"})
	void testRealOutput(String symbol){
		BigDecimal value1 = new BigDecimal(8.17893828);
		BigDecimal value2 = new BigDecimal(6.349738);
		Operation op = null;
		List<Expression> params = Arrays.asList(new MyNumber(new RealValue(value1)), new MyNumber(new RealValue(value2)));
		try {
			//construct another type of operation depending on the input value
			//of the parameterised test
			switch (symbol) {
				case "+"	->	op = new Plus(params);
				case "-"	->	op = new Minus(params);
				case "*"	->	op = new Times(params);
				case "/"	->	op = new Divides(params);
				case "sqrt"	->	op = new Sqrt(params);
				case "||"	->	op = new Modulus(params);
				default		->	fail();
			}
		} catch (IllegalConstruction e) {
			fail();
		}
	}

	@ParameterizedTest
	@ValueSource(strings = {"*", "+", "/", "-", "sqrt", "||"})
	void testComplexRealOutput (String symbol){
		BigDecimal value1 = new BigDecimal(8.19392);
		BigDecimal imaginary1 = new BigDecimal(6.3742874);
		BigDecimal value2 = new BigDecimal(6.348774);
		BigDecimal imaginary2 = new BigDecimal(4.23892);
		Operation op = null;
		List<Expression> params = Arrays.asList(new MyNumber(new RealValue(value1), new RealValue(imaginary1)), new MyNumber(new RealValue(value2), new RealValue(imaginary2)));
		try {
			//construct another type of operation depending on the input value
			//of the parameterised test
			switch (symbol) {
				case "+"	->	op = new Plus(params);
				case "-"	->	op = new Minus(params);
				case "*"	->	op = new Times(params);
				case "/"	->	op = new Divides(params);
				case "sqrt"	->	op = new Sqrt(params);
				case "||"	->	op = new Modulus(params);
				default		->	fail();
			}
		} catch (IllegalConstruction e) {
			fail();
		}
	}

}
