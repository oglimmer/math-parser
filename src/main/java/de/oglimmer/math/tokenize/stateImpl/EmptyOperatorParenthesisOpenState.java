package de.oglimmer.math.tokenize.stateImpl;

import de.oglimmer.fsm.TransitionResult;
import de.oglimmer.math.InvalidStateException;
import de.oglimmer.math.astnode.Parenthesis;
import de.oglimmer.math.tokenize.ReadCharacterEvent;
import de.oglimmer.math.tokenize.Token;

public abstract class EmptyOperatorParenthesisOpenState extends ReadCharToTokenState {
    @Override
    public void validate(ReadCharacterEvent inputEvent) {
        char readCharacter = inputEvent.getReadCharacter();
        if (isOperator(readCharacter) && !isAlgebraicSign(readCharacter)) {
            throw new InvalidStateException("Illegal state");
        }
        if (readCharacter == Parenthesis.CLOSE) {
            throw new InvalidStateException("Illegal state");
        }
        if (!isDigit(readCharacter) && !isAlgebraicSign(readCharacter) && readCharacter != Parenthesis.OPEN && !isAlpha(readCharacter)) {
            throw new InvalidStateException("Illegal char read " + readCharacter);
        }
    }

    @Override
    public TransitionResult<Token> transition(ReadCharacterEvent inputEvent) {
        char readCharacter = inputEvent.getReadCharacter();
        Character nextC = inputEvent.getNextCharacter();
        if (readCharacter == Parenthesis.OPEN) {
            return new ParenthesisOpenState(readCharacter).getTransitionResult();
        } else if (nextC != null && isDigit(nextC)) {
            return new DigitReadingState(readCharacter).getTransitionResult();
        } else if (nextC != null && isAlpha(nextC)) {
            return new CharacterReadingState(readCharacter).getTransitionResult();
        } else if (nextC == null || isOperator(nextC) || nextC == Parenthesis.CLOSE) {
            if (isDigit(readCharacter)) {
                return new DigitCompletedState(Character.toString(readCharacter)).getTransitionResult();
            } else if (isAlpha(readCharacter)) {
                if (containsConstant(Character.toString(readCharacter))) {
                    return new ConstantCompletedState(Character.toString(readCharacter)).getTransitionResult();
                } else if (containsKeyword(Character.toString(readCharacter))) {
                    return new PostfixOperationState(Character.toString(readCharacter)).getTransitionResult();
                } else {
                    return new VarCompletedState(Character.toString(readCharacter)).getTransitionResult();
                }
            }
        }
        return getTransitionResult();
    }
}
