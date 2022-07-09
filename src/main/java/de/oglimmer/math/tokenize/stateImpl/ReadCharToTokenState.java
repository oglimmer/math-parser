package de.oglimmer.math.tokenize.stateImpl;

import de.oglimmer.fsm.State;
import de.oglimmer.fsm.Transition;
import de.oglimmer.math.astnode.Constant;
import de.oglimmer.math.astnode.Operation;
import de.oglimmer.math.astnode.PostfixOperation;
import de.oglimmer.math.tokenize.ReadCharacterEvent;
import de.oglimmer.math.tokenize.Token;

public abstract class ReadCharToTokenState implements State<ReadCharacterEvent, Token> {

    protected static boolean isOperator(char c) {
        return Operation.match(c);
    }

    protected static boolean isDigit(char c) {
        return Character.isDigit(c) || c == '.';
    }

    protected static boolean isAlpha(char c) {
        return Character.isAlphabetic(c);
    }

    protected static boolean isAlgebraicSign(char c) {
        return c == '+' || c == '-';
    }

    protected static boolean containsKeyword(String string) {
        return Constant.match(string) || PostfixOperation.match(string);
    }

    protected static boolean containsConstant(String string) {
        return Constant.match(string);
    }

    protected abstract Token getToken();

    protected Transition<Token> getTransitionResult() {
        return new Transition<>(this, getToken());
    }


}


