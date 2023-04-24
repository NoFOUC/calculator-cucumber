package calculator;

import converter.ComplexConverter;
import visitor.Visitor;

import java.math.BigDecimal;

/**
 * MyNumber is a concrete class that represents arithmetic numbers,
 * which are a special kind of Expressions, just like operations are.
 *
 * @see Expression
 * @see Operation
 */
public class MyNumber implements Expression {

    private final AbstractValue value;

    private final AbstractValue imaginary;

    private double theta;

    private double r;

    /** getter method to obtain the value contained in the object
     *
     * @return The integer number contained in the object
     */
    public AbstractValue getValue() { return value; }

    /** getter method to obtain the imaginary part of the number
     *
     * @return The imaginary part of the number
     */
    public AbstractValue getImaginary() { return imaginary; }


    /**
     * Constructor method
     *
     * @param v The integer value to be contained in the object
     */
    public /*constructor*/ MyNumber(int v) {
        value= new IntegerValue(v);
        imaginary = new IntegerValue(0);
    }

    /**
     * accept method to implement the visitor design pattern to traverse arithmetic expressions.
     * Each number will pass itself to the visitor object to get processed by the visitor.
     *
     * @param v	The visitor object
     * @param i The imaginary part of the number
     */
    public MyNumber(int v, int i) {
        value= new IntegerValue(v);
        imaginary = new IntegerValue(i);
    }

    /**
     * Constructor method
     *
     * @param v The integer value to be contained in the object
     */
    public /*constructor*/ MyNumber(AbstractValue v) {

        if (v instanceof RealValue){
            value = v;
            imaginary = new RealValue(new BigDecimal(0));
        }
        else {
            value = v;
            imaginary = new IntegerValue(0);
        }
      }

    /**
     * accept method to implement the visitor design pattern to traverse arithmetic expressions.
     * Each number will pass itself to the visitor object to get processed by the visitor.
     *
     * @param v	The visitor object
     * @param i The imaginary part of the number
     */
    public MyNumber(AbstractValue v, AbstractValue i) {
        value = v;
        imaginary = i;
    }

    public MyNumber(MyExpPol e) {

        MyNumber number = ComplexConverter.exponentialToCartesian(e);
        value = number.getValue();
        imaginary = number.getImaginary();
    }

    public void accept(Visitor v) {
      v.visit(this);
    }


    /** The depth of a number expression is always 0
     *
     * @return The depth of a number expression
     */
    public int countDepth() {
      return 0;
    }

    /** The number of operations contained in a number expression is always 0
     *
     * @return The number of operations contained in a number expression
     */
    public int countOps() {
      return 0;
    }

    /** The number of numbers contained in a number expression is always 1
     *
     * @return The number of numbers contained in  a number expression
     */
    public int countNbs() {
      return 1;
    }

    /**
     * Check if the number is complex
     * @return True if the number is complex, false otherwise
     */
    public boolean isComplex (){

        if (imaginary instanceof RealValue) {
            return !imaginary.equals(new RealValue(0));
        }
        else {
            return !imaginary.equals(new IntegerValue(0));
        }
    }

    /**
     * Convert a number into a String to allow it to be printed.
     *
     * @return	The String that is the result of the conversion.
     */
    @Override
    public String toString() {
        if (isComplex()) {
            return value + " + " + imaginary + "i";
        }
        else {
            return value.toString();
        }
    }

    /** Two MyNumber expressions are equal if the values they contain are equal
    *
    * @param o The object to compare to
    * @return  A boolean representing the result of the equality test
    */
    @Override
    public boolean equals(Object o) {
      // No object should be equal to null (not including this check can result in an exception if a MyNumber is tested against null)
      if (o == null) return false;

      // If the object is compared to itself then return true
      if (o == this) {
          return true;
      }

      // If the object is of another type then return false
      if (!(o instanceof MyNumber)) {
            return false;
      }
      return this.value.equals(((MyNumber)o).value) && this.imaginary.equals(((MyNumber)o).imaginary);
      // Used == since the contained value is a primitive value
      // If it had been a Java object, .equals() would be needed
    }

    /** The method hashCode needs to be overridden it the equals method is overridden;
     * 	otherwise there may be problems when you use your object in hashed collections
     * 	such as HashMap, HashSet, LinkedHashSet.
     *
     * @return	The result of computing the hash.
     */
    @Override
    public int hashCode() {
        return value.hashCode(); //TODO Hashing for floats and rationals
    }

}
