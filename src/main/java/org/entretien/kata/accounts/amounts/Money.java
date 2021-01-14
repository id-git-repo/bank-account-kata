package org.entretien.kata.accounts.amounts;

import org.entretien.kata.accounts.exceptions.AmountNotAllowedException;

public class Money extends Amount {

    public Money(double amount) {
        super(amount);
    }

    public static Money of(double amount) {
        if(amount < 0)
            throw new AmountNotAllowedException("Can not create money with a negative amount.");
        return new Money(amount);
    }
}
