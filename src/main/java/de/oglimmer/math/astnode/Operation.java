package de.oglimmer.math.astnode;

import java.util.Map;

public abstract class Operation implements ASTNode {

    private final String symbol;
    private final int precedence;

    public Operation(String symbol, int precedence) {
        this.symbol = symbol;
        this.precedence = precedence;
    }

    public int getPrecedence() {
        return precedence;
    }

    abstract double resolve(Map<String, Double> vars, Expression... expressions);

    @Override
    public boolean openForInput() {
        return false;
    }

    @Override
    public String toString() {
        return symbol;
    }
}

