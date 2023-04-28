package visitor;

/**
 * A generic abstract class for codifying the visitor classes counting certain properties of an expression
 */
public abstract class CounterVisitor extends Visitor {

    public abstract int getCount();
}
