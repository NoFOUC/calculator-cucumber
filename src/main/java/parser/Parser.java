package parser;

import calculator.*;

import java.lang.reflect.Array;
import java.math.BigDecimal;
import java.util.*;


public class Parser {

    private static final ArrayList<String> beforepriority = new ArrayList<String>
            (Arrays.asList("RNG","√", "exp", "||", "ln", "RAD", "DEG", "sin", "cos", "tan", "cot", "asin", "acos", "atan", "acot"));

    private static final ArrayList<Object> priority_no_subArray = new ArrayList<Object>
            (Arrays.asList
                    ("!", "%",
                            "*", "/",
                            "+", "-",
                            "<", ">"
                    ));

    private static final ArrayList<Object> priority = new ArrayList<Object>
            (Arrays.asList
                    ("!", "%",
                    new ArrayList<String>(Arrays.asList("*", "/")),
                    new ArrayList<String>(Arrays.asList("+", "-")),
                    new ArrayList<String>(Arrays.asList("<", ">"))
                    ));

    private static final ArrayList<String> afterpriority = new ArrayList<String> (Arrays.asList("!"));

    public static MyNumber main(ArrayList<Object> args) throws IllegalConstruction {


        // transforme the ArrayList of Object into an ArrayList of CustomType
        ArrayList<CustomType> CustomTypeArray = new ArrayList<>();
        for (int i = 0; i<args.size(); i++){
            CustomTypeArray.add(CustomType.convertObjectToCustomType(args.get(i)));
        }

        // parsing of the number en transformation in MyNumber and the operator in String
        ArrayList<CustomType> parsedNumberAndOperator = parseNumberAndOperator(CustomTypeArray);


        // call the list to make the priority of the operator and return the result
        //OLD
        //ArrayList<CustomType> makePriority = makePriority(parsedNumberAndOperator);
        //NEW
        ArrayList<CustomType> makePriority2 = newMakePriority(parsedNumberAndOperator);

        return makePriority2.get(0).getMyNumber();

    }


    /*
    public static ArrayList<ArrayList<Object>> parseParenthesis (ArrayList<Object> args) {

        // Create 3 list of string before, inside, and after the parenthesis
        // if there is more than one parenthesis ( ), recursion is needed

        // Create a list of list of string to store the list of string before, inside, and after the parenthesis
        ArrayList<ArrayList<Object>> parenthesis = new ArrayList<ArrayList<Object>>();

        // Create a list of string to store the list of string before the parenthesis
        ArrayList<Object> beforeParenthesis = new ArrayList<Object>();

        // Create a list of string to store the list of string inside the parenthesis
        ArrayList<Object> insideParenthesis = new ArrayList<Object>();

        // Create a list of string to store the list of string after the parenthesis
        ArrayList<Object> afterParenthesis = new ArrayList<Object>();

        if (args.contains("(") && args.contains(")")) {

            // Find the index of the first parenthesis
            int firstParenthesis = args.indexOf("(");

            // Find the index of the last parenthesis
            int lastParenthesis = args.lastIndexOf(")");

            // Add the list of string before the parenthesis to the list of list of string
            for (int i = 0; i < firstParenthesis; i++) {
                beforeParenthesis.add(args.get(i));
            }
            parenthesis.add(beforeParenthesis);

            // Add the list of string inside the parenthesis to the list of list of string
            for (int i = firstParenthesis + 1; i < lastParenthesis; i++) {
                insideParenthesis.add(args.get(i));

                if (insideParenthesis.contains("(") && insideParenthesis.contains(")")) {
                    parseParenthesis(insideParenthesis);
                }
            }
            parenthesis.add(insideParenthesis);

            // Add the list of string after the parenthesis to the list of list of string
            for (int i = lastParenthesis + 1; i < args.size(); i++) {
                afterParenthesis.add(args.get(i));
            }
            parenthesis.add(afterParenthesis);


        }
        return parenthesis;
    }
    */


