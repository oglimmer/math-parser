package de.oglimmer.math.astnode;

import de.oglimmer.math.InvalidStateException;

public class Number implements Expression {
    private final long val;

    public Number(long val) {
        this.val = val;
    }

    @Override
    public Expression add(ASTNode toAdd) {
        if (!(toAdd instanceof Operation)) {
            throw new InvalidStateException("illegal class " + toAdd.getClass().getName() + " on val " + val);
        }
        return new BinaryOperationExpression(this, (Operation) toAdd);
    }

    @Override
    public long resolve() {
        return val;
    }

    @Override
    public String toString() {
        return Double.toString(val);
    }
}
