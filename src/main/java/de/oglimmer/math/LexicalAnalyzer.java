package de.oglimmer.math;

import java.util.ArrayList;
import java.util.List;

public class LexicalAnalyzer {

    public List<String> parseToTokens(String input) {
        List<String> nodes = new ArrayList<>();
        for (int pos = 0; pos < input.length(); pos++) {
            char currentC = input.charAt(pos);
            Character nextC = pos < input.length() - 1 ? input.charAt(pos + 1) : null;
            read(currentC, nextC);
            if (tokenCompleted()) {
                nodes.add(nextToken());
            }
        }
        return nodes;
    }

    enum State {
        EMPTY, DIGIT_READING, DIGIT_COMPLETED, OPERATION, PARENTHESIS_OPEN, PARENTHESIS_CLOSED, VAR_READING, VAR_COMPLETED
    }

    private StringBuilder buff;
    private State state = State.EMPTY;

    private void read(char readCharacter, Character nextC) {
        switch (state) {
            case EMPTY:
            case PARENTHESIS_OPEN:
            case OPERATION:
                inStateEmptyOrOperator(readCharacter, nextC);
                break;
            case VAR_READING:
                inStateVarReadingInProgress(readCharacter, nextC);
                break;
            case DIGIT_READING:
                inStateDigitReadingInProgress(readCharacter, nextC);
                break;
            case VAR_COMPLETED:
            case DIGIT_COMPLETED:
            case PARENTHESIS_CLOSED:
                inStateDigitReadingCompleted(readCharacter);
                break;
        }
    }

    private void inStateDigitReadingCompleted(char readCharacter) {
        if (isOperator(readCharacter)) {
            state = State.OPERATION;
        } else if (readCharacter == ')') {
            state = State.PARENTHESIS_CLOSED;
        } else {
            throw new RuntimeException("Illegal state");
        }
        buff = new StringBuilder(Character.toString(readCharacter));
    }

    private void inStateDigitReadingInProgress(char readCharacter, Character nextC) {
        if (isOperator(readCharacter)) {
            throw new RuntimeException("Illegal state");
        }
        if (readCharacter == '(') {
            throw new RuntimeException("Illegal state");
        }
        if (isDigit(readCharacter)) {
            buff.append(readCharacter);
        }
        if (nextC == null || isOperator(nextC) || nextC == ')') {
            state = State.DIGIT_COMPLETED;
        }
    }

    private void inStateVarReadingInProgress(char readCharacter, Character nextC) {
        if (isOperator(readCharacter)) {
            throw new RuntimeException("Illegal state");
        }
        if (readCharacter == '(') {
            throw new RuntimeException("Illegal state");
        }
        if (isAlpha(readCharacter)) {
            buff.append(readCharacter);
        }
        if (nextC == null || isOperator(nextC) || nextC == ')') {
            state = State.VAR_COMPLETED;
        }
    }

    private void inStateEmptyOrOperator(char readCharacter, Character nextC) {
        if (isOperator(readCharacter) && !isAlgebraicSign(readCharacter)) {
            throw new RuntimeException("Illegal state");
        }
        if (readCharacter == ')') {
            throw new RuntimeException("Illegal state");
        }
        buff = new StringBuilder();
        if (isDigit(readCharacter) || isAlgebraicSign(readCharacter) || readCharacter == '(' || isAlpha(readCharacter)) {
            buff.append(readCharacter);
        }
        if (readCharacter == '(') {
            state = State.PARENTHESIS_OPEN;
        } else if (nextC != null && isDigit(nextC)) {
            state = State.DIGIT_READING;
        } else if (nextC != null && isAlpha(nextC)) {
            state = State.VAR_READING;
        } else if (isDigit(readCharacter) && (nextC == null || isOperator(nextC) || nextC == ')')) {
            state = State.DIGIT_COMPLETED;
        } else if (isAlpha(readCharacter) && (nextC == null || isOperator(nextC) || nextC == ')')) {
            state = State.VAR_COMPLETED;
        }
    }

    private boolean isOperator(char c) {
        return c == '+' || c == '-' || c == '*' || c == '/';
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
        return state != State.EMPTY && state != State.VAR_READING && state != State.DIGIT_READING;
    }

    public String nextToken() {
        return buff.toString();
    }
}