    public static ArrayList<CustomType> parseNumberAndOperator (ArrayList<CustomType> args) throws IllegalConstruction {

        //creation of the list of CustomType that will be returned
        // les nombres seront des MyNumber et les opérateurs des String
        ArrayList<CustomType> numberAndOperator = new ArrayList<>();

        //temp est une ParserList pouvant être une list ayant un type pour reconnaitre le type de nombre
        ParserList temp = new ParserList();
        TypeEnum type = TypeEnum.IntegerValue;



        for (int i = 0; i < args.size(); i++) {

            if (args.get(i).isString()) {

                // Check if the element is a number
                if (args.get(i).isString() && (args.get(i).getString()).matches("[0-9]")){
                    temp.add((args.get(i).getString()));
                }
                // Check if the element is a decimal point and the next element is a number (RealValue)
                else if (args.get(i).isString() && (args.get(i).getString()).equals(".") && (args.get(i+1).getString()).matches("[0-9]")) {
                    temp.add(args.get(i).getString());
                    type = TypeEnum.RealValue;
                }
                else if (i == args.size()-1 & args.get(i).isString() && (args.get(i).getString()).equals(".")){
                    throw new IllegalConstruction();
                }

                /**
                 * Here it's the end of a number and the beginning of an operator
                 */

                // Check if the next element is an operator or a parenthesis closing or i for complexValue
                else if (args.get(i).isString() && !((args.get(i).getString()).equals("."))) {


                    // Check if the next element is not a parenthesis closing or i for complexValue
                    if (!(args.get(i).getString().equals(")")) && !(args.get(i).getString().equals("i")) ) {

                        // if the last element was a parenthesis list
                        if (i>0 && args.get(i-1).isListOfCustomTypes()) {
                            numberAndOperator.add(new CustomType(args.get(i).getString()));
                            temp =  new ParserList();
                            type = TypeEnum.IntegerValue;
                        }

                        else if (i>0 && args.get(i-1).isString() && args.get(i-1).getString().equals("i")) {
                            numberAndOperator.add(new CustomType(args.get(i).getString()));
                            temp =  new ParserList();
                            type = TypeEnum.IntegerValue;
                        }
                        else if (beforepriority.contains(args.get(i).getString())) {

                            if (args.size() == 1) {
                                throw new IllegalConstruction();
                            }

                            numberAndOperator.add(new CustomType(args.get(i).getString()));
                            temp =  new ParserList();
                            type = TypeEnum.IntegerValue;
                        }
                        else if (afterpriority.contains(args.get(i).getString())) {

                            if (temp.size() == 0) {
                                throw new IllegalConstruction();
                            }
                            else {
                                temp.setType(type);
                                numberAndOperator.add(new CustomType(temp));
                                numberAndOperator.add(new CustomType(args.get(i).getString()));
                                numberAndOperator.add(new CustomType(""));

                                temp = new ParserList();
                                type = TypeEnum.IntegerValue;
                            }

                        }
                        else {
                            if (i == args.size()-1 && !(args.get(i).getString().matches("[0-9]"))){
                                throw new IllegalConstruction();
                            }
                            temp.setType(type);
                            numberAndOperator.add(new CustomType(temp));
                            numberAndOperator.add(new CustomType(args.get(i).getString()));
                            temp = new ParserList();
                            type = TypeEnum.IntegerValue;
                        }

                    }

                    // if the element is an "i" of complex value
                    else if (!args.get(i).getString().equals(")") && args.get(i).getString().equals("i")) {


                        // if the number is a real value or just an integer value
                        if (type == TypeEnum.RealValue) {
                            type = TypeEnum.ComplexRealValue;
                        }
                        else {
                            type = TypeEnum.ComplexValue;
                        }
                        if ((i>0 &&  !(args.get(i-1).getString().matches("[0-9]"))) ) {
                            temp.add("1");
                            numberAndOperator.add(new CustomType(temp));
                        }
                        else if (i==0) {
                            temp.add("1");
                            numberAndOperator.add(new CustomType(temp));
                        }

                        temp.setType(type);
                        numberAndOperator.add(new CustomType(temp));
                        temp =  new ParserList();
                        type = TypeEnum.IntegerValue;

                    }

                    // the number is a integer or real value
                    else {
                        numberAndOperator.add(new CustomType(temp));
                        temp =  new ParserList();
                        type = TypeEnum.IntegerValue;
                    }
                }

            }
            // if the element is a list for exemple : parenthesis, sqrt, ...
            else if (args.get(i).isListOfCustomTypes()) {
                numberAndOperator.add(new CustomType(parseNumberAndOperator(args.get(i).getCustomTypes())));
            }
        }


        if (temp.size() != 0 && (numberAndOperator.size()==0 || !(numberAndOperator.get(numberAndOperator.size()-1).equals(temp))) ){
            temp.setType(type);
            numberAndOperator.add(new CustomType(temp));
        }





        return myNumberTransform(numberAndOperator);
    }

