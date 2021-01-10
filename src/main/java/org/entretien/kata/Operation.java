package org.entretien.kata;

import java.time.LocalDate;
import java.util.Objects;

public class Operation {
    private final OperationType type;
    private final LocalDate date;
    private final Money amount;
    private final Money balance;

    private Operation(OperationType type, LocalDate date, Money amount, Money balance) {
        this.type = type;
        this.date = date;
        this.amount = amount;
        this.balance = balance;
    }

    public static Operation of(OperationType type, Money amount, Money balance) {
        return new Operation(
                type,
                LocalDate.now(),
                amount,
                balance
        );
    }

    public Money getBalance() {
        return balance;
    }

    @Override
    public String toString() {
        return "Operation{" +
                "type=" + type.getName() +
                ", date=" + date +
                ", amount=" + amount +
                ", balance=" + balance +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Operation operation = (Operation) o;
        return type == operation.type && Objects.equals(date, operation.date) && Objects.equals(amount, operation.amount) && Objects.equals(balance, operation.balance);
    }

    @Override
    public int hashCode() {
        return Objects.hash(type, date, amount, balance);
    }

}
