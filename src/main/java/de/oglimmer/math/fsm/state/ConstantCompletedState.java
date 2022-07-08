package de.oglimmer.math.fsm.state;

import de.oglimmer.math.token.Token;

public class ConstantCompletedState extends DigitOrCharacterReadingCompletedState {

    private String constant;

    public ConstantCompletedState(String constant) {
        this.constant = constant;
    }

    @Override
    public Token getToken() {
        return new Token(constant, Token.Type.CONSTANT);
    }

}
