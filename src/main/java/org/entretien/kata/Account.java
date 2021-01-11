package org.entretien.kata;

import org.entretien.kata.exceptions.AmountNotAllowedException;
import org.entretien.kata.exceptions.OverdraftLimitExceededException;

import java.util.ArrayList;
import java.util.List;

public class Account {
    public static final double MINIMUM_ALLOWED_DEPOSIT_MONEY_AMOUNT = 0.01;
    private static final double OVERDRAFT_LIMIT_MONEY_AMOUNT = -50;

    private final Transactions transactions;

    private Account(List<Transaction> transactions) {
        this.transactions = Transactions.of(transactions);
    }

    public static Account newAccount() {
        return new Account(new ArrayList<>());
    }

    public static Account with(Money money) {
        List<Transaction> transactions = new ArrayList<>();

        transactions.add(
                Transaction.of(TransactionType.DEPOSIT_MONEY, money)
        );

        return new Account(transactions);
    }

    public void depositMoney(Money money) {
        if (money.isLessThan(Money.of(MINIMUM_ALLOWED_DEPOSIT_MONEY_AMOUNT)))
            throw new AmountNotAllowedException("Can not deposit money less than " + MINIMUM_ALLOWED_DEPOSIT_MONEY_AMOUNT + ".");

        transactions.saveTransaction(
                Transaction.of(TransactionType.DEPOSIT_MONEY, money)
        );
    }

    public void withdrawMoney(Money money) {
        Balance newBalance = transactions.calculateBalance().subtract(money);

        if (newBalance.isLessThan(Balance.of(OVERDRAFT_LIMIT_MONEY_AMOUNT)))
            throw new OverdraftLimitExceededException("Operation refused. Overdraft exceeded. Limit is " + OVERDRAFT_LIMIT_MONEY_AMOUNT + ".");

        transactions.saveTransaction(
                Transaction.of(TransactionType.WITHDRAW_MONEY, money)
        );
    }

    public Balance getBalance() {
        return transactions.calculateBalance();
    }

    public List<Operation> getOperations() {
        List<Operation> operations = new ArrayList<>();
        Balance balance = Balance.of(0);
        for (Transaction transaction : transactions.sortByDate()) {
            balance = balance.add(transaction.initBalance());
            operations.add(transaction.toOperation(balance));
        }
        return operations;
    }
}
