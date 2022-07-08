package de.oglimmer.math.fsm.state;

import de.oglimmer.math.token.Token;

public class PostfixOperationState extends EmptyOperatorParenthesisOpenState {

    private String operatorName;

    public PostfixOperationState(String operatorName) {
        this.operatorName = operatorName;
    }

    @Override
    public Token getToken() {
        return new Token(operatorName, Token.Type.POSTFIX_OPERATOR);
    }

}
