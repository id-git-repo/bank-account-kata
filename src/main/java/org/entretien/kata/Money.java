package org.entretien.kata;

import java.util.Objects;

public class Money {
    private double amount;

    private Money(double amount) {
        this.amount = amount;
    }

    public static Money of(double amount) {
        return new Money(amount);
    }

    public Money add(Money money) {
        return new Money(amount + money.amount);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Money money = (Money) o;
        return Double.compare(money.amount, amount) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(amount);
    }

    public boolean isLessThan(Money money) {
        return amount < money.amount;
    }
}
