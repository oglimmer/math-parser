package de.oglimmer.math.tokenize.stateImpl;

import de.oglimmer.fsm.Transition;
import de.oglimmer.math.InvalidStateException;
import de.oglimmer.math.tokenize.ReadCharacterEvent;
import de.oglimmer.math.tokenize.Token;

public abstract class EmptyOperatorState extends ReadCharToTokenState {
    @Override
    public void validate(ReadCharacterEvent inputEvent) {
        char readCharacter = inputEvent.getReadCharacter();
        if (isOperator(readCharacter)) {
            throw new InvalidStateException("Illegal state");
        }
        if (!isDigit(readCharacter)) {
            throw new InvalidStateException("Illegal char read " + readCharacter);
        }
    }

    @Override
    public Transition<Token> transition(ReadCharacterEvent inputEvent) {
        char readCharacter = inputEvent.getReadCharacter();
        Character nextC = inputEvent.getNextCharacter();
        if (nextC != null && isDigit(nextC)) {
            return new DigitReadingState(readCharacter).getTransitionResult();
        }
        return new DigitCompletedState(Character.toString(readCharacter)).getTransitionResult();
    }
}
