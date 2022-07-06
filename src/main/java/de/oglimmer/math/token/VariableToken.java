package de.oglimmer.math.token;

import de.oglimmer.math.astnode.ASTNode;
import de.oglimmer.math.astnode.Number;
import de.oglimmer.math.astnode.Variable;

public class VariableToken extends Token {

    private String name;

    public VariableToken(String s) {
        name = s;
    }

    public ASTNode toASTNode() {
        return new Variable(name);
    }

    @Override
    public String toString() {
        return "VariableToken{" +
                "name='" + name + '\'' +
                '}';
    }
}
