package de.oglimmer.fsm;

public interface State<E extends Event, A extends Action> {

    void validate(E inputEvent);

    TransitionResult<A> transition(E inputEvent);

}
