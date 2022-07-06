package de.oglimmer.math.astnode;

import java.util.Map;

public class MinusOperation extends Operation {
    public MinusOperation() {
        super("-", 1);
    }

    @Override
    public double resolve(Map<String, Double> vars, Expression... expressions) {
        return expressions[0].resolve(vars) - expressions[1].resolve(vars);
    }
}
