package org.entretien.kata.accounts.exceptions;

public class AmountNotAllowedException extends RuntimeException {
    public AmountNotAllowedException(String errorMessage) {
        super(errorMessage);
    }
}
