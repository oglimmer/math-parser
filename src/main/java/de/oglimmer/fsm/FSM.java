package de.oglimmer.fsm;


public class FSM<E extends Event, A extends Action> {

    private State<E, A> state;

    public FSM(State<E, A> initialState) {
        this.state = initialState;
    }

    public A transition(E inputEvent) {
        state.validate(inputEvent);
        Transition<A> transition = state.transition(inputEvent);
        state = transition.getTargetState();
        return transition.getAction();
    }

}
