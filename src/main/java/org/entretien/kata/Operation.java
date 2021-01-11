package org.entretien.kata;

import java.time.LocalDateTime;
import java.util.Objects;

public class Operation {
    private final OperationType type;
    private final LocalDateTime date;
    private final Money amount;
    private final Balance balance;

    private Operation(OperationType type, LocalDateTime date, Money amount, Balance balance) {
        this.type = type;
        this.date = date;
        this.amount = amount;
        this.balance = balance;
    }

    public static Operation of(OperationType type, Money amount, Balance balance) {
        return new Operation(
                type,
                LocalDateTime.now(),
                amount,
                balance
        );
    }

    public int compareByDate(Operation operation) {
        return date.compareTo(operation.date);
    }

    public Balance getBalance() {
        return balance;
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
