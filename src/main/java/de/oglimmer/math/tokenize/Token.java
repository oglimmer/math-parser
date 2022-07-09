package de.oglimmer.math.tokenize;

import de.oglimmer.fsm.Action;
import de.oglimmer.math.astnode.Number;
import de.oglimmer.math.astnode.*;

public class Token implements Action {

    private final String data;
    private final Type type;

    public Token(String data, Type type) {
        this.data = data;
        this.type = type;
    }

    public ASTNode toASTNode() {
        switch (type) {
            case NUMBER:
                return new Number(Long.parseLong(data));
            case OPERATOR:
                return new Operation(data);
        }
        return null;
    }

    @Override
    public String toString() {
        return "Token{" +
                "type='" + type + '\'' +
                "data='" + data + '\'' +
                '}';
    }

    public enum Type {
        NUMBER, OPERATOR
    }

}

