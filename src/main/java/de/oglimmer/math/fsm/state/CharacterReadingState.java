package de.oglimmer.math.fsm.state;

import de.oglimmer.math.InvalidFormulaException;
import de.oglimmer.math.astnode.Parenthesis;
import de.oglimmer.math.fsm.Action;
import de.oglimmer.math.token.Token;

public class CharacterReadingState extends State {

    private StringBuilder buff;

    public CharacterReadingState(char initialCharacter) {
        buff = new StringBuilder();
        buff.append(initialCharacter);
    }

    @Override
    public void validate(Action action) {
        if (!isAlpha(action.getReadCharacter())) {
            throw new InvalidFormulaException("Unexpected character " + action.getReadCharacter() + " detected. Only alphanumerical characters allowed here.");
        }
    }

    @Override
    public State transition(Action action) {
        char readCharacter = action.getReadCharacter();
        buff.append(readCharacter);
        Character nextC = action.getNextCharacter();
        if (nextC == null || isOperator(nextC) || nextC == Parenthesis.CLOSE || (nextC == Parenthesis.OPEN && containsKeyword(buff.toString()))) {
            if (containsConstant(buff.toString())) {
                return new ConstantCompletedState(buff.toString());
            } else if (containsKeyword(buff.toString())) {
                return new PostfixOperationState(buff.toString());
            } else {
                return new VarCompletedState(buff.toString());
            }
        }
        return this;
    }

    @Override
    public Token getToken() {
        return null;
    }

}
