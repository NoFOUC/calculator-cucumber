package visitor;

import calculator.*;

/**
 * An abstract class serving as a baseline for the three main
 * visitor classes responsible for displaying the finalized expression
 */
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


    /**
     * Outputs a concrete DisplayString class based on the provided Notation type
     * @param n the Notation enum corresponding to the required notation type
     * @param d the desired way of displaying complex numbers
     * @return a new concrete instance of a required DisplayString visitor
     */
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
