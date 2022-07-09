package de.oglimmer.math.tokenize.stateImpl;

import de.oglimmer.math.tokenize.Token;

public class ParenthesisOpenState extends EmptyOperatorParenthesisOpenState {

    private final char character;

    public ParenthesisOpenState(char character) {
        this.character = character;
    }

    @Override
    protected Token getToken() {
        return new Token(Character.toString(character), Token.Type.PARENTHESIS);
    }

}
