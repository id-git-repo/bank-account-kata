package org.entretien.kata.exceptions;

public class AmountNotAllowedException extends Exception {
    public AmountNotAllowedException(String errorMessage) {
        super(errorMessage);
    }
}
