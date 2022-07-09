package de.oglimmer.math.tokenize.stateImpl;

import de.oglimmer.fsm.TransitionResult;
import de.oglimmer.math.InvalidFormulaException;
import de.oglimmer.math.astnode.Parenthesis;
import de.oglimmer.math.tokenize.ReadCharacterEvent;
import de.oglimmer.math.tokenize.Token;

public abstract class DigitOrCharacterReadingCompletedState extends ReadCharToTokenState {

    @Override
    public void validate(ReadCharacterEvent inputEvent) {
        if (!isOperator(inputEvent.getReadCharacter()) && inputEvent.getReadCharacter() != Parenthesis.CLOSE) {
            throw new InvalidFormulaException("Unexpected character " + inputEvent.getReadCharacter() + " detected. Only operator or closing parenthesis allowed here.");
        }
    }

    @Override
    public TransitionResult<Token> transition(ReadCharacterEvent inputEvent) {
        char readCharacter = inputEvent.getReadCharacter();
        if (isOperator(readCharacter)) {
            return new OperationState(readCharacter).getTransitionResult();
        } else if (readCharacter == Parenthesis.CLOSE) {
            return new ParenthesisClosedState(readCharacter).getTransitionResult();
        }
        return null;
    }
}
