package de.oglimmer.math.tokenize.stateImpl;

import de.oglimmer.math.tokenize.Token;

public class DigitCompletedState extends DigitReadingCompletedState {

    private final String number;

    public DigitCompletedState(String number) {
        this.number = number;
    }

    @Override
    protected Token getToken() {
        return new Token(number, Token.Type.NUMBER);
    }

}
