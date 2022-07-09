package de.oglimmer.fsm;


public class FSM<E extends Event, A extends Action> {

    private State<E, A> state;

    public FSM(State<E, A> initialState) {
        this.state = initialState;
    }

    public A transition(E inputEvent) {
        state.validate(inputEvent);
        TransitionResult<A> transitionResult = state.transition(inputEvent);
        state = transitionResult.getState();
        return transitionResult.getAction();
    }

}
