package de.oglimmer.math.astnode;

public class PlusOperation extends Operation {
    public PlusOperation() {
        super("+", 1);
    }

    @Override
    public double resolve(Expression... expressions) {
        return expressions[0].resolve() + expressions[1].resolve();
    }
}
