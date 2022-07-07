package de.oglimmer.math.astnode;

import java.util.Arrays;
import java.util.Map;

public class Constant implements Expression {

    private final SupportedConstant supportedConstant;

    public Constant(String name) {
        this.supportedConstant = SupportedConstant.valueOf(name.toUpperCase());
    }

    public static boolean match(String string) {
        return Arrays.stream(SupportedConstant.values()).anyMatch(e -> e.toString().equals(string.toUpperCase()));
    }

    @Override
    public Expression add(ASTNode toAdd) {
        if (!(toAdd instanceof Operation)) {
            throw new RuntimeException("illegal class " + toAdd.getClass().getName() + " on supportedConstant " + supportedConstant);
        }
        return new BinaryOperationExpression(this, (Operation) toAdd);
    }

    @Override
    public double resolve(Map<String, Double> vars) {
        return supportedConstant.exec();
    }

    @Override
    public boolean openForInput() {
        return false;
    }

    @Override
    public Expression simplify() {
        return this;
    }

    @Override
    public String toString() {
        return supportedConstant.toString();
    }

    enum SupportedConstant {
        PI, E;

        public double exec() {
            switch (this) {
                case PI:
                    return Math.PI;
                case E:
                    return Math.E;
                default:
                    throw new RuntimeException();
            }
        }
    }
}
