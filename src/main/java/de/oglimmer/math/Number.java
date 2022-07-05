package de.oglimmer.math;

public class Number implements Expression {
    private final double val;

    Number(double val) {
        this.val = val;
    }

    public String toString() {
        return Double.toString(val);
    }

    @Override
    public Expression add(Expression toAdd) {
        if (!(toAdd instanceof Operation)) {
            throw new RuntimeException("illegal");
        }
        return new BinaryOperationExpression(this, (Operation) toAdd);
    }

    @Override
    public double resolve() {
        return val;
    }

}
