package de.oglimmer.math.fsm.state;

import de.oglimmer.math.token.Token;

public class DigitCompletedState extends DigitOrCharacterReadingCompletedState {

    private String number;

    public DigitCompletedState(String number) {
        this.number = number;
    }

    @Override
    public Token getToken() {
        return new Token(number, Token.Type.NUMBER);
    }

}
