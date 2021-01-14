package org.entretien.kata.transactions;

import org.entretien.kata.amounts.Balance;

import java.util.List;

import static java.util.stream.Collectors.toList;

public class Transactions {

    private final List<Transaction> transactions;

    public Transactions(List<Transaction> transactions) {
        this.transactions = transactions;
    }

    public static Transactions of(List<Transaction> transactions) {
        return new Transactions(transactions);
    }

    public void saveTransaction(Transaction transaction) {
        transactions.add(transaction);
    }

    public Balance calculateBalance() {
        return transactions.stream()
                .map(transaction -> transaction.getAmountAsBalance())
                .reduce(Balance.of(0), (balance1, balance2) -> balance1.add(balance2));
    }

    public List<Transaction> sortByDate() {
        return transactions
                .stream()
                .sorted((first, second) -> first.compareByDate(second))
                .collect(toList());
    }

}
