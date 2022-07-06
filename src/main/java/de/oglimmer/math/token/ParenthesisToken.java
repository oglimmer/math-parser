package de.oglimmer.math.token;

import de.oglimmer.math.astnode.ASTNode;
import de.oglimmer.math.astnode.Parenthesis;

public class ParenthesisToken extends Token {

    private char c;

    public ParenthesisToken(String s) {
        this.c = s.charAt(0);
    }

    public ASTNode toASTNode() {
        switch (c) {
            case '(':
            case ')':
                return new Parenthesis();
            default:
                throw new RuntimeException();
        }
    }

    @Override
    public String toString() {
        return "ParenthesisToken{" +
                "content='" + c + '\'' +
                '}';
    }
}
