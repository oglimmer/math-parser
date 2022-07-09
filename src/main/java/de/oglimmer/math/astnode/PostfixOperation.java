package de.oglimmer.math.astnode;

import de.oglimmer.math.InvalidStateException;

import java.util.Arrays;
import java.util.Map;

public class PostfixOperation implements Expression {

    protected Expression nestedExp;
    private final Type type;

    public PostfixOperation(String name) {
        this.type = Type.valueOf(name.toUpperCase());
    }

    public static boolean match(String string) {
        return Arrays.stream(Type.values()).anyMatch(e -> e.toString().equals(string.toUpperCase()));
    }

    @Override
    public double resolve(Map<String, Double> vars) {
        return type.exec(nestedExp.resolve(vars));
    }

    @Override
    public Expression add(ASTNode toAdd) {
        Expression retExp = this;
        if (nestedExp == null) {
            nestedExp = (Expression) toAdd;
        } else if (nestedExp.openForInput()) {
            nestedExp = nestedExp.add(toAdd);
        } else {
            retExp = new BinaryOperationExpression(this, (Operation) toAdd);
        }
        return retExp;
    }

    @Override
    public boolean openForInput() {
        return nestedExp == null || nestedExp.openForInput();
    }

    @Override
    public Expression simplify() {
        return this;
    }

    @Override
    public void validate() {
        nestedExp.validate();
    }

    @Override
    public String toString() {
        return nestedExp != null ? type + "(" + nestedExp + ')' : type + "(?)";
    }

    enum Type {
        SIN, COS, TAN, ASIN, ACOS, ATAN, SQRT, LOG, LOGTEN;

        public double exec(double resolve) {
            switch (this) {
                case SIN:
                    return Math.sin(resolve);
                case COS:
                    return Math.cos(resolve);
                case TAN:
                    return Math.tan(resolve);
                case ASIN:
                    return Math.asin(resolve);
                case ACOS:
                    return Math.acos(resolve);
                case ATAN:
                    return Math.atan(resolve);
                case SQRT:
                    return Math.sqrt(resolve);
                case LOG:
                    return Math.log(resolve);
                case LOGTEN:
                    return Math.log10(resolve);
                default:
                    throw new InvalidStateException("Unknown enum type " + this);
            }
        }
    }

}