    public static ArrayList<CustomType> myNumberTransform (ArrayList<CustomType> args) {

        ArrayList<CustomType> myNumberAndOperator = new ArrayList<>();

        for (int j = 0; j < args.size(); j++) {
            if (args.get(j).isParserList()) {

                if ((args.get(j)).getParserList().getType() == TypeEnum.IntegerValue) {

                    StringBuilder numberString = new StringBuilder();

                    for (int k = 0; k< (args.get(j)).getParserList().size(); k++) {
                        numberString.append((args.get(j)).getParserList().get(k));
                    }

                    if (numberString.toString().equals("")) {
                        numberString = new StringBuilder("0");
                    }

                    MyNumber number;

                    if (numberString.toString().length() > 10) {
                        number = new MyNumber(new RealValue(new BigDecimal(numberString.toString())));
                    }
                    else {
                        number = new MyNumber(Integer.parseInt(numberString.toString()));
                    }

                    myNumberAndOperator.add(new CustomType(number));

                }

                if ((args.get(j)).getParserList().getType() == TypeEnum.ComplexValue) {

                    StringBuilder numberString = new StringBuilder();

                    for (int k = 0; k< (args.get(j)).getParserList().size(); k++) {
                        numberString.append((args.get(j)).getParserList().get(k));
                    }

                    MyNumber number = new MyNumber(0, Integer.parseInt(numberString.toString()));

                    myNumberAndOperator.add(new CustomType(number));
                }

                if ((args.get(j)).getParserList().getType() == TypeEnum.RealValue) {

                    StringBuilder numberString = new StringBuilder();

                    for (int k = 0; k< (args.get(j)).getParserList().size(); k++) {
                        numberString.append((args.get(j)).getParserList().get(k));
                    }

                    MyNumber number = new MyNumber(new RealValue(Double.parseDouble(numberString.toString())));

                    myNumberAndOperator.add(new CustomType(number));
                }

                if ((args.get(j)).getParserList().getType() == TypeEnum.ComplexRealValue) {

                    StringBuilder numberString = new StringBuilder();

                    for (int k = 0; k< (args.get(j)).getParserList().size(); k++) {
                        numberString.append((args.get(j)).getParserList().get(k));
                    }

                    MyNumber number = new MyNumber(new RealValue(0), new RealValue(Double.parseDouble(numberString.toString())));

                    myNumberAndOperator.add(new CustomType(number));
                }

            }
            else {
                if (args.get(j).isString()) {
                    myNumberAndOperator.add(new CustomType(args.get(j).getString()));
                }
                else if (args.get(j).isListOfCustomTypes()) {
                    myNumberAndOperator.add(new CustomType(args.get(j).getCustomTypes()));
                }
            }
        }
        return myNumberAndOperator;

    }

/*
    public static ArrayList<CustomType> makePriority (ArrayList<CustomType> args) throws IllegalConstruction {

        ArrayList<CustomType> tempPar = new ArrayList<>();
        boolean sqrt = false;
        boolean exp = false;


        if (args.get(0).isString()){

            if (args.get(0).getString().equals("sqrt")) {
                sqrt = true;
            }
            else if (args.get(0).getString().equals("exp") || args.get(0).getString().equals("^")) {
                exp = true;
            }

        }

        for (int i = 0; i< args.size(); i++) {

            // Check if the element is a list and call makePriority on it
            if (args.get(i).isListOfCustomTypes()) {

                CustomType t = new CustomType(makePriority(args.get(i).getCustomTypes()).get(0).getMyNumber());

                tempPar.add(t);
            }

            else {
                tempPar.add(args.get(i));
            }
        }

        ArrayList<CustomType> tempMod = new ArrayList<>();

        for (int i = 0; i< tempPar.size(); i++) {

            // Check if the elemen is a number and if the next element is a string = "*" or "/"

            if (tempPar.get(i).isMyNumber()) {

                if (i > args.size()-1 && tempPar.get(i+1).isString()) {

                    if (tempPar.get(i + 1).getString().equals("%")) {
                        tempMod = modParsing(i + 1, tempPar);
                        if (i + 2 >= tempPar.size() - 1) {
                            break;
                        }
                    }
                    else {
                        tempMod.add(tempPar.get(i));
                        tempMod.add(tempPar.get(i + 1));
                    }

                }

            }
            if (i == tempPar.size()-1) {
                tempMod.add(tempPar.get(i));
            }



        }

        ArrayList<CustomType> tempMultDiv = new ArrayList<>();
        for (int i = 0; i< tempMod.size(); i++) {

            // Check if the elemen is a number and if the next element is a string = "*" or "/"

            if (tempMod.get(i).isMyNumber()) {

                if (i > args.size()-1 && tempMod.get(i+1).isString()){

                    if (tempMod.get(i+1).getString().equals("*") || tempMod.get(i+1).getString().equals("/")) {
                        tempMultDiv = multdivParsing(i+1, tempMod);
                        if (i+2 >= tempMod.size()-1) {
                            break;
                        }
                    }
                    else {
                        tempMultDiv.add(tempMod.get(i));
                        tempMultDiv.add(tempMod.get(i+1));
                    }

                }

            }
            if (i == tempMod.size()-1) {
                tempMultDiv.add(tempMod.get(i));
            }



        }


        ArrayList<CustomType> tempAddMin = new ArrayList<>();
        for (int i = 0; i< tempMultDiv.size(); i++) {

            if (tempMultDiv.get(i).isMyNumber()) {

                if (i > args.size()-1 && tempMultDiv.get(i+1).isString()){

                    if (tempMultDiv.get(i+1).getString().equals("+") || tempMultDiv.get(i+1).getString().equals("-")) {
                        tempMultDiv = addsubParsing(i+1, tempMod);
                        if (i+2 >= tempMultDiv.size()-1) {
                            break;
                        }
                    }
                    else {
                        tempAddMin.add(tempMod.get(i));
                        tempAddMin.add(tempMod.get(i+1));
                    }

                }

            }
            if (i == tempMod.size()-1) {
                tempAddMin.add(tempMod.get(i));
            }

        }


        if (sqrt) {
            sqrtParsing(tempAddMin);
        }

        if (exp) {
            expParsing(tempAddMin);
        }


        return tempAddMin;

    }
*/
    public static ArrayList<CustomType> newMakePriority (ArrayList<CustomType> args) throws IllegalConstruction {


        if (args.get(0).isString() && beforepriority.contains(args.get(0).getString())) {

            String operation = args.get(0).getString();
            args.remove(0);
            args = newMakePriority(args);

            switch (operation) {
                case "√" -> args = sqrtParsing(args);
                case "exp" -> args = expParsing(args);
                case "||" -> args = modulusParsing(args);
                case "sin" -> args = sinParsing(args);
                case "cos" -> args = cosParsing(args);
                case "tan" -> args = tanParsing(args);
                case "cot" -> args = cotParsing(args);
                case "asin" -> args = asinParsing(args);
                case "acos" -> args = acosParsing(args);
                case "atan" -> args = atanParsing(args);
                case "acot" -> args = acotParsing(args);
                case "ln" -> args = lnParsing(args);
                case "RAD" -> args = radParsing(args);
                case "DEG" -> args = degParsing(args);
                case "RNG" -> args = genParsing(args);
                default -> new Exception("IllegalConstruction");


            }
        }

        for (int i = 0; i< args.size(); i++) {

            // Check if the element is a list and call makePriority on it
            if (args.get(i).isListOfCustomTypes()) {

                MyNumber t = newMakePriority(args.get(i).getCustomTypes()).get(0).getMyNumber();

                args.set(i, new CustomType(t));
            }
        }

        for (int i = 0; i < priority.size(); i++) {


            for (int j = 1; j < args.size(); j++) {

                if (args.get(j).isString()) {

                    if (priority.get(i) instanceof String && args.get(j).getString().equals(priority.get(i))) {


                        ArrayList<CustomType> temp = new ArrayList<CustomType>();


                        temp.add(new CustomType(args.get(j - 1).getMyNumber()));
                        temp.add(new CustomType(args.get(j).getString()));
                        temp.add(new CustomType(args.get(j + 1).getMyNumber()));

                        args.remove(j - 1);
                        args.remove(j - 1);
                        args.remove(j - 1);

                        String a = temp.get(1).getString();
                        switch (a) {
                            case "%" -> args.add(j - 1, new CustomType(modParsing(1, temp).get(0).getMyNumber()));
                            case "!" -> args.add(j - 1, new CustomType(factoParsing(1, temp).get(0).getMyNumber()));
                            case "*", "/" -> args.add(j - 1, new CustomType(multdivParsing(1, temp).get(0).getMyNumber()));
                            case "+", "-" -> args.add(j - 1, new CustomType(addsubParsing(1, temp).get(0).getMyNumber()));
                            case "<", ">"  -> args.add(j - 1, new CustomType(lessGreatParsing(1, temp).get(0).getMyNumber()));
                            default -> new Exception("IllegalConstruction");
                        }


                        j = j - 1;
                    }

                    else {


                        if (priority.get(i) instanceof ArrayList) {
                                if (((ArrayList) priority.get(i)).contains(args.get(j).getString())) {
                                    ArrayList<CustomType> temp = new ArrayList<CustomType>();


                                    temp.add(new CustomType(args.get(j - 1).getMyNumber()));
                                    temp.add(new CustomType(args.get(j).getString()));
                                    temp.add(new CustomType(args.get(j + 1).getMyNumber()));

                                    args.remove(j - 1);
                                    args.remove(j - 1);
                                    args.remove(j - 1);

                                    String a = temp.get(1).getString();
                                    switch (a) {
                                        case "%" -> args.add(j - 1, new CustomType(modParsing(1, temp).get(0).getMyNumber()));
                                        case "!" -> args.add(j - 1, new CustomType(factoParsing(1, temp).get(0).getMyNumber()));
                                        case "*", "/" -> args.add(j - 1, new CustomType(multdivParsing(1, temp).get(0).getMyNumber()));
                                        case "+", "-" -> args.add(j - 1, new CustomType(addsubParsing(1, temp).get(0).getMyNumber()));
                                        case "<", ">"  -> args.add(j - 1, new CustomType(lessGreatParsing(1, temp).get(0).getMyNumber()));
                                        default -> new Exception("IllegalConstruction");
                                    }


                                    j = j - 1;
                                }
                        }
                    }

                }

            }

        }

        return args;


    }





