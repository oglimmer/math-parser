package de.oglimmer.math.tokenize.stateImpl;

import de.oglimmer.math.tokenize.Token;

public class PostfixOperationState extends EmptyOperatorParenthesisOpenState {

    private final String operatorName;

    public PostfixOperationState(String operatorName) {
        this.operatorName = operatorName;
    }

    @Override
    protected Token getToken() {
        return new Token(operatorName, Token.Type.POSTFIX_OPERATOR);
    }

}
