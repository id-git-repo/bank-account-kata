package org.entretien.kata.accounts.amounts;

public class Balance extends Amount {

    private Balance(double amount) {
        super(amount);
    }

    public static Balance of(double amount) {
        return new Balance(amount);
    }

    public static Balance of(Money money) {
        return new Balance(money.amount);
    }

    public Balance add(Money money) {
        return of(amount + money.amount);
    }

    public Balance subtract(Money money) {
        return of(amount - money.amount);
    }

    public Balance negate() {
        return of(-amount);
    }

    public Balance add(Balance balance) {
        return of(amount + balance.amount);
    }
}
