package org.entretien.kata.exceptions;

public class OverdraftLimitExceededException extends RuntimeException {
    public OverdraftLimitExceededException(String errorMessage) {
        super(errorMessage);
    }
}
