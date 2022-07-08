package de.oglimmer.math.token;

import de.oglimmer.math.astnode.Number;
import de.oglimmer.math.astnode.*;

public class Token {

    private String data;
    private Type type;
    public Token(String data, Type type) {
        this.data = data;
        this.type = type;
    }

    public ASTNode toASTNode() {
        switch (type) {
            case CONSTANT:
                return new Constant(data);
            case NUMBER:
                return new Number(Double.parseDouble(data));
            case OPERATOR:
                return new Operation(data);
            case PARENTHESIS:
                return new Parenthesis(data.charAt(0));
            case POSTFIX_OPERATOR:
                return new PostfixOperation(data);
            case VARIABLE:
                return new Variable(data);
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
        CONSTANT, NUMBER, OPERATOR, PARENTHESIS, POSTFIX_OPERATOR, VARIABLE;
    }

}

