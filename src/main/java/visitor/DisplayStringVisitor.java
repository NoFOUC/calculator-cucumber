package visitor;

import calculator.*;

public abstract class DisplayStringVisitor extends Visitor {

    protected StringBuilder computedDisplayString;

    protected DisplayType complexDisplayType;

    public DisplayStringVisitor(DisplayType displayType) {
        complexDisplayType = displayType;
        computedDisplayString = new StringBuilder();
    }

    public DisplayStringVisitor() {
        this(DisplayType.CARTESIAN);
    }

    public abstract String getDisplayString();


    public static DisplayStringVisitor getVisitorForNotation(Notation n, DisplayType d) {
        switch (n) {
            case INFIX -> {
                return new DisplayStringInfix(d);
            }
            case POSTFIX -> {
                return new DisplayStringPostfix(d);
            }
            default -> {
                return new DisplayStringPrefix(d);
            }
        }
    }
}
