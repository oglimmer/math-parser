package de.oglimmer.math.tokenize.stateImpl;

import de.oglimmer.math.tokenize.Token;

public class EmptyState extends EmptyOperatorParenthesisOpenState {

    @Override
    protected Token getToken() {
        return null;
    }

}
