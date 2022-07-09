package de.oglimmer.math.tokenize.stateImpl;

import de.oglimmer.fsm.Transition;
import de.oglimmer.math.InvalidFormulaException;
import de.oglimmer.math.astnode.Parenthesis;
import de.oglimmer.math.tokenize.ReadCharacterEvent;
import de.oglimmer.math.tokenize.Token;

public class DigitReadingState extends ReadCharToTokenState {

    private final StringBuilder buff;

    public DigitReadingState(char initialCharacter) {
        buff = new StringBuilder();
        buff.append(initialCharacter);
    }

    @Override
    public void validate(ReadCharacterEvent inputEvent) {
        if (!isDigit(inputEvent.getReadCharacter())) {
            throw new InvalidFormulaException("Unexpected character " + inputEvent.getReadCharacter() + " detected. Only digits allowed here.");
        }
    }

    @Override
    public Transition<Token> transition(ReadCharacterEvent inputEvent) {
        char readCharacter = inputEvent.getReadCharacter();
        Character nextC = inputEvent.getNextCharacter();
        buff.append(readCharacter);
        if (nextC == null || isOperator(nextC) || nextC == Parenthesis.CLOSE) {
            return new DigitCompletedState(buff.toString()).getTransitionResult();
        }
        return this.getTransitionResult();
    }

    @Override
    protected Token getToken() {
        return null;
    }

}
