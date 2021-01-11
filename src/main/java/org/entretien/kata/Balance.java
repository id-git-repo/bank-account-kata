package org.entretien.kata;

public class Balance extends Amount {

    private Balance(double amount) {
        super(amount);
    }

    public static Balance of(double amount) {
        return new Balance(amount);
    }

    public static Balance of(Money amount) {
        return new Balance(amount.amount);
    }

    public Balance add(Money money) {
        return of(amount + money.amount);
    }

    public Balance subtract(Money money) {
        return  of(amount- money.amount);
    }

}
