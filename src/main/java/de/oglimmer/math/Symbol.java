package de.oglimmer.math;

public enum Symbol {
    PLUS("+"), MINUS("-");

    private final String c;

    Symbol(String c) {
        this.c = c;
    }

    @Override
    public String toString() {
        return c;
    }

    public double resolve(Expression e1, Expression e2) {
        if (c.equals("+")) {
            return e1.resolve() + e2.resolve();
        } else if (c.equals("-")) {
            return e1.resolve() - e2.resolve();
        }
        throw new RuntimeException();
    }
}
