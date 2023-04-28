package visitor;

import calculator.DisplayType;
import calculator.IllegalConstruction;
import calculator.MyNumber;
import calculator.Operation;

/**
 * A visitor class for producing a string sequence representing an expression in the infix notation
 */
public class DisplayStringInfix extends DisplayStringVisitor {


    private int firstTermPosition;
    public DisplayStringInfix(DisplayType displayType) {
        super(displayType);
    }

    public DisplayStringInfix() {
        super();
    }

    public String getDisplayString() {return computedDisplayString.toString();}

    @Override
    public void visit(MyNumber n) {
        computedDisplayString.append(n.toString(complexDisplayType));
        // We go off the assumption that we always work with a maximum of 2 sub-expressions
        firstTermPosition = computedDisplayString.length()-n.toString(complexDisplayType).length();
    }

    @Override
    public void visit(Operation o) throws IllegalConstruction {
        computedDisplayString.insert(firstTermPosition, " "+o.getSymbol()+" ");
        computedDisplayString.insert(0, "( ");
        computedDisplayString.append(" )");

    }
}
