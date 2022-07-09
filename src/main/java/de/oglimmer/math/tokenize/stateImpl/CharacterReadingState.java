package de.oglimmer.math.tokenize.stateImpl;

import de.oglimmer.fsm.TransitionResult;
import de.oglimmer.math.InvalidFormulaException;
import de.oglimmer.math.astnode.Parenthesis;
import de.oglimmer.math.tokenize.ReadCharacterEvent;
import de.oglimmer.math.tokenize.Token;

public class CharacterReadingState extends ReadCharToTokenState {

    private final StringBuilder buff;

    public CharacterReadingState(char initialCharacter) {
        buff = new StringBuilder();
        buff.append(initialCharacter);
    }

    @Override
    public void validate(ReadCharacterEvent inputEvent) {
        if (!isAlpha(inputEvent.getReadCharacter())) {
            throw new InvalidFormulaException("Unexpected character " + inputEvent.getReadCharacter() + " detected. Only alphanumerical characters allowed here.");
        }
    }

    @Override
    public TransitionResult<Token> transition(ReadCharacterEvent inputEvent) {
        char readCharacter = inputEvent.getReadCharacter();
        buff.append(readCharacter);
        Character nextC = inputEvent.getNextCharacter();
        if (nextC == null || isOperator(nextC) || nextC == Parenthesis.CLOSE || (nextC == Parenthesis.OPEN && containsKeyword(buff.toString()))) {
            if (containsConstant(buff.toString())) {
                return new ConstantCompletedState(buff.toString()).getTransitionResult();
            } else if (containsKeyword(buff.toString())) {
                return new PostfixOperationState(buff.toString()).getTransitionResult();
            } else {
                return new VarCompletedState(buff.toString()).getTransitionResult();
            }
        }
        return this.getTransitionResult();
    }

    @Override
    protected Token getToken() {
        return null;
    }

}
