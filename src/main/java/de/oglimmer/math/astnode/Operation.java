package de.oglimmer.math.astnode;

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

    abstract double resolve(Expression... expressions);

    @Override
    public boolean openForInput() {
        return false;
    }

    @Override
    public String toString() {
        return symbol;
    }
}

