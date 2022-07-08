package de.oglimmer.math.fsm.state;

import de.oglimmer.math.astnode.Constant;
import de.oglimmer.math.astnode.Operation;
import de.oglimmer.math.astnode.PostfixOperation;
import de.oglimmer.math.fsm.Action;
import de.oglimmer.math.token.Token;

public abstract class State {

    abstract public void validate(Action action);

    abstract public State transition(Action action);

    abstract public Token getToken();

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

}


