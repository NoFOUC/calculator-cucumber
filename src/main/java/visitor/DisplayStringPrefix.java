package visitor;

import calculator.*;

/**
 * A visitor class for producing a string sequence representing an expression in the prefix notation
 */
public class DisplayStringPrefix extends DisplayStringVisitor {

    public DisplayStringPrefix(DisplayType displayType) {
        super(displayType);
    }

    public DisplayStringPrefix() {
        super();
    }

    public String getDisplayString() {return computedDisplayString.substring(0, computedDisplayString.length()-2);}

    @Override
    public void visit(MyNumber n) {
        computedDisplayString.append(n.toString(complexDisplayType));
        computedDisplayString.append(", ");
    }

    @Override
    public void visit(Operation o) throws IllegalConstruction {
        computedDisplayString.delete(computedDisplayString.length()-2, computedDisplayString.length());
        computedDisplayString.insert(0, "(");
        computedDisplayString.insert(0, o.getSymbol()+" ");
        computedDisplayString.append(")");
        computedDisplayString.append(", ");
    }
}
