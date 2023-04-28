package visitor;

import calculator.IllegalConstruction;
import calculator.MyNumber;
import calculator.Operation;

public class OperationCounter extends CounterVisitor {

    private int count;

    public int getCount() {
        return count;
    }

    @Override
    public void visit(MyNumber n) {

    }

    @Override
    public void visit(Operation o) throws IllegalConstruction {
        count += 1;
    }
}
