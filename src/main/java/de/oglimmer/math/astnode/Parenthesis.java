package de.oglimmer.math.astnode;

import java.util.Map;

public class Parenthesis implements Expression {

    private Expression nestedExp;

    private boolean closed;

    @Override
    public Expression add(ASTNode toAdd) {
        if (closed) {
            return new BinaryOperationExpression(this, (Operation) toAdd);
        }
        if (nestedExp == null) {
            nestedExp = (Expression) toAdd;
        } else {
            if (toAdd instanceof Parenthesis && !nestedExp.openForInput()) {
                closed = true;
            } else {
                nestedExp = nestedExp.add(toAdd);
            }
        }
        return this;
    }

    @Override
    public double resolve(Map<String, Double> vars) {
        return nestedExp.resolve(vars);
    }

    @Override
    public boolean openForInput() {
        return !closed;
    }

    @Override
    public String toString() {
        return nestedExp != null ? "(" + nestedExp + ')' : "(";
    }
}