    public static ArrayList<CustomType> multdivParsing (int i, ArrayList<CustomType> args) throws IllegalConstruction {

        List<Expression> params = new ArrayList<>();

        if (args.get(i).isString() && args.get(i).getString().equals("*")) {

            if (args.get(i - 1).isMyNumber() && args.get(i + 1).isMyNumber()) {
                Expression e;
                Calculator c = new Calculator();
                params.add(args.get(i - 1).getMyNumber());
                params.add(args.get(i + 1).getMyNumber());

                e = new Times(params);

                CustomType temp = new CustomType(c.eval(e));

                args.set(i - 1, temp);
                args.remove(i);
                args.remove(i);

            }


        }

        else if (args.get(i).isString() && args.get(i).getString().equals("/")) {

            if (args.get(i - 1).isMyNumber() && args.get(i + 1).isMyNumber()) {
                Expression e;
                Calculator c = new Calculator();
                params.add(args.get(i - 1).getMyNumber());
                params.add(args.get(i + 1).getMyNumber());

                e = new Divides(params);

                CustomType temp = new CustomType(c.eval(e));

                args.set(i - 1, temp);
                args.remove(i);
                args.remove(i);

            }

        }


        return args;
    }

    public static ArrayList<CustomType> modParsing (int i, ArrayList<CustomType> args) throws IllegalConstruction {

        List<Expression> params = new ArrayList<>();

        if (args.get(i).isString() && args.get(i).getString().equals("%")) {

            if (args.get(i - 1).isMyNumber() && args.get(i + 1).isMyNumber()) {
                Expression e;
                Calculator c = new Calculator();
                params.add(args.get(i - 1).getMyNumber());
                params.add(args.get(i + 1).getMyNumber());

                e = new Modulo(params);

                CustomType temp = new CustomType(c.eval(e));

                args.set(i - 1, temp);
                args.remove(i);
                args.remove(i);

            }

        }

        return args;
    }

