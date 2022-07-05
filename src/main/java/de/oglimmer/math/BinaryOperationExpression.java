package de.oglimmer.math;

public class BinaryOperationExpression implements Expression {

    private final Expression op1;
    private Expression op2;
    private final Operation op;

    public BinaryOperationExpression(Expression op1, Operation op) {
        this.op1 = op1;
        this.op = op;
    }

    public String toString() {
        return op1.toString() + op + op2;
    }

    @Override
    public Expression add(Expression toAdd) {
        if (toAdd instanceof Operation) {
            if (op2 == null) {
                throw new RuntimeException();
            }
            return new BinaryOperationExpression(this, (Operation) toAdd);
        } else {
            if (op2 != null) {
                throw new RuntimeException();
            }
            op2 = toAdd;
            return this;
        }
    }

    @Override
    public double resolve() {
        return op.resolve(op1, op2);
    }
}
