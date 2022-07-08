package de.oglimmer.math.fsm.state;

import de.oglimmer.math.token.Token;

public class EmptyState extends EmptyOperatorParenthesisOpenState {

    @Override
    public Token getToken() {
        return null;
    }

}
