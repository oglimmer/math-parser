package de.oglimmer.math.astnode;

public class MinusOperation extends Operation {
    public MinusOperation() {
        super("-", 1);
    }

    @Override
    public double resolve(Expression... expressions) {
        return expressions[0].resolve() - expressions[1].resolve();
    }
}