    public static ArrayList<CustomType> addsubParsing (int i, ArrayList<CustomType> args) throws IllegalConstruction {

        List<Expression> params = new ArrayList<>();

        if (args.get(i).isString() && args.get(i).getString().equals("+")) {

            if (args.get(i - 1).isMyNumber() && args.get(i + 1).isMyNumber()) {
                Expression e;
                Calculator c = new Calculator();
                params.add(args.get(i - 1).getMyNumber());
                params.add(args.get(i + 1).getMyNumber());

                e = new Plus(params);

                CustomType temp = new CustomType(c.eval(e));

                args.set(i - 1, temp);
                args.remove(i);
                args.remove(i);

            }

        }

        else if (args.get(i).isString() && args.get(i).getString().equals("-")) {

            if (args.get(i - 1).isMyNumber() && args.get(i + 1).isMyNumber()) {
                Expression e;
                Calculator c = new Calculator();
                params.add(args.get(i - 1).getMyNumber());
                params.add(args.get(i + 1).getMyNumber());

                e = new Minus(params);

                CustomType temp = new CustomType(c.eval(e));

                args.set(i - 1, temp);
                args.remove(i);
                args.remove(i);

            }

        }

        return args;


    }

    public static ArrayList<CustomType> lessGreatParsing (int i, ArrayList<CustomType> args) throws IllegalConstruction {

        List<Expression> params = new ArrayList<>();

        if (args.get(i).isString() && args.get(i).getString().equals("<")) {

            if (args.get(i - 1).isMyNumber() && args.get(i + 1).isMyNumber()) {
                Expression e;
                Calculator c = new Calculator();
                params.add(args.get(i - 1).getMyNumber());
                params.add(args.get(i + 1).getMyNumber());

                e = new LessThan(params);

                CustomType temp = new CustomType(c.eval(e));

                args.set(i - 1, temp);
                args.remove(i);
                args.remove(i);

            }

        }

        else if (args.get(i).isString() && args.get(i).getString().equals(">")) {

            if (args.get(i - 1).isMyNumber() && args.get(i + 1).isMyNumber()) {
                Expression e;
                Calculator c = new Calculator();
                params.add(args.get(i - 1).getMyNumber());
                params.add(args.get(i + 1).getMyNumber());

                e = new BiggerThan(params);

                CustomType temp = new CustomType(c.eval(e));

                args.set(i - 1, temp);
                args.remove(i);
                args.remove(i);

            }

        }

        return args;
    }
    public static ArrayList<CustomType> sqrtParsing (ArrayList<CustomType> args) throws IllegalConstruction {

        List<Expression> params = new ArrayList<>();

        Expression e;
        Calculator c = new Calculator();

        params.add(args.get(0).getMyNumber());

        e = new Sqrt(params);

        CustomType temp = new CustomType(c.eval(e));



        args.set(0, temp);



        return args;
    }

