package de.oglimmer.math.astnode;

import de.oglimmer.math.InvalidFormulaException;
import de.oglimmer.math.InvalidStateException;

public class BinaryOperationExpression implements Expression {

    private final Expression op1;
    private final Operation op;
    private Expression op2;

    public BinaryOperationExpression(Expression op1, Operation op) {
        this.op1 = op1;
        this.op = op;
    }

    @Override
    public Expression add(ASTNode toAdd) {
        if (toAdd instanceof Operation) {
            if (op2 == null) {
                throw new InvalidStateException("Adding an operation to a BinaryOperationExpression is illegal if the second operand is still null");
            }
            Operation opToAdd = (Operation) toAdd;
            return new BinaryOperationExpression(this, opToAdd);
        } else {
            if (!(toAdd instanceof Expression)) {
                throw new InvalidStateException("Illegal class on second operand. " + toAdd.getClass().getName());
            }
            op2 = (Expression) toAdd;
            return this;
        }
    }

    @Override
    public long resolve() {
        return op.resolve(op1, op2);
    }

    @Override
    public void validate() {
        if (op2 == null) {
            throw new InvalidFormulaException("Missing second operand on " + this);
        }
        op1.validate();
        op2.validate();
    }

    @Override
    public String toString() {
        return "(" + op1.toString() + op + op2 + ")";
    }

}
