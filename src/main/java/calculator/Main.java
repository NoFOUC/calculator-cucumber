package calculator;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * A very simple calculator in Java
 * University of Mons - UMONS
 * Software Engineering Lab
 * Faculty of Sciences
 *
 * @author tommens
 */
public class Main {

	/**
	 * This is the main method of the application.
	 * It provides examples of how to use it to construct and evaluate arithmetic expressions.
	 *
	 * @param args	Command-line parameters are not used in this version
	 */
	public static void main(String[] args) {

  	Expression e;
  	Calculator c = new Calculator();

	try{

		e = new MyNumber(8);
		c.getExpressionResult(e);
		c.eval(e);

	    List<Expression> params = new ArrayList<>();
	    Collections.addAll(params, new MyNumber(3), new MyNumber(4), new MyNumber(5));
	    e = new Plus(params,Notation.PREFIX);
		c.getExpressionDetails(e);
		c.eval(e);
	
		List<Expression> params2 = new ArrayList<>();
		Collections.addAll(params2, new MyNumber(5), new MyNumber(3));
		e = new Minus(params2, Notation.INFIX);
		c.getExpressionResult(e);
		c.eval(e);

		List<Expression> params3 = new ArrayList<>();
		Collections.addAll(params3, new Plus(params), new Minus(params2));
		e = new Times(params3);
		c.getExpressionDetails(e);
		c.eval(e);

		List<Expression> params4 = new ArrayList<>();
		Collections.addAll(params4, new Plus(params), new Minus(params2), new MyNumber(5));
		e = new Divides(params4,Notation.POSTFIX);
		c.getExpressionResult(e);
		c.eval(e);

		List<Expression> params5 = new ArrayList<>();
		Collections.addAll(params5, new MyNumber(2, 3), new Minus(params2));
		e = new Times(params5,Notation.POSTFIX);
		c.getExpressionResult(e);
		c.eval(e);

		List<Expression> params6 = new ArrayList<>();
		Collections.addAll(params6, new MyNumber(42, 69), new MyNumber(5, 3));
		e = new Divides(params6,Notation.INFIX);
		c.getExpressionResult(e);
		c.eval(e);

		BigDecimal value3 = new BigDecimal("8.278397");
		BigDecimal value4 = new BigDecimal("6.187802");
		List<Expression> params7 = Arrays.asList(
				new MyNumber(new RealValue(value3)),
				new MyNumber(new RealValue(value4)));

		c.getExpressionResult(new Divides(params7));
		c.eval(new Divides(params7));
	}

	catch(IllegalConstruction exception) {
		System.out.println("cannot create operations without parameters");
		}
 	}

}
