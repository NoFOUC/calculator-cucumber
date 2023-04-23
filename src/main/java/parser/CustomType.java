package parser;

import calculator.MyNumber;

import java.util.ArrayList;

public class CustomType {

    private MyNumber myNumber = null;

    private String string = null;

    private ParserList parserList = null;

    private ArrayList<CustomType> customTypes = null;

    public CustomType(MyNumber myNumber) {
        this.myNumber = myNumber;
    }

    public CustomType(String string) {
        this.string = string;
    }

    public CustomType(ParserList parserList) {
        this.parserList = parserList;
    }

    public CustomType(ArrayList<CustomType> customTypes) {
        this.customTypes = customTypes;
    }

    // make a fonction that return the value of customType in a array when we have a list of customType without a getter
    // exemple ArrayList<CustomType> customTypes = new ArrayList<>();
    // customTypes.add(new CustomType(new MyNumber(new RealValue(1))));
    // system.out.println(customTypes.get(0))
    // return MyNumber(new RealValue(1))




    public void changeType (MyNumber myNumber) {
        this.myNumber = myNumber;
        this.string = null;
        this.parserList = null;
        this.customTypes = null;
    }

    public void changeType (String string) {
        this.string = string;
        this.myNumber = null;
        this.parserList = null;
        this.customTypes = null;
    }

    public void changeType (ParserList parserList) {
        this.parserList = parserList;
        this.myNumber = null;
        this.string = null;
        this.customTypes = null;
    }

    public void changeType (ArrayList<CustomType> customTypes) {
        this.customTypes = customTypes;
        this.myNumber = null;
        this.string = null;
        this.parserList = null;
    }

    public MyNumber getMyNumber() {
        return myNumber;
    }

    public String getString() {
        return string;
    }

    public ParserList getParserList() {
        return parserList;
    }

    public ArrayList<CustomType> getCustomTypes() {
        return customTypes;
    }

    public boolean isMyNumber() {
        return myNumber != null;
    }

    public boolean isString() {
        return string != null;
    }

    public boolean isParserList() {
        return parserList != null;
    }

    public boolean isListOfCustomTypes() {
        return customTypes != null;
    }

    public static CustomType convertObjectToCustomType (Object object) {
        if (object instanceof MyNumber) {
            return new CustomType((MyNumber) object);
        } else if (object instanceof String) {
            return new CustomType((String) object);
        } else if (object instanceof ParserList) {
            return new CustomType((ParserList) object);
        } else if (object instanceof ArrayList) {

            for (int i=0; i<((ArrayList) object).size(); i++) {
                ((ArrayList) object).set(i, convertObjectToCustomType(((ArrayList) object).get(i)));
            }

            return new CustomType((ArrayList<CustomType>) object);
        } else {
            return null;
        }
    }



}
