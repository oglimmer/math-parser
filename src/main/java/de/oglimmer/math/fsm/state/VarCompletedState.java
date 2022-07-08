package de.oglimmer.math.fsm.state;

import de.oglimmer.math.token.Token;

public class VarCompletedState extends DigitOrCharacterReadingCompletedState {

    private String varName;

    public VarCompletedState(String varName) {
        this.varName = varName;
    }

    @Override
    public Token getToken() {
        return new Token(varName, Token.Type.VARIABLE);
    }

}
