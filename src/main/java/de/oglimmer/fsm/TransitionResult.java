package de.oglimmer.fsm;

public class TransitionResult<A extends Action> {

    private final State state;

    private final A action;

    public TransitionResult(State state, A action) {
        this.state = state;
        this.action = action;
    }

    public State getState() {
        return state;
    }

    public A getAction() {
        return action;
    }
}
