package org.entretien.kata;

import java.time.LocalDateTime;

public class Transaction {
    private final TransactionType type;
    private final LocalDateTime date;
    private final Money amount;

    private Transaction(TransactionType type, LocalDateTime date, Money amount) {
        this.type = type;
        this.date = date;
        this.amount = amount;
    }

    public static Transaction of(TransactionType type, Money amount) {
        return new Transaction(type, LocalDateTime.now(), amount);
    }

    public Balance initBalance() {
        return TransactionType.DEPOSIT_MONEY.equals(type)
                ? Balance.of(amount)
                : Balance.of(amount).negate();
    }

    public int compareByDate(Transaction transaction) {
        return date.compareTo(transaction.date);
    }

    public Operation toOperation(Balance balance) {
        return Operation.of(type, date, amount, balance);
    }
}
