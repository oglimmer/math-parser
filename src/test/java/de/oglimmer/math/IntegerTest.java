package de.oglimmer.math;

import de.oglimmer.math.astnode.Expression;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class IntegerTest {

    @Test
    public void simpleAddition() {
        String input = "23+45";
        Expression n = new FunctionParser().parse(input);
        Assertions.assertEquals(68d, n.resolve());
    }

    @Test
    public void simpleSubtraction() {
        String input = "123-23";
        Expression n = new FunctionParser().parse(input);
        Assertions.assertEquals(100d, n.resolve());
    }

    @Test
    public void doubleAddition() {
        String input = "23+45+1";
        Expression n = new FunctionParser().parse(input);
        Assertions.assertEquals(69d, n.resolve());
    }

    @Test
    public void doubleSubtraction() {
        String input = "123-23-33";
        Expression n = new FunctionParser().parse(input);
        Assertions.assertEquals(67d, n.resolve());
    }

    @Test
    public void additionAndSubtraction() {
        String input = "123+23-33";
        Expression n = new FunctionParser().parse(input);
        Assertions.assertEquals(113d, n.resolve());
    }

    @Test
    public void multipleAdditionAndSubtraction() {
        String input = "123+23-33-13+100+5-25";
        Expression n = new FunctionParser().parse(input);
        Assertions.assertEquals(180d, n.resolve());
    }

    @Test
    public void simpleMultiplication() {
        String input = "6*4";
        Expression n = new FunctionParser().parse(input);
        Assertions.assertEquals(24d, n.resolve());
    }

    @Test
    public void doubleMultiplication() {
        String input = "6*4*3";
        Expression n = new FunctionParser().parse(input);
        Assertions.assertEquals(72d, n.resolve());
    }

    @Test
    public void addingAndMultiplication() {
        String input = "2+3*4";
        Expression n = new FunctionParser().parse(input);
        Assertions.assertEquals(14d, n.resolve());
    }

    @Test
    public void addingAndMultiplication2() {
        String input = "3*4+2";
        Expression n = new FunctionParser().parse(input);
        Assertions.assertEquals(14d, n.resolve());
    }

    @Test
    public void addingAndMultiplication3() {
        String input = "3*4+2*4";
        Expression n = new FunctionParser().parse(input);
        Assertions.assertEquals(20d, n.resolve());
    }

    @Test
    public void addingAndMultiplication5() {
        String input = "3*4+2*4+8";
        Expression n = new FunctionParser().parse(input);
        Assertions.assertEquals(28d, n.resolve());
    }

    @Test
    public void addingAndMultiplication6() {
        String input = "2+3*4+2*4+8";
        Expression n = new FunctionParser().parse(input);
        Assertions.assertEquals(30d, n.resolve());
    }

    @Test
    public void addingAndSubtractionAndMultiplication() {
        String input = "30-2+3*4+2*4+8";
        Expression n = new FunctionParser().parse(input);
        Assertions.assertEquals(56d, n.resolve());
    }

    @Test
    public void addingNegativeInteger() {
        String input = "2+-1";
        Expression n = new FunctionParser().parse(input);
        Assertions.assertEquals(1d, n.resolve());
    }

    @Test
    public void addingNegativeIntegerToNegativeNumber() {
        String input = "-2+-1";
        Expression n = new FunctionParser().parse(input);
        Assertions.assertEquals(-3d, n.resolve());
    }

    @Test
    public void multiplyNegativeIntegerToNegativeNumber() {
        String input = "-2*-1";
        Expression n = new FunctionParser().parse(input);
        Assertions.assertEquals(2d, n.resolve());
    }

}
