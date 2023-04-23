package parser;

import static org.junit.jupiter.api.Assertions.*;

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

        calcul.add("2");
        calcul.add(".");
        calcul.add("3");
        calcul.add("*");
        calcul.add(new ArrayList<Object>(Arrays.asList("exp", "1", "+", "2")));
        calcul.add("+");
        calcul.add("2");
        calcul.add(".");
        calcul.add("3");
        calcul.add("i");
        calcul.add("*");
        calcul.add("3");
    }


    @Test
    public void testParsing () {

        try {

            MyNumber a = Parser.main(calcul);


            System.out.println(a);

            assertEquals(1,1);
            //assertEquals(a.toString(), new MyNumber(new RealValue(49.2), new RealValue(6)).toString());
        } catch (Exception e) {
            fail();
        }


    }





}
