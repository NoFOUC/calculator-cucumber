package calculator;

//Import Junit5 libraries for unit testing:
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;
import visitor.*;

import java.util.Arrays;
import java.util.List;

class TestOperation {

	private Operation o;
	private Operation o2;

	private Operation o3;

	private Operation o4;


	@BeforeEach
	void setUp() throws Exception {
		List<Expression> params1 = Arrays.asList(new MyNumber(3), new MyNumber(4), new MyNumber(5));
		List<Expression> params2 = Arrays.asList(new MyNumber(5), new MyNumber(4));
		List<Expression> params3 = Arrays.asList(new Plus(params1), new Minus(params2), new MyNumber(7));
		List<Expression> params4 = Arrays.asList(new MyNumber(3, 2), new MyNumber(4, 5), new MyNumber(5));
		List<Expression> params5 = Arrays.asList(new MyNumber(5), new MyNumber(4, 3));
		List<Expression> params6 = Arrays.asList(new Plus(params4), new Minus(params5), new MyNumber(7));
		o = new Divides(params3);
		o2 = new Divides(params3);
		o3 = new Divides(params6);
		o4 = new Divides(params6);
	}

	@Test
	void testEquals() {
		assertEquals(o,o2);
		assertEquals(o3,o4);
	}

	@Test
	void testCountDepth() {
		CounterVisitor counter = new DepthCounter();
		try {
			o.accept(counter);
			assertEquals(3, counter.getCount());

			counter = new DepthCounter();
			o3.accept(counter);
			assertEquals(3, counter.getCount());
		} catch (IllegalConstruction e) {
			fail();
		}
	}

	@Test
	void testCountOps() {
		CounterVisitor counter = new OperationCounter();
		try {
			o.accept(counter);
			assertEquals(3, counter.getCount());

			counter = new OperationCounter();
			o3.accept(counter);
			assertEquals(3, counter.getCount());
		} catch (IllegalConstruction e) {
			fail();
		}
	}

	@Test
	void testCountNbs() {
		CounterVisitor counter = new NumberCounter();
		try {
			o.accept(counter);
			assertEquals(6, counter.getCount());

			counter = new NumberCounter();
			o3.accept(counter);
			assertEquals(6, counter.getCount());
		} catch (IllegalConstruction e) {
			fail();
		}
	}

}
