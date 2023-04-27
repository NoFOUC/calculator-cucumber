package visitor;

import calculator.DisplayType;
import calculator.IllegalConstruction;
import calculator.MyNumber;
import calculator.Operation;

public class DisplayStringPostfix extends DisplayStringVisitor {

    public DisplayStringPostfix(DisplayType displayType) {
        super(displayType);
    }

    public DisplayStringPostfix() {
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
        computedDisplayString.append(")").append(" ").append(o.getSymbol());
        computedDisplayString.append(", ");
    }
}
