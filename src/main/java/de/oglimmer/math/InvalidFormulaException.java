package de.oglimmer.math;

/**
 * Exception used for flagging any user input error, like missing parenthesis
 */
public class InvalidFormulaException extends RuntimeException {

    public InvalidFormulaException(String msg) {
        super(msg);
    }
}
