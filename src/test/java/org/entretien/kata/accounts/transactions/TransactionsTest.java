package org.entretien.kata.accounts.transactions;

import org.entretien.kata.accounts.amounts.Balance;
import org.entretien.kata.accounts.amounts.Money;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

public class TransactionsTest {

    @Test
    public void calculate_balance_giving_transactions_list() {
        Transactions transactions = Transactions.of(
                Arrays.asList(
                        Transaction.of(TransactionType.DEPOSIT_MONEY, Money.of(10)),
                        Transaction.of(TransactionType.DEPOSIT_MONEY, Money.of(10)),
                        Transaction.of(TransactionType.WITHDRAW_MONEY, Money.of(5))
                )
        );

        Balance balance = transactions.calculateBalance();

        Assertions.assertEquals(Balance.of(10 + 10 - 5), balance);
    }
}
