package de.oglimmer.math.astnode;

public class BinaryOperationExpression implements Expression {

    private final Expression op1;
    private Expression op2;
    private final Operation op;

    public BinaryOperationExpression(Expression op1, Operation op) {
        this.op1 = op1;
        this.op = op;
    }

    private BinaryOperationExpression(Expression op1, Operation op, Expression op2) {
        this.op1 = op1;
        this.op = op;
        this.op2 = op2;
    }

    @Override
    public Expression add(ASTNode toAdd) {
        if (op2 != null && op2.openForInput()) {
            op2.add(toAdd);
            return this;
        }
        if (toAdd instanceof Operation) {
            if (op2 == null) {
                throw new RuntimeException("Adding an operation to a BinaryOperationExpression is illegal if the second operand is still null");
            }
            Operation opToAdd = (Operation) toAdd;
            if (op.getPrecedence() < opToAdd.getPrecedence()) {
                return new BinaryOperationExpression(op1, op, new BinaryOperationExpression(op2, opToAdd));
            } else {
                return new BinaryOperationExpression(this, opToAdd);
            }
        } else {
            if (!(toAdd instanceof Expression)) {
                throw new RuntimeException("Illegal class on second operand. " + toAdd.getClass().getName());
            }
            op2 = (Expression) toAdd;
            return this;
        }
    }

    @Override
    public double resolve() {
        return op.resolve(op1, op2);
    }

    @Override
    public boolean openForInput() {
        return op2 == null || op2.openForInput();
    }

    @Override
    public String toString() {
        return op1.toString() + op + op2;
    }

}
