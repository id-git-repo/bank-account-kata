package org.entretien.kata.exceptions;

public class OverdraftIsAlreadyUsedException extends RuntimeException {
    public OverdraftIsAlreadyUsedException(String errorMessage) {
        super(errorMessage);
    }
}
