package de.oglimmer.math.token;

import de.oglimmer.math.astnode.Constant;
import de.oglimmer.math.astnode.Operation;
import de.oglimmer.math.astnode.Parenthesis;
import de.oglimmer.math.astnode.PostfixOperation;

import java.util.ArrayList;
import java.util.List;

public class LexicalAnalyzer {

    private StringBuilder buff;
    private State state = State.EMPTY;

    public List<Token> parseToTokens(String input) {
        List<Token> nodes = new ArrayList<>();
        for (int pos = 0; pos < input.length(); pos++) {
            char currentC = input.charAt(pos);
            Character nextC = pos < input.length() - 1 ? input.charAt(pos + 1) : null;
            read(currentC, nextC);
            if (tokenCompleted()) {
                Token token = getToken();
                nodes.add(token);
            }
        }
        return nodes;
    }

    private Token getToken() {
        Token.Type type;
        switch (state) {
            case OPERATION:
                type = Token.Type.OPERATOR;
                break;
            case VAR_COMPLETED:
                type = Token.Type.VARIABLE;
                break;
            case DIGIT_COMPLETED:
                type = Token.Type.NUMBER;
                break;
            case PARENTHESIS_OPEN:
            case PARENTHESIS_CLOSED:
                type = Token.Type.PARENTHESIS;
                break;
            case POSTFIX_OPERATION:
                type = Token.Type.POSTFIX_OPERATOR;
                break;
            case CONSTANT_COMPLETED:
                type = Token.Type.CONSTANT;
                break;
            default:
                throw new RuntimeException("Unsupported state " + state);
        }
        return new Token(buff.toString(), type);
    }

    private void read(char readCharacter, Character nextC) {
        switch (state) {
            case EMPTY:
            case PARENTHESIS_OPEN:
            case OPERATION:
            case POSTFIX_OPERATION:
                inStateEmptyOrOperator(readCharacter, nextC);
                break;
            case CHARACTER_READING:
                inStateCharacterReadingInProgress(readCharacter, nextC);
                break;
            case DIGIT_READING:
                inStateDigitReadingInProgress(readCharacter, nextC);
                break;
            case VAR_COMPLETED:
            case CONSTANT_COMPLETED:
            case DIGIT_COMPLETED:
            case PARENTHESIS_CLOSED:
                inStateDigitOrCharacterReadingCompleted(readCharacter);
                break;
        }
    }

    private void inStateDigitOrCharacterReadingCompleted(char readCharacter) {
        if (isOperator(readCharacter)) {
            state = State.OPERATION;
        } else if (readCharacter == Parenthesis.CLOSE) {
            state = State.PARENTHESIS_CLOSED;
        } else {
            throw new RuntimeException("Illegal state. State " + state);
        }
        buff = new StringBuilder(Character.toString(readCharacter));
    }

    private void inStateDigitReadingInProgress(char readCharacter, Character nextC) {
        if (isOperator(readCharacter)) {
            throw new RuntimeException("Illegal state");
        }
        if (readCharacter == Parenthesis.OPEN) {
            throw new RuntimeException("Illegal state");
        }
        if (isDigit(readCharacter)) {
            buff.append(readCharacter);
        }
        if (nextC == null || isOperator(nextC) || nextC == Parenthesis.CLOSE) {
            state = State.DIGIT_COMPLETED;
        }
    }

    private void inStateCharacterReadingInProgress(char readCharacter, Character nextC) {
        if (isOperator(readCharacter)) {
            throw new RuntimeException("Illegal state");
        }
        if (readCharacter == Parenthesis.OPEN) {
            throw new RuntimeException("Illegal state. State " + state);
        }
        if (isAlpha(readCharacter)) {
            buff.append(readCharacter);
        }
        if (nextC == null || isOperator(nextC) || nextC == Parenthesis.CLOSE || (nextC == Parenthesis.OPEN && buffContainsKeyword())) {
            if (buffContainsConstant()) {
                state = State.CONSTANT_COMPLETED;
            } else if (buffContainsKeyword()) {
                state = State.POSTFIX_OPERATION;
            } else {
                state = State.VAR_COMPLETED;
            }
        }
    }

    private boolean buffContainsKeyword() {
        String buffStr = buff.toString();
        return Constant.match(buffStr) || PostfixOperation.match(buffStr);
    }

    private boolean buffContainsConstant() {
        return Constant.match(buff.toString());
    }

    private void inStateEmptyOrOperator(char readCharacter, Character nextC) {
        if (isOperator(readCharacter) && !isAlgebraicSign(readCharacter)) {
            throw new RuntimeException("Illegal state");
        }
        if (readCharacter == Parenthesis.CLOSE) {
            throw new RuntimeException("Illegal state");
        }
        buff = new StringBuilder();
        if (isDigit(readCharacter) || isAlgebraicSign(readCharacter) || readCharacter == Parenthesis.OPEN || isAlpha(readCharacter)) {
            buff.append(readCharacter);
        }
        if (readCharacter == Parenthesis.OPEN) {
            state = State.PARENTHESIS_OPEN;
        } else if (nextC != null && isDigit(nextC)) {
            state = State.DIGIT_READING;
        } else if (nextC != null && isAlpha(nextC)) {
            state = State.CHARACTER_READING;
        } else if (isDigit(readCharacter) && (nextC == null || isOperator(nextC) || nextC == Parenthesis.CLOSE)) {
            state = State.DIGIT_COMPLETED;
        } else if (isAlpha(readCharacter) && (nextC == null || isOperator(nextC) || nextC == Parenthesis.CLOSE)) {
            if (buffContainsConstant()) {
                state = State.CONSTANT_COMPLETED;
            } else if (buffContainsKeyword()) {
                state = State.POSTFIX_OPERATION;
            } else {
                state = State.VAR_COMPLETED;
            }
        }
    }

    private boolean isOperator(char c) {
        return Operation.match(c);
    }

    private boolean isDigit(char c) {
        return Character.isDigit(c) || c == '.';
    }

    private boolean isAlpha(char c) {
        return Character.isAlphabetic(c);
    }

    private boolean isAlgebraicSign(char c) {
        return c == '+' || c == '-';
    }

    public boolean tokenCompleted() {
        return state != State.EMPTY && state != State.CHARACTER_READING && state != State.DIGIT_READING;
    }

    enum State {
        EMPTY, DIGIT_READING, DIGIT_COMPLETED, OPERATION, PARENTHESIS_OPEN, PARENTHESIS_CLOSED, CHARACTER_READING, VAR_COMPLETED, POSTFIX_OPERATION, CONSTANT_COMPLETED
    }

}
