package de.oglimmer.math;

import de.oglimmer.math.astnode.Expression;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.HashMap;

public class PostfixOperatorTest {

    @Test
    public void sqrt() {
        String input = "sqrt(9)";
        Expression n = new FunctionParser().parse(input);
        Assertions.assertEquals(3d, n.resolve(new HashMap<>()));
    }

    @Test
    public void sqrtWithExpression() {
        String input = "sqrt(3+6)";
        Expression n = new FunctionParser().parse(input);
        Assertions.assertEquals(3d, n.resolve(new HashMap<>()));
    }

    @Test
    public void sqrtAndAddition() {
        String input = "2+sqrt(9)+1";
        Expression n = new FunctionParser().parse(input);
        Assertions.assertEquals(6d, n.resolve(new HashMap<>()));
    }

    @Test
    public void parenthesisAndAdditionAndMultiplicationAndSqrt() {
        String input = "(2+sqrt((2+2)*4))*3";
        Expression n = new FunctionParser().parse(input);
        Assertions.assertEquals(18d, n.resolve(new HashMap<>()));
    }

    @Test
    public void sin() {
        String input = "sin(pi/2)";
        Expression n = new FunctionParser().parse(input);
        Assertions.assertEquals(1d, n.resolve(new HashMap<>()));
    }

    @Test
    public void fullTrigonometry() {
        String input = "sin(1)*cos(2)*tan(3)*asin(.1)*acos(.2)*atan(.3)*sqrt(7)*log(8)*logten(9)";
        Expression n = new FunctionParser().parse(input);
        Assertions.assertEquals(0.010477076341892384d, n.resolve(new HashMap<>()));
    }

    @Test
    public void constantPi() {
        String input = "pi";
        Expression n = new FunctionParser().parse(input);
        Assertions.assertEquals(Math.PI, n.resolve(new HashMap<>()));
    }

    @Test
    public void constantE() {
        String input = "e";
        Expression n = new FunctionParser().parse(input);
        Assertions.assertEquals(Math.E, n.resolve(new HashMap<>()));
    }

}
