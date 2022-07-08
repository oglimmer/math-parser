package de.oglimmer.math.fsm.state;

import de.oglimmer.math.token.Token;

public class ParenthesisOpenState extends EmptyOperatorParenthesisOpenState {

    private char character;

    public ParenthesisOpenState(char character) {
        this.character = character;
    }

    @Override
    public Token getToken() {
        return new Token(Character.toString(character), Token.Type.PARENTHESIS);
    }

}
