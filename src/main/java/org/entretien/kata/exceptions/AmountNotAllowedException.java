package org.entretien.kata.exceptions;

public class AmountNotAllowedException extends RuntimeException {
    public AmountNotAllowedException(String errorMessage) {
        super(errorMessage);
    }
}
