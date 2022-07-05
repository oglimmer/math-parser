package de.oglimmer.math;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class DecimalTest {

    @Test
    public void simpleAddition() {
        String input = "23.3+45.2";
        Expression n = new FunctionParser().parse(input);
        Assertions.assertEquals(68.5d, n.resolve());
    }

    @Test
    public void simpleSubtraction() {
        String input = "123.7-23.1";
        Expression n = new FunctionParser().parse(input);
        Assertions.assertEquals(100.6d, n.resolve());
    }

    @Test
    public void doubleAddition() {
        String input = "23.0+45.3+1.2";
        Expression n = new FunctionParser().parse(input);
        Assertions.assertEquals(69.5d, n.resolve());
    }

    @Test
    public void doubleSubtraction() {
        String input = "123.9-23.8-33.5";
        Expression n = new FunctionParser().parse(input);
        Assertions.assertEquals(66.60000000000001, n.resolve());
    }

    @Test
    public void additionAndSubtraction() {
        String input = "123.9+23.8-33.7";
        Expression n = new FunctionParser().parse(input);
        Assertions.assertEquals(114.00000000000001, n.resolve());
    }

    @Test
    public void multipleAdditionAndSubtraction() {
        String input = "123.3+23.2-33.6-13.8+100.3+5.0-25.2";
        Expression n = new FunctionParser().parse(input);
        Assertions.assertEquals(179.20000000000002, n.resolve());
    }
}
