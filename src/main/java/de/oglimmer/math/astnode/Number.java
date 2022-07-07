package de.oglimmer.math.astnode;

import java.util.Map;

public class Number implements Expression {
    private final double val;

    public Number(double val) {
        this.val = val;
    }

    @Override
    public Expression add(ASTNode toAdd) {
        if (!(toAdd instanceof Operation)) {
            throw new RuntimeException("illegal class " + toAdd.getClass().getName() + " on val " + val);
        }
        return new BinaryOperationExpression(this, (Operation) toAdd);
    }

    @Override
    public double resolve(Map<String, Double> vars) {
        return val;
    }

    @Override
    public boolean openForInput() {
        return false;
    }

    @Override
    public Expression simplify() {
        return this;
    }

    @Override
    public String toString() {
        return Double.toString(val);
    }
}
