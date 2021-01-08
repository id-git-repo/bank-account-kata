package org.entretien.kata;

import org.entretien.kata.exceptions.AmountNotAllowedException;

public class Account {
    public static final Money MINIMUM_ALLOWED_DEPOSIT_MONEY_AMOUNT = Money.of(0.01);
    private Money balance;

    private Account(Money money) {
        balance = money;
    }

    public static Account with(Money money) {
        return new Account(money);
    }

    public Money getBalance() {
        return balance;
    }

    public void depositMoney(Money money) throws AmountNotAllowedException {
        if(money.isLessThan(MINIMUM_ALLOWED_DEPOSIT_MONEY_AMOUNT))
            throw new AmountNotAllowedException("Can not deposit money less than " + money.toString() + ".");
        balance = balance.add(money);
    }
}
