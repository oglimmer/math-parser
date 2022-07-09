package de.oglimmer.math.astnode;

import de.oglimmer.math.InvalidStateException;

import java.util.Map;

public class Variable implements Expression {
    private final String name;

    public Variable(String name) {
        this.name = name;
    }

    @Override
    public Expression add(ASTNode toAdd) {
        if (!(toAdd instanceof Operation)) {
            throw new InvalidStateException("illegal class " + toAdd.getClass().getName() + " on name " + name);
        }
        return new BinaryOperationExpression(this, (Operation) toAdd);
    }

    @Override
    public double resolve(Map<String, Double> vars) {
        Double val = vars.get(name);
        if (val == null) {
            throw new InvalidStateException("Cannot find value for variable " + name);
        }
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
        return name;
    }
}
