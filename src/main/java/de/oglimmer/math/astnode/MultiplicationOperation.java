package de.oglimmer.math.astnode;

import java.util.Map;

public class MultiplicationOperation extends Operation {
    public MultiplicationOperation() {
        super("*", 2);
    }

    @Override
    public double resolve(Map<String, Double> vars, Expression... expressions) {
        return expressions[0].resolve(vars) * expressions[1].resolve(vars);
    }
}
