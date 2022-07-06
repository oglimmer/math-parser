package de.oglimmer.math.astnode;

public class DivisionOperation extends Operation {
    public DivisionOperation() {
        super("/", 2);
    }

    @Override
    public double resolve(Expression... expressions) {
        return expressions[0].resolve() / expressions[1].resolve();
    }
}
