package calculator;

import static org.junit.jupiter.api.Assertions.*;

import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import java.util.ArrayList;
import java.util.List;

public class CalculatorSteps {

//	static final Logger log = getLogger(lookup().lookupClass());

	private ArrayList<Expression> params;
	private Operation op;
	private Calculator c;

	@Before
    public void resetMemoryBeforeEachScenario() {
		params = null;
		op = null;
	}

	@Given("I initialise a calculator")
	public void givenIInitialiseACalculator() {
		c = new Calculator();
	}

	@Given("an integer operation {string}")
	public void givenAnIntegerOperation(String s) {
		// Write code here that turns the phrase above into concrete actions
		params = new ArrayList<>(); // create an empty set of parameters to be filled in
		try {
			switch (s) {
				case "+"	->	op = new Plus(params);
				case "-"	->	op = new Minus(params);
				case "*"	->	op = new Times(params);
				case "/"	->	op = new Divides(params);
				default		->	fail();
			}
		} catch (IllegalConstruction e) {
			fail();
		}
	}

	@Given("a complex operation {string}")
	public void givenAComplexOperation(String s) {
		// Write code here that turns the phrase above into concrete actions
		params = new ArrayList<>(); // create an empty set of parameters to be filled in
		try {
			switch (s) {
				case "+"	->	op = new Plus(params);
				case "-"	->	op = new Minus(params);
				case "*"	->	op = new Times(params);
				case "/"	->	op = new Divides(params);
				default		->	fail();
			}
		} catch (IllegalConstruction e) {
			fail();
		}
	}

	// The following example shows how to use a DataTable provided as input.
	// The example looks slightly complex, since DataTables can take as input
	//  tables in two dimensions, i.e. rows and lines. This is why the input
	//  is a list of lists.
	@Given("the following list of integer numbers")
	public void givenTheFollowingListOfNumbers(List<List<String>> numbers) {
		params = new ArrayList<>();
		// Since we only use one line of input, we use get(0) to take the first line of the list,
		// which is a list of strings, that we will manually convert to integers:
		numbers.get(0).forEach(n -> params.add(new MyNumber(Integer.parseInt(n))));
	    params.forEach(n -> System.out.println("value ="+ n));
		op = null;
	}


	@Given("the following list of complex numbers")
	public void givenTheFollowingListOfComplexNumbers(List<List<String>> numbers) {
		params = new ArrayList<>();
		// Since we only use one line of input, we use get(0) to take the first line of the list,
		// which is a list of strings, that we will manually convert to integers:
		numbers.get(0).forEach(n -> params.add(new MyNumber(Integer.parseInt(n.split("\\+")[0]), Integer.parseInt(n.split("\\+")[1].split("i")[0]))));
		params.forEach(n -> System.out.println("value ="+ n));
		op = null;
	}

	// The string in the Given annotation shows how to use regular expressions...
	// In this example, the notation d+ is used to represent numbers, i.e. nonempty sequences of digits
	@Given("^the sum of two numbers (\\d+) and (\\d+)$")
	// The alternative, and in this case simpler, notation would be:
	// @Given("the sum of two numbers {int} and {int}")
	public void givenTheSum(int n1, int n2) {
		try {
			params = new ArrayList<>();
		    params.add(new MyNumber(n1));
		    params.add(new MyNumber(n2));
		    op = new Plus(params);}
		catch(IllegalConstruction e) { fail(); }
	}

	@Given("^the sum of two complex numbers (.+) and (.+)$")
	public void givenTheComplexSum(String n1, String n2) {
		try {
			params = new ArrayList<>();
			params.add(new MyNumber(Integer.parseInt(n1.split("\\+")[0]), Integer.parseInt(n1.split("\\+")[1].split("i")[0])));
			params.add(new MyNumber(Integer.parseInt(n2.split("\\+")[0]), Integer.parseInt(n2.split("\\+")[1].split("i")[0])));
			op = new Plus(params);}
		catch(IllegalConstruction e) { fail(); }
	}

	@Then("^its (.*) notation is (.*)$")
	public void thenItsNotationIs(String notation, String s) {
		if (notation.equals("PREFIX")||notation.equals("POSTFIX")||notation.equals("INFIX")) {
			op.notation = Notation.valueOf(notation);
			assertEquals(s, op.toString());
		}
		else fail(notation + " is not a correct notation! ");
	}

	@When("^I provide a (.*) number (\\d+)$")
	public void whenIProvideANumber(String s, int val) {
		//add extra parameter to the operation
		params = new ArrayList<>();
		params.add(new MyNumber(val));
		op.addMoreParams(params);
	}

	@When("^I provide a (.*) complex number (.+)$")
	public void whenIProvideAComplexNumber(String s, String val) {
		//add extra parameter to the operation
		params = new ArrayList<>();
		params.add(new MyNumber(Integer.parseInt(val.split("\\+")[0]), Integer.parseInt(val.split("\\+")[1].split("i")[0])));
		op.addMoreParams(params);
	}

	@Then("^the (.*) is (.*)")
	public void thenTheOperationIs(String s, String val) {
		try {
			switch (s) {
				case "sum" -> op = new Plus(params);
				case "product" -> op = new Times(params);
				case "quotient" -> op = new Divides(params);
				case "difference" -> op = new Minus(params);
				default -> fail();
			}
			System.out.println(op);
			val = val.replaceAll("\\s+","");
			if (val.split("\\+").length>1) {
				MyNumber a = new MyNumber(Integer.parseInt(val.split("\\+")[0]), Integer.parseInt(val.split("\\+")[1].split("i")[0]));
				System.out.println(a);
				assertEquals(a, c.eval(op));
			}
			else {
				MyNumber a = new MyNumber(Integer.parseInt(val));
				System.out.println(a);
				assertEquals(a, c.eval(op));
			}

		} catch (IllegalConstruction e) {
			fail();
		}
	}

	@Then("the operation evaluates to (\\d+)$")
	public void thenTheOperationEvaluatesTo(int val) {
		assertEquals(new MyNumber(val), c.eval(op));
	}
	@Then("the complex operation evaluates to (.+)$")
	public void thenTheComplexOperationEvaluatesTo(String val) {
		assertEquals(new MyNumber(Integer.parseInt(val.split("\\+")[0]), Integer.parseInt(val.split("\\+")[1].split("i")[0])), c.eval(op));
	}

}
