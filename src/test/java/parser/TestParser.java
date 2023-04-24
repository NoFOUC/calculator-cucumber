package parser;

import static org.junit.jupiter.api.Assertions.*;

import calculator.IntegerValue;
import calculator.MyNumber;
import calculator.RationalValue;
import calculator.RealValue;
import org.junit.jupiter.api.*;

import java.util.ArrayList;
import java.util.Arrays;

public class TestParser {

    ArrayList <Object> calcul = new ArrayList<>();

    @BeforeEach
    public void setUp() {

        calcul.add(new ArrayList<Object>(Arrays.asList("exp", "0")));
        calcul.add("*");
        calcul.add("2");
        calcul.add(".");
        calcul.add("3");
        calcul.add("*");
        calcul.add(new ArrayList<Object>(Arrays.asList("1", "*", "3")));
        calcul.add("+");
        calcul.add(new ArrayList<Object>(Arrays.asList("sqrt", "1")));
        calcul.add("*");
        calcul.add("2");
        calcul.add(".");
        calcul.add("3");
        calcul.add("i");
        calcul.add("*");
        calcul.add("3");
        calcul.add("+");
        calcul.add("2");
        calcul.add("%");
        calcul.add("3");

    }


    @Test
    public void testParsing () {

        try {
            MyNumber a = Parser.main(calcul);
            assertEquals("8.90 + 6.90i",a.toString());

            // pb avec approximation
            //assertEquals(a, new MyNumber(new RealValue(8.90), new RealValue(6.90)));

        } catch (Exception e) {
            fail();
        }


    }

    @Test
    public void testPriority() {


    }





}
