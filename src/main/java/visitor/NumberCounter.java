package visitor;

import calculator.IllegalConstruction;
import calculator.MyNumber;
import calculator.Operation;

/**
 * A visitor class for counting the number of MyNumber-type objects used in an expression
 */
public class NumberCounter extends CounterVisitor {

    private int count;

    public int getCount() {
        return count;
    }

    @Override
    public void visit(MyNumber n) {
        count += 1;
    }

    @Override
    public void visit(Operation o) throws IllegalConstruction {
    }
}
