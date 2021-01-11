package org.entretien.kata;

import java.time.LocalDateTime;
import java.util.Objects;

public class Operation {
    private final TransactionType type;
    private final LocalDateTime date;
    private final Money amount;
    private final Balance balance;

    private Operation(TransactionType type, LocalDateTime date, Money amount, Balance balance) {
        this.type = type;
        this.date = date;
        this.amount = amount;
        this.balance = balance;
    }

    public static Operation of(TransactionType type, LocalDateTime date, Money amount, Balance balance) {
        return new Operation(type, date, amount, balance);
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
        Operation operation = (Operation) o;
        return type == operation.type && Objects.equals(date.toLocalDate(), operation.date.toLocalDate()) && Objects.equals(amount, operation.amount) && Objects.equals(balance, operation.balance);
    }

    @Override
    public int hashCode() {
        return Objects.hash(type, date, amount, balance);
    }

}
