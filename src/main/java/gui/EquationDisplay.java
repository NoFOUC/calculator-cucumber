package gui;

import calculator.Operation;

import java.util.ArrayList;
import java.util.Objects;

/**
 * A container for various possible terms of the calculation
 */
public class EquationDisplay {

    private int value = 10; //Default value for tests, normally will never go past 9

    private String operator = "";

    private String openBracket = "";
    private String closeBracket = "";

    private boolean isRoot = false;
    private boolean hasCursor = false;
    private boolean isOpen = false;

    private EquationDisplay next;
    private EquationDisplay prev;
    private EquationDisplay parent;
    private EquationDisplay child;

    public EquationDisplay() {
        isRoot = true;
    }

    public EquationDisplay(int value) {
        this.value = value;
    }

    public EquationDisplay(String operator) {
        this.operator = operator;
    }

    public EquationDisplay(String operator, String openBracket, String closeBracket) {
        this.operator = operator;
        this.openBracket = openBracket;
        this.closeBracket = closeBracket;
        this.isOpen = true;
    }

    public EquationDisplay(String openBracket, String closeBracket) {
        this.openBracket = openBracket;
        this.closeBracket = closeBracket;
        this.isOpen = true;
    }

    @Override
    public String toString() {

        String cursorOpen = hasCursor && isOpen ? "_" : "";
        String cursorClosed = hasCursor && !isOpen ? "_" : "";

        String children = child == null ? "" : child.toString();

        String remainder = next == null ? "" : next.toString();

        String number = value == 10 ? "" : ""+value;

        return operator + openBracket + number + cursorOpen + children + closeBracket + cursorClosed + remainder;

    }

    public ArrayList<Object> toArrayList(ArrayList<Object> prev) {

        if (value != 10) prev.add(""+value);

        else if (child != null) {
            ArrayList<Object> sub = new ArrayList<>();
            if(!Objects.equals(operator, "")) sub.add(operator);
            child.toArrayList(sub);
            prev.add(sub);
        }

        else if (!Objects.equals(operator, "")) {
            prev.add(operator);
        }

        if (next != null) next.toArrayList(prev);

        return prev;
    }

    public ArrayList<Object> toArrayList() {
        ArrayList<Object> base = new ArrayList<>();
        return toArrayList(base);
    }
    /**
     * Moves the cursor from the current position to the next logical one
     * @return the new cursor
     */
    public EquationDisplay moveCursorRight() {
        if (next != null) {
            next.setHasCursor(true);
            this.setHasCursor(false);
            return next;
        }
        // Case for entering an expression
        // Normally should never occur
//        if (child != null) {
//            child.setHasCursor(true);
//            this.setHasCursor(false);
//            return child;
//        }
        // Case for exiting an expression
        if (parent != null) {
            parent.setHasCursor(true);
            this.setHasCursor(false);
            parent.close();
            return parent;
        }

        return this;
    }


    private void close() {
        isOpen = false;
    }

    public void setHasCursor(boolean cursor) {
        hasCursor = cursor;
    }

    public void add(EquationDisplay next) {
        next.setHasCursor(true);
        this.setHasCursor(false);

        if (this.isOpen) {
            this.child = next;
            next.setParent(this);
        }
        else if (this.next == null) {
            this.next = next;
            next.setPrev(this);
            next.setParent(parent);
        }
        else this.next.add(next);
    }

    public EquationDisplay erase() {

        if (prev != null) {
            prev.setNext(null);
            prev.setHasCursor(true);
            return prev;
        }

        if (parent != null) {
            parent.setChild(null);
            parent.setHasCursor(true);
            return parent;
        }

        return this; // Normally only occurs when this is the root of the equation
    }

    private void setPrev(EquationDisplay prev) {
        this.prev = prev;
    }

    public void setNext(EquationDisplay next) {
        this.next = next;
    }

    public void setParent(EquationDisplay parent) {
        this.parent = parent;
    }

    public void setChild(EquationDisplay child) {
        this.child = child;
    }

    public void reset() {
        this.child = null;
        this.parent = null;
        this.next = null;
        this.prev = null;
        this.hasCursor = true;
    }
}
