package de.oglimmer.math;

import de.oglimmer.math.astnode.ASTNode;
import de.oglimmer.math.astnode.Expression;
import de.oglimmer.math.token.Token;

import java.util.List;


public class ASTBuilder {

    public Expression tokensToExpression(List<Token> tokens) {
        return tokens.stream().reduce(null,
                (result, token) -> accumulator(result, token),
                (result, element) -> result);
    }

    private Expression accumulator(Expression result, Token token) {
        ASTNode newASTNode = token.toASTNode();
        if (result == null) {
            if (!(newASTNode instanceof Expression)) {
                throw new RuntimeException("Root must be an expression");
            }
            return (Expression) newASTNode;
        }
        return result.add(newASTNode);
    }

}
