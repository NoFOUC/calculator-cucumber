package calculator;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.math.BigDecimal;

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

		e = new RealNumber(new BigDecimal(3));
		c.print(e);
		c.eval(e);

	    List<Expression> params = new ArrayList<>();
	    Collections.addAll(params, new RealNumber(new BigDecimal(3)), new RealNumber(new BigDecimal(4)), new RealNumber(new BigDecimal(5)));
	    e = new Plus(params,Notation.PREFIX);
		c.printExpressionDetails(e);
		c.eval(e);
	
		List<Expression> params2 = new ArrayList<>();
		Collections.addAll(params2, new RealNumber(new BigDecimal(5)), new RealNumber(new BigDecimal(3)));
		e = new Minus(params2, Notation.INFIX);
		c.print(e);
		c.eval(e);

		List<Expression> params3 = new ArrayList<>();
		Collections.addAll(params3, new Plus(params), new Minus(params2));
		e = new Times(params3);
		c.printExpressionDetails(e);
		c.eval(e);

		List<Expression> params4 = new ArrayList<>();
		Collections.addAll(params4, new Plus(params), new Minus(params2), new RealNumber(new BigDecimal(5)));
		e = new Divides(params4,Notation.POSTFIX);
		c.print(e);
		c.eval(e);

		List<Expression> params5 = new ArrayList<>();
		Collections.addAll(params5, new MyNumber(2, 3), new Minus(params2));
		e = new Times(params5,Notation.POSTFIX);
		c.print(e);
		c.eval(e);

		List<Expression> params6 = new ArrayList<>();
		Collections.addAll(params6, new MyNumber(42, 69), new MyNumber(5, 3));
		e = new Divides(params6,Notation.INFIX);
		c.print(e);
		c.eval(e);

	}

	catch(IllegalConstruction exception) {
		System.out.println("cannot create operations without parameters");
		}
 	}

}
