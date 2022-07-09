package de.oglimmer.math;

/**
 * Exception for any internal state error. This exception should never been seen to the user and means the system has a bug.
 */
public class InvalidStateException extends RuntimeException {
    public InvalidStateException(String msg) {
        super(msg);
    }
}
