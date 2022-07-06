package de.oglimmer.math;

import de.oglimmer.math.astnode.Expression;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.HashMap;

public class ParenthesisTest {

    @Test
    public void simpleParenthesis() {
        String input = "(2)";
        Expression n = new FunctionParser().parse(input);
        Assertions.assertEquals(2d, n.resolve(new HashMap<>()));
    }

    @Test
    public void simpleAdditionInParenthesis() {
        String input = "(2+3)";
        Expression n = new FunctionParser().parse(input);
        Assertions.assertEquals(5d, n.resolve(new HashMap<>()));
    }

    @Test
    public void additionInParenthesisAndMultiplication() {
        String input = "(2+3)*4";
        Expression n = new FunctionParser().parse(input);
        Assertions.assertEquals(20d, n.resolve(new HashMap<>()));
    }

    @Test
    public void multiplicationInParenthesisAndAddition() {
        String input = "(2*3)+4";
        Expression n = new FunctionParser().parse(input);
        Assertions.assertEquals(10d, n.resolve(new HashMap<>()));
    }

    @Test
    public void twoAdditionInParenthesisAndMultiplication() {
        String input = "(2+3)*(4+5)";
        Expression n = new FunctionParser().parse(input);
        Assertions.assertEquals(45d, n.resolve(new HashMap<>()));
    }

    @Test
    public void threeAdditionInParenthesisAndMultiplication() {
        String input = "(2+3)*(4+5)*(1+1)";
        Expression n = new FunctionParser().parse(input);
        Assertions.assertEquals(90d, n.resolve(new HashMap<>()));
    }

    @Test
    public void doubleParenthesis() {
        String input = "((4+2)-(8+2))/2";
        Expression n = new FunctionParser().parse(input);
        Assertions.assertEquals(-2d, n.resolve(new HashMap<>()));
    }


}
