package visitor;

import calculator.IllegalConstruction;
import calculator.MyNumber;
import calculator.Operation;

import java.util.Collections;
import java.util.LinkedList;

/**
 * A visitor class for counting the depth of an expression
 */
public class DepthCounter extends CounterVisitor {

    private int maxDepth;
    private LinkedList<Integer> depths;

    public int getCount() {
        return maxDepth;
    }

    public DepthCounter() {
        depths = new LinkedList<>();
    }

    @Override
    public void visit(MyNumber n) {
        depths.add(0);
    }

    @Override
    public void visit(Operation o) throws IllegalConstruction {
        maxDepth = Collections.max(depths)+1;
        depths = new LinkedList<>();
        depths.add(maxDepth);
    }
}
