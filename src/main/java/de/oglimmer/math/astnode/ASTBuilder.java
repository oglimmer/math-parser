package de.oglimmer.math.astnode;

import de.oglimmer.math.InvalidStateException;
import de.oglimmer.math.tokenize.Token;

import java.util.List;


public class ASTBuilder {

    public Expression tokensToExpression(List<Token> tokens) {
        Expression resultExp = tokens.stream().reduce(null,
                (result, token) -> accumulator(result, token),
                (result, element) -> result);
        resultExp.validate();
        return resultExp.simplify();
    }

    private Expression accumulator(Expression result, Token token) {
        ASTNode newASTNode = token.toASTNode();
        if (result == null) {
            if (!(newASTNode instanceof Expression)) {
                throw new InvalidStateException("Root must be an expression");
            }
            return (Expression) newASTNode;
        }
        return result.add(newASTNode);
    }

}
