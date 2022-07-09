package de.oglimmer.math.tokenize.stateImpl;

import de.oglimmer.fsm.Transition;
import de.oglimmer.math.InvalidFormulaException;
import de.oglimmer.math.tokenize.ReadCharacterEvent;
import de.oglimmer.math.tokenize.Token;

public abstract class DigitReadingCompletedState extends ReadCharToTokenState {

    @Override
    public void validate(ReadCharacterEvent inputEvent) {
        if (!isOperator(inputEvent.getReadCharacter())) {
            throw new InvalidFormulaException("Unexpected character " + inputEvent.getReadCharacter() + " detected. Only operator or closing parenthesis allowed here.");
        }
    }

    @Override
    public Transition<Token> transition(ReadCharacterEvent inputEvent) {
        char readCharacter = inputEvent.getReadCharacter();
        if (isOperator(readCharacter)) {
            return new OperationState(readCharacter).getTransitionResult();
        }
        return null;
    }
}
