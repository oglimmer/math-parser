package de.oglimmer.math.fsm;

import de.oglimmer.math.fsm.state.EmptyState;
import de.oglimmer.math.fsm.state.State;
import de.oglimmer.math.token.Token;

public class FSM {

    private State state = new EmptyState();

    public Token transition(Action action) {
        state.validate(action);
        state = state.transition(action);
        return state.getToken();
    }

}
