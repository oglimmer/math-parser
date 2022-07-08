package de.oglimmer.math.fsm.state;

import de.oglimmer.math.astnode.Parenthesis;
import de.oglimmer.math.fsm.Action;

public abstract class EmptyOperatorParenthesisOpenState extends State {
    @Override
    public void validate(Action action) {
        char readCharacter = action.getReadCharacter();
        if (isOperator(readCharacter) && !isAlgebraicSign(readCharacter)) {
            throw new RuntimeException("Illegal state");
        }
        if (readCharacter == Parenthesis.CLOSE) {
            throw new RuntimeException("Illegal state");
        }
        if (!isDigit(readCharacter) && !isAlgebraicSign(readCharacter) && readCharacter != Parenthesis.OPEN && !isAlpha(readCharacter)) {
            throw new RuntimeException("Illegal char read " + readCharacter);
        }
    }

    @Override
    public State transition(Action action) {
        char readCharacter = action.getReadCharacter();
        Character nextC = action.getNextCharacter();
        if (readCharacter == Parenthesis.OPEN) {
            return new ParenthesisOpenState(readCharacter);
        } else if (nextC != null && isDigit(nextC)) {
            return new DigitReadingState(readCharacter);
        } else if (nextC != null && isAlpha(nextC)) {
            return new CharacterReadingState(readCharacter);
        } else if (nextC == null || isOperator(nextC) || nextC == Parenthesis.CLOSE) {
            if (isDigit(readCharacter)) {
                return new DigitCompletedState(Character.toString(readCharacter));
            } else if (isAlpha(readCharacter)) {
                if (containsConstant(Character.toString(readCharacter))) {
                    return new ConstantCompletedState(Character.toString(readCharacter));
                } else if (containsKeyword(Character.toString(readCharacter))) {
                    return new PostfixOperationState(Character.toString(readCharacter));
                } else {
                    return new VarCompletedState(Character.toString(readCharacter));
                }
            }
        }
        return this;
    }
}
