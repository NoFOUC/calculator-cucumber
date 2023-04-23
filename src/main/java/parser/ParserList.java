package parser;

import java.util.ArrayList;

public class ParserList extends ArrayList<Object> {

    // an Parserlist if an Arraylist with a type (IntegerValue, RationalValue, RealValue)

    private TypeEnum type;

    public ParserList() {
        this.type = null;
    }
    public ParserList(TypeEnum type) {
        this.type = type;
    }

    public TypeEnum getType() {
        return type;
    }

    public void setType(TypeEnum type) {
        this.type = type;
    }

    public ParserList set(int i, Object o) {
        return (ParserList) super.set(i, o);
    }
}
