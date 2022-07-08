package de.oglimmer.math.fsm.state;

import de.oglimmer.math.token.Token;

public class OperationState extends EmptyOperatorParenthesisOpenState {

    private char operator;

    public OperationState(char operator) {
        this.operator = operator;
    }

    @Override
    public Token getToken() {
        return new Token(Character.toString(operator), Token.Type.OPERATOR);
    }

}
