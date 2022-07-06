package de.oglimmer.math;

import de.oglimmer.math.astnode.Expression;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

public class VariableTest {

    @Test
    public void simpleVariable() {
        String input = "x";
        Expression n = new FunctionParser().parse(input);
        Map<String, Double> vars = new HashMap<>();
        vars.put("x", 1d);
        Assertions.assertEquals(1d, n.resolve(vars));
    }

    @Test
    public void multiCharVariable() {
        String input = "myVariable";
        Expression n = new FunctionParser().parse(input);
        Map<String, Double> vars = new HashMap<>();
        vars.put("myVariable", 1d);
        Assertions.assertEquals(1d, n.resolve(vars));
    }

    @Test
    public void formulaWithVariable() {
        String input = "(x+2)*3+5";
        Expression n = new FunctionParser().parse(input);
        Map<String, Double> vars = new HashMap<>();
        vars.put("x", 5d);
        Assertions.assertEquals(26d, n.resolve(vars));
    }

}
