package de.oglimmer.math.tokenize.stateImpl;

import de.oglimmer.math.tokenize.Token;

public class VarCompletedState extends DigitOrCharacterReadingCompletedState {

    private final String varName;

    public VarCompletedState(String varName) {
        this.varName = varName;
    }

    @Override
    protected Token getToken() {
        return new Token(varName, Token.Type.VARIABLE);
    }

}
