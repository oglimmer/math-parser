package de.oglimmer.math.astnode;

import java.util.Arrays;
import java.util.Map;

public class Operation implements ASTNode {

    private final OpImpl opImpl;

    public Operation(String symbol) {
        this.opImpl = OpImpl.fromString(symbol);
    }

    public static boolean match(char c) {
        return Arrays.stream(OpImpl.values()).anyMatch(e -> e.symbol.equals(Character.toString(c)));
    }

    public int getPrecedence() {
        return opImpl.getPrecedence();
    }

    public double resolve(Map<String, Double> vars, Expression... expressions) {
        return opImpl.resolve(vars, expressions);
    }

    @Override
    public boolean openForInput() {
        return false;
    }

    @Override
    public String toString() {
        return opImpl.toString();
    }

    enum OpImpl {
        PLUS("+", 1),
        MINUS("-", 1),
        MULTI("*", 2),
        DIV("/", 2),
        POWER("^", 3);

        private final String symbol;
        private final int precedence;

        OpImpl(String symbol, int precedence) {
            this.symbol = symbol;
            this.precedence = precedence;
        }

        static OpImpl fromString(String s) {
            return Arrays.stream(values()).filter(e -> e.toString().equals(s)).findFirst().orElseThrow();
        }

        int getPrecedence() {
            return precedence;
        }

        double resolve(Map<String, Double> vars, Expression... expressions) {
            switch (this) {
                case PLUS:
                    return expressions[0].resolve(vars) + expressions[1].resolve(vars);
                case MINUS:
                    return expressions[0].resolve(vars) - expressions[1].resolve(vars);
                case MULTI:
                    return expressions[0].resolve(vars) * expressions[1].resolve(vars);
                case DIV:
                    return expressions[0].resolve(vars) / expressions[1].resolve(vars);
                case POWER:
                    return Math.pow(expressions[0].resolve(vars), expressions[1].resolve(vars));
                default:
                    throw new RuntimeException();
            }
        }

        public String toString() {
            return symbol;
        }
    }
}

