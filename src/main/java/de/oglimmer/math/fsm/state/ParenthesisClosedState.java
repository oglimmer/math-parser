package de.oglimmer.math.fsm.state;

import de.oglimmer.math.token.Token;

public class ParenthesisClosedState extends DigitOrCharacterReadingCompletedState {

    private char character;

    public ParenthesisClosedState(char character) {
        this.character = character;
    }

    @Override
    public Token getToken() {
        return new Token(Character.toString(character), Token.Type.PARENTHESIS);
    }

}
