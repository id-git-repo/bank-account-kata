package org.entretien.kata;

import org.entretien.kata.exceptions.AmountNotAllowedException;
import org.entretien.kata.exceptions.OverdraftIsAlreadyUsedException;
import org.entretien.kata.exceptions.OverdraftLimitExceededException;

public class Account {
    public static final Money MINIMUM_ALLOWED_DEPOSIT_MONEY_AMOUNT = Money.of(0.01);
    private static final Money OVERDRAFT_THRESHOLD_MONEY_AMOUNT = Money.of(0);
    private static final Money OVERDRAFT_LIMIT_MONEY_AMOUNT = Money.of(-50);

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
            throw new AmountNotAllowedException("Can not deposit money less than " + MINIMUM_ALLOWED_DEPOSIT_MONEY_AMOUNT + ".");
        balance = balance.add(money);
    }

    public void withdrawMoney(Money money) {
        if(balance.isLessThan(OVERDRAFT_THRESHOLD_MONEY_AMOUNT))
            throw new OverdraftIsAlreadyUsedException("Operation refused. Overdraft is already used.");

        Money newBalance = balance.subtract(money);

        if(newBalance.isLessThan(OVERDRAFT_LIMIT_MONEY_AMOUNT))
            throw new OverdraftLimitExceededException("Operation refused. Overdraft exceeded. Limit is " + OVERDRAFT_LIMIT_MONEY_AMOUNT + ".");
        balance = newBalance;
    }
}
