package org.entretien.kata.accounts.amounts;

import java.util.Objects;

public abstract class Amount {
    protected final double amount;

    protected Amount(double amount) {
        this.amount = amount;
    }

    public boolean isLessThan(Amount amount) {
        return this.amount < amount.amount;
    }

    @Override
    public String toString() {
        return amount + Currency.EURO.getSymbol();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Amount amount1 = (Amount) o;
        return Double.compare(amount1.amount, amount) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(amount);
    }
}
