package org.entretien.kata.transactions;

import org.entretien.kata.amounts.Balance;
import org.entretien.kata.amounts.Money;

import java.time.LocalDateTime;
import java.util.Objects;

public class Operation extends Transaction {
    private final Balance balance;

    private Operation(TransactionType type, LocalDateTime date, Money amount, Balance balance) {
        super(type, date, amount);
        this.balance = balance;
    }

    public static Operation of(Transaction transaction, Balance balance) {
        return new Operation(transaction.type, transaction.date, transaction.amount, balance);
    }

    @Override
    public String toString() {
        return "Operation{" +
                "type=" + type.getName() +
                ", date=" + date +
                ", amount=" + amount.toString() +
                ", balance=" + balance.toString() +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Operation operation = (Operation) o;
        return Objects.equals(balance, operation.balance);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), balance);
    }
}
