package org.entretien.kata.transactions;

import org.entretien.kata.amounts.Balance;
import org.entretien.kata.amounts.Money;

import java.time.LocalDateTime;
import java.util.Objects;

public class Transaction {
    protected final TransactionType type;
    protected final LocalDateTime date;
    protected final Money amount;

    protected Transaction(TransactionType type, LocalDateTime date, Money amount) {
        this.type = type;
        this.date = date;
        this.amount = amount;
    }

    public static Transaction of(TransactionType type, Money amount) {
        return new Transaction(type, LocalDateTime.now(), amount);
    }

    public Balance getAmountAsBalance() {
        return TransactionType.DEPOSIT_MONEY.equals(type)
                ? Balance.of(amount)
                : Balance.of(amount).negate();
    }

    public int compareByDate(Transaction transaction) {
        return date.compareTo(transaction.date);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Transaction that = (Transaction) o;
        return type == that.type && Objects.equals(date, that.date) && Objects.equals(amount, that.amount);
    }

    @Override
    public int hashCode() {
        return Objects.hash(type, date, amount);
    }
}
