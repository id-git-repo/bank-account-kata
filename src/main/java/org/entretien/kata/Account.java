package org.entretien.kata;

import org.entretien.kata.exceptions.AmountNotAllowedException;
import org.entretien.kata.exceptions.OverdraftIsAlreadyUsedException;
import org.entretien.kata.exceptions.OverdraftLimitExceededException;

import java.util.ArrayList;
import java.util.List;

public class Account {
    public static final double MINIMUM_ALLOWED_DEPOSIT_MONEY_AMOUNT = 0.01;
    private static final double OVERDRAFT_THRESHOLD_MONEY_AMOUNT = 0;
    private static final double OVERDRAFT_LIMIT_MONEY_AMOUNT = -50;

    private final List<Operation> operations;

    private Account(List<Operation> operations) {
        this.operations = operations;
    }

    public static Account newAccount() {
        List<Operation> operations = new ArrayList<>();

        operations.add(Operation.of(OperationType.ACCOUNT_CREATION, Money.of(0), Balance.of(0)));

        return new Account(operations);
    }

    public static Account with(Money money) {
        List<Operation> operations = new ArrayList<>();

        operations.add(Operation.of(OperationType.ACCOUNT_CREATION, money, Balance.fromMoney(money)));

        return new Account(operations);
    }

    public void depositMoney(Money money) {
        if (money.isLessThan(Money.of(MINIMUM_ALLOWED_DEPOSIT_MONEY_AMOUNT)))
            throw new AmountNotAllowedException("Can not deposit money less than " + MINIMUM_ALLOWED_DEPOSIT_MONEY_AMOUNT + ".");

        saveOperation(
                Operation.of(OperationType.DEPOSIT_MONEY, money, getBalance().add(money))
        );
    }

    public void withdrawMoney(Money money) {
        if (getBalance().isLessThan(Balance.of(OVERDRAFT_THRESHOLD_MONEY_AMOUNT)))
            throw new OverdraftIsAlreadyUsedException("Operation refused. Overdraft is already used.");

        Balance newBalance = getBalance().subtract(money);

        if (newBalance.isLessThan(Balance.of(OVERDRAFT_LIMIT_MONEY_AMOUNT)))
            throw new OverdraftLimitExceededException("Operation refused. Overdraft exceeded. Limit is " + OVERDRAFT_LIMIT_MONEY_AMOUNT + ".");

        saveOperation(
                Operation.of(OperationType.WITHDRAW_MONEY, money, newBalance)
        );
    }

    public Balance getBalance() {
        return operations.get(operations.size() - 1).getBalance();
    }

    public List<Operation> getOperations() {
        return operations;
    }

    private void saveOperation(Operation operation) {
        operations.add(operation);
    }

}
