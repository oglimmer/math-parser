package de.oglimmer.math.token;

import de.oglimmer.math.astnode.*;

public class OperatorToken extends Token {

    private char c;

    public OperatorToken(String s) {
        this.c = s.charAt(0);
    }

    public ASTNode toASTNode() {
        switch (c) {
            case '+':
                return new PlusOperation();
            case '-':
                return new MinusOperation();
            case '*':
                return new MultiplicationOperation();
            case '/':
                return new DivisionOperation();
            default:
                throw new RuntimeException();
        }
    }

    @Override
    public String toString() {
        return "OperatorToken{" +
                "content='" + c + '\'' +
                '}';
    }
}
