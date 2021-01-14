package org.entretien.kata.accounts.exceptions;

public class OverdraftLimitExceededException extends RuntimeException {
    public OverdraftLimitExceededException(String errorMessage) {
        super(errorMessage);
    }
}