    public static ArrayList<CustomType> expParsing (ArrayList<CustomType> args) throws IllegalConstruction {

        List<Expression> params = new ArrayList<>();

        Expression e;
        Calculator c = new Calculator();

        params.add(args.get(0).getMyNumber());

        e = new General_Exponential(params);

        CustomType temp = new CustomType(c.eval(e));



        args.set(0, temp);



        return args;
    }

    public static ArrayList<CustomType> modulusParsing (ArrayList<CustomType> args) throws IllegalConstruction {

        List<Expression> params = new ArrayList<>();

        Expression e;
        Calculator c = new Calculator();

        params.add(args.get(0).getMyNumber());

        e = new Modulus(params);

        CustomType temp = new CustomType(c.eval(e));

        args.set(0, temp);

        return args;
    }

    public static ArrayList<CustomType> factoParsing (int i, ArrayList<CustomType> args) throws IllegalConstruction {

        List<Expression> params = new ArrayList<>();

        Expression e;
        Calculator c = new Calculator();

        params.add(args.get(i-1).getMyNumber());

        e = new Factorial(params);

        CustomType temp = new CustomType(c.eval(e));



        args.set(0, temp);



        return args;
    }


    public static ArrayList<CustomType> lnParsing (ArrayList<CustomType> args) throws IllegalConstruction {

        List<Expression> params = new ArrayList<>();

        Expression e;
        Calculator c = new Calculator();

        params.add(args.get(0).getMyNumber());

        e = new Ln(params);

        CustomType temp = new CustomType(c.eval(e));

        args.set(0, temp);
        return args;
    }

