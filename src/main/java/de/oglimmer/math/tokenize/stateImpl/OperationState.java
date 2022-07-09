package de.oglimmer.math.tokenize.stateImpl;

import de.oglimmer.math.tokenize.Token;

public class OperationState extends EmptyOperatorState {

    private final char operator;

    public OperationState(char operator) {
        this.operator = operator;
    }

    @Override
    protected Token getToken() {
        return new Token(Character.toString(operator), Token.Type.OPERATOR);
    }

}
