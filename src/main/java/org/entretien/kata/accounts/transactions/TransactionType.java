package org.entretien.kata.accounts.transactions;

public enum TransactionType {
    DEPOSIT_MONEY("Deposit money"),
    WITHDRAW_MONEY("Withdraw money");

    private final String name;

    TransactionType(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
