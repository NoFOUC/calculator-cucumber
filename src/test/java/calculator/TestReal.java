package calculator;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TestReal {

    BigDecimal value = BigDecimal.valueOf(1536874929273910.7);

    RealValue number;

    @BeforeEach
    void setUp() {
        number = new RealValue(value);
    }

    @Test
    void testToString () {
        assertEquals("1.53687E15", number.toString().replace(".", ".").replace(",", "."));
    }





}