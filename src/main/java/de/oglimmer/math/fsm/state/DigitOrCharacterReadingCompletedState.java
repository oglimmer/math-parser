package de.oglimmer.math.fsm.state;

import de.oglimmer.math.InvalidFormulaException;
import de.oglimmer.math.astnode.Parenthesis;
import de.oglimmer.math.fsm.Action;

public abstract class DigitOrCharacterReadingCompletedState extends State {

    @Override
    public void validate(Action action) {
        if (!isOperator(action.getReadCharacter()) && action.getReadCharacter() != Parenthesis.CLOSE) {
            throw new InvalidFormulaException("Unexpected character " + action.getReadCharacter() + " detected. Only operator or closing parenthesis allowed here.");
        }
    }

    @Override
    public State transition(Action action) {
        char readCharacter = action.getReadCharacter();
        if (isOperator(readCharacter)) {
            return new OperationState(readCharacter);
        } else if (readCharacter == Parenthesis.CLOSE) {
            return new ParenthesisClosedState(readCharacter);
        }
        return null;
    }
}
