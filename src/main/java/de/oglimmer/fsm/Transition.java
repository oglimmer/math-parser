package de.oglimmer.fsm;

public class Transition<A extends Action> {

    private final State targetState;

    private final A action;

    public Transition(State targetState, A action) {
        this.targetState = targetState;
        this.action = action;
    }

    public State getTargetState() {
        return targetState;
    }

    public A getAction() {
        return action;
    }
}
