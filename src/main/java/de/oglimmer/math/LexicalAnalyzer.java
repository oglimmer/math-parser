package de.oglimmer.math;

public class LexicalAnalyzer {

    enum State {
        EMPTY, DIGIT_READING, DIGIT_COMPLETED, PLUS, MINUS
    }

    private StringBuilder buff;
    private State state = State.EMPTY;

    public void read(char currentC, Character nextC) {
        switch (state) {
            case EMPTY:
            case PLUS:
            case MINUS:
                processEmptyPlusMinus(currentC, nextC);
                break;
            case DIGIT_READING:
                processReadingInProgress(currentC, nextC);
                break;
            case DIGIT_COMPLETED:
                processDigitReadingCompleted(currentC);
                break;
        }
    }

    private void processDigitReadingCompleted(char currentC) {
        if (currentC == '+') {
            state = State.PLUS;
        } else if (currentC == '-') {
            state = State.MINUS;
        } else {
            throw new RuntimeException("Illegal state");
        }
        buff = new StringBuilder(Character.toString(currentC));
    }

    private void processReadingInProgress(char currentC, Character nextC) {
        if (isDigit(currentC) && nextC != null && isDigit(nextC)) {
            buff.append(currentC);
        } else if (isDigit(currentC) && (nextC == null || nextC == '+' || nextC == '-')) {
            buff.append(currentC);
            state = State.DIGIT_COMPLETED;
        } else if (currentC == '+' || currentC == '-') {
            throw new RuntimeException("Illegal state");
        }
    }

    private boolean isDigit(char c) {
        return Character.isDigit(c) || c == '.';
    }

    private void processEmptyPlusMinus(char currentC, Character nextC) {
        buff = new StringBuilder();
        if (isDigit(currentC) && nextC != null && isDigit(nextC)) {
            buff.append(currentC);
            state = State.DIGIT_READING;
        } else if (isDigit(currentC) && (nextC == null || nextC == '+' || nextC == '-')) {
            buff.append(currentC);
            state = State.DIGIT_COMPLETED;
        } else if (currentC == '+' || currentC == '-') {
            throw new RuntimeException("Must start with digit.");
        }
    }

    public boolean tokenCompleted() {
        return state == State.DIGIT_COMPLETED || state == State.PLUS || state == State.MINUS;
    }

    public String nextToken() {
        return buff.toString();
    }
}
