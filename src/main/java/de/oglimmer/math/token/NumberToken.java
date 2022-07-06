package de.oglimmer.math.token;

import de.oglimmer.math.astnode.ASTNode;
import de.oglimmer.math.astnode.Number;

public class NumberToken extends Token {

    private double val;

    public NumberToken(String s) {
        val = Double.parseDouble(s);
    }

    public ASTNode toASTNode() {
        return new Number(val);
    }

    @Override
    public String toString() {
        return "NumberToken{" +
                "val='" + val + '\'' +
                '}';
    }
}
