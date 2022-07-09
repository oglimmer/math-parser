package de.oglimmer.math.tokenize.stateImpl;

import de.oglimmer.fsm.Transition;
import de.oglimmer.math.InvalidFormulaException;
import de.oglimmer.math.tokenize.ReadCharacterEvent;
import de.oglimmer.math.tokenize.Token;

public class DigitCompletedState extends ReadCharToTokenState {

    private final String number;

    public DigitCompletedState(String number) {
        this.number = number;
    }

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

    @Override
    protected Token getToken() {
        return new Token(number, Token.Type.NUMBER);
    }

}
