package de.oglimmer.math.tokenize.stateImpl;

import de.oglimmer.math.tokenize.Token;

public class ConstantCompletedState extends DigitOrCharacterReadingCompletedState {

    private final String constant;

    public ConstantCompletedState(String constant) {
        this.constant = constant;
    }

    @Override
    protected Token getToken() {
        return new Token(constant, Token.Type.CONSTANT);
    }

}
