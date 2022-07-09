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

}
