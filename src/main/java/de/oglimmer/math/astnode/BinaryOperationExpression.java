package de.oglimmer.math.astnode;

import de.oglimmer.math.InvalidFormulaException;

import java.util.Map;

public class BinaryOperationExpression implements Expression {

    private final Expression op1;
    private final Operation op;
    private Expression op2;

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
    public double resolve(Map<String, Double> vars) {
        return op.resolve(vars, op1, op2);
    }

    @Override
    public boolean openForInput() {
        return op2 == null || op2.openForInput();
    }

    @Override
    public Expression simplify() {
        return this;
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
        return Parenthesis.OPEN + op1.toString() + op + op2 + Parenthesis.CLOSE;
    }

}
