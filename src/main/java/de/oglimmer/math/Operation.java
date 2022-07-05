package de.oglimmer.math;

public class Operation implements Expression {

    public static final Expression PLUS = new Operation(Symbol.PLUS);
    public static final Expression MINUS = new Operation(Symbol.MINUS);
    private final Symbol symbol;

    public Operation(Symbol symbol) {
        this.symbol = symbol;
    }

    public String toString() {
        return symbol.toString();
    }

    @Override
    public Expression add(Expression toAdd) {
        throw new RuntimeException();
    }

    @Override
    public double resolve() {
        throw new RuntimeException();
    }

    public double resolve(Expression e1, Expression e2) {
        return symbol.resolve(e1, e2);
    }
}
