package org.entretien.kata;

public enum OperationType {
    ACCOUNT_CREATION("Account creation"),
    DEPOSIT_MONEY("Deposit money"),
    WITHDRAW_MONEY("Withdraw money");

    private final String name;

    OperationType(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
