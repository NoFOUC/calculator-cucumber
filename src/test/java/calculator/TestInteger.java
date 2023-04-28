package calculator;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TestInteger {

    int value = 1727846937;

    MyNumber number;

    @BeforeEach
    void setUp() {
        number = new MyNumber(value);
    }

    @Test
    void testToString () {

        assertEquals("1.72785E9", number.toString());

    }





}
