package de.oglimmer.math.astnode;

import de.oglimmer.math.InvalidStateException;

import java.util.Arrays;

public class Operation implements ASTNode {

    private final OpImpl opImpl;

    public Operation(String symbol) {
        this.opImpl = OpImpl.fromString(symbol);
    }

    public static boolean match(char c) {
        return Arrays.stream(OpImpl.values()).anyMatch(e -> e.symbol.equals(Character.toString(c)));
    }

    public long resolve(Expression... expressions) {
        return opImpl.resolve(expressions);
    }

    @Override
    public String toString() {
        return opImpl.toString();
    }

    enum OpImpl {
        PLUS("+"),
        MINUS("-");

        private final String symbol;

        OpImpl(String symbol) {
            this.symbol = symbol;
        }

        static OpImpl fromString(String s) {
            return Arrays.stream(values()).filter(e -> e.toString().equals(s)).findFirst().orElseThrow();
        }

        long resolve(Expression... expressions) {
            switch (this) {
                case PLUS:
                    return expressions[0].resolve() + expressions[1].resolve();
                case MINUS:
                    return expressions[0].resolve() - expressions[1].resolve();
                default:
                    throw new InvalidStateException("Unknown enum type " + this);
            }
        }

        public String toString() {
            return symbol;
        }
    }
}