    public static ArrayList<CustomType> sinParsing (ArrayList<CustomType> args) throws IllegalConstruction {

        List<Expression> params = new ArrayList<>();

        Expression e;
        Calculator c = new Calculator();

        params.add(args.get(0).getMyNumber());

        e = new Sinus(params);

        CustomType temp = new CustomType(c.eval(e));

        args.set(0, temp);

        return args;
    }

    public static ArrayList<CustomType> cosParsing (ArrayList<CustomType> args) throws IllegalConstruction {

        List<Expression> params = new ArrayList<>();

        Expression e;
        Calculator c = new Calculator();

        params.add(args.get(0).getMyNumber());

        e = new Cosinus(params);

        CustomType temp = new CustomType(c.eval(e));

        args.set(0, temp);

        return args;
    }
    public static ArrayList<CustomType> tanParsing (ArrayList<CustomType> args) throws IllegalConstruction {

        List<Expression> params = new ArrayList<>();

        Expression e;
        Calculator c = new Calculator();

        params.add(args.get(0).getMyNumber());

        e = new Tan(params);

        CustomType temp = new CustomType(c.eval(e));

        args.set(0, temp);

        return args;
    }

    public static ArrayList<CustomType> cotParsing (ArrayList<CustomType> args) throws IllegalConstruction{
        List<Expression> params = new ArrayList<>();

        Expression e;
        Calculator c = new Calculator();

        params.add(args.get(0).getMyNumber());

        e = new Cot(params);

        CustomType temp = new CustomType(c.eval(e));

        args.set(0, temp);

        return args;
    }

    public static ArrayList<CustomType> asinParsing (ArrayList<CustomType> args) throws IllegalConstruction {

        List<Expression> params = new ArrayList<>();

        Expression e;
        Calculator c = new Calculator();

        params.add(args.get(0).getMyNumber());

        e = new ArcSin(params);

        CustomType temp = new CustomType(c.eval(e));

        args.set(0, temp);

        return args;
    }

    public static ArrayList<CustomType> acosParsing (ArrayList<CustomType> args) throws IllegalConstruction {

        List<Expression> params = new ArrayList<>();

        Expression e;
        Calculator c = new Calculator();

        params.add(args.get(0).getMyNumber());

        e = new ArcCos(params);

        CustomType temp = new CustomType(c.eval(e));

        args.set(0, temp);

        return args;
    }

    public static ArrayList<CustomType> atanParsing (ArrayList<CustomType> args) throws IllegalConstruction {

        List<Expression> params = new ArrayList<>();

        Expression e;
        Calculator c = new Calculator();

        params.add(args.get(0).getMyNumber());

        e = new ArcTan(params);

        CustomType temp = new CustomType(c.eval(e));

        args.set(0, temp);

        return args;
    }

    public static ArrayList<CustomType> acotParsing (ArrayList<CustomType> args) throws IllegalConstruction {

        List<Expression> params = new ArrayList<>();

        Expression e;
        Calculator c = new Calculator();

        params.add(args.get(0).getMyNumber());

        e = new ArcCot(params);

        CustomType temp = new CustomType(c.eval(e));

        args.set(0, temp);

        return args;
    }

    public static ArrayList<CustomType> radParsing (ArrayList<CustomType> args) throws IllegalConstruction {

        List<Expression> params = new ArrayList<>();

        Expression e;
        Calculator c = new Calculator();

        params.add(args.get(0).getMyNumber());

        e = new Rad(params);

        CustomType temp = new CustomType(c.eval(e));

        args.set(0, temp);

        return args;
    }

    public static ArrayList<CustomType> degParsing (ArrayList<CustomType> args) throws IllegalConstruction {

        List<Expression> params = new ArrayList<>();

        Expression e;
        Calculator c = new Calculator();

        params.add(args.get(0).getMyNumber());

        e = new Deg(params);

        CustomType temp = new CustomType(c.eval(e));

        args.set(0, temp);

        return args;
    }

    public static ArrayList<CustomType> genParsing (ArrayList<CustomType> args) throws IllegalConstruction {

        List<Expression> params = new ArrayList<>();

        Expression e;
        Calculator c = new Calculator();

        params.add(args.get(0).getMyNumber());

        e = new Generator(params);

        CustomType temp = new CustomType(c.eval(e));

        args.set(0, temp);

        return args;
    }
}


