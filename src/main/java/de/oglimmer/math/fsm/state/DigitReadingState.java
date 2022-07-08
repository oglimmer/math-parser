package de.oglimmer.math.fsm.state;

import de.oglimmer.math.InvalidFormulaException;
import de.oglimmer.math.astnode.Parenthesis;
import de.oglimmer.math.fsm.Action;
import de.oglimmer.math.token.Token;

public class DigitReadingState extends State {

    private StringBuilder buff;

    public DigitReadingState(char initialCharacter) {
        buff = new StringBuilder();
        buff.append(initialCharacter);
    }

    @Override
    public void validate(Action action) {
        if (!isDigit(action.getReadCharacter())) {
            throw new InvalidFormulaException("Unexpected character " + action.getReadCharacter() + " detected. Only digits allowed here.");
        }
    }

    @Override
    public State transition(Action action) {
        char readCharacter = action.getReadCharacter();
        Character nextC = action.getNextCharacter();
        buff.append(readCharacter);
        if (nextC == null || isOperator(nextC) || nextC == Parenthesis.CLOSE) {
            return new DigitCompletedState(buff.toString());
        }
        return this;
    }

    @Override
    public Token getToken() {
        return null;
    }

}
