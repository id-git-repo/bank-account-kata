package org.entretien.kata;

import org.entretien.kata.exceptions.AmountNotAllowedException;
import org.entretien.kata.exceptions.OverdraftIsAlreadyUsedException;
import org.entretien.kata.exceptions.OverdraftLimitExceededException;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

public class AccountTest {

    @Test
    public void create_account_with_initial_money_amount() {
        Account account = Account.with(Money.of(10.5));

        assertEquals(Money.of(10.5), account.getBalance());
    }

    @Nested
    class DepositMoney {

        @Test
        public void can_not_deposit_money_giving_amount_less_than_one_cent() {
            Account account = Account.with(Money.of(10));

            assertThrows(AmountNotAllowedException.class,
                    () -> account.depositMoney(Money.of(0.008)));
        }

        @Test
        public void can_deposit_money_giving_allowed_amount() {
            Account account = Account.with(Money.of(10));

            account.depositMoney(Money.of(1));

            assertEquals(Money.of(11), account.getBalance());
        }

        @Test
        public void can_make_two_deposit_money_giving_allowed_amounts() {
            Account account = Account.with(Money.of(10));

            account.depositMoney(Money.of(1));

            account.depositMoney(Money.of(5));

            assertEquals(Money.of(16), account.getBalance());
        }

        @Test
        public void can_make_three_deposit_money_giving_allowed_amounts() {
            Account account = Account.with(Money.of(10));

            account.depositMoney(Money.of(5));

            account.depositMoney(Money.of(10));

            account.depositMoney(Money.of(4));

            assertEquals(Money.of(29), account.getBalance());
        }
    }

    @Nested
    class WithdrawMoney {

        @Test
        public void can_not_withdraw_money_when_overdraft_is_already_used() {
            Account account = Account.with(Money.of(-1));

            assertThrows(OverdraftIsAlreadyUsedException.class,
                    () -> account.withdrawMoney(Money.of(10)));
        }

        @Test
        public void can_withdraw_money_giving_allowed_amount() {
            Account account = Account.with(Money.of(10));

            account.withdrawMoney(Money.of(5));

            assertEquals(Money.of(5), account.getBalance());
        }

        @Test
        public void can_not_withdraw_money_when_overdraft_limit_exceeded() {
            Account account = Account.with(Money.of(10));

            assertThrows(OverdraftLimitExceededException.class,
                    () -> account.withdrawMoney(Money.of(100)));
        }

        @Test
        public void can_make_two_withdraw_money_giving_allowed_amounts() {
            Account account = Account.with(Money.of(10));

            Money firstAmount = Money.of(1);
            account.withdrawMoney(firstAmount);

            Money secondAmount = Money.of(5);
            account.withdrawMoney(secondAmount);

            assertEquals(Money.of(4), account.getBalance());
        }

        @Test
        public void can_make_three_withdraw_money_giving_allowed_amounts() {
            Account account = Account.with(Money.of(10));

            Money firstAmount = Money.of(5);
            account.withdrawMoney(firstAmount);

            Money secondAmount = Money.of(4);
            account.withdrawMoney(secondAmount);

            Money thirdAmount = Money.of(10);
            account.withdrawMoney(thirdAmount);

            assertEquals(Money.of(-9), account.getBalance());
        }
    }

    @Nested
    class OperationsHistory {

        @Test
        public void should_add_creation_operation_when_account_creation() {
            Account account = Account.with(Money.of(10));

            assertIterableEquals(
                    Arrays.asList(
                            Operation.of(
                                    OperationType.ACCOUNT_CREATION,
                                    Money.of(10),
                                    Money.of(10))
                    ),
                    account.getOperations()
            );
        }

        @Test
        public void should_add_operation_when_deposit_money() {
            Account account = Account.with(Money.of(10));

            account.depositMoney(Money.of((5)));

            assertIterableEquals(
                    Arrays.asList(
                            Operation.of(
                                    OperationType.ACCOUNT_CREATION,
                                    Money.of(10),
                                    Money.of(10)),
                            Operation.of(
                                    OperationType.DEPOSIT_MONEY,
                                    Money.of(5),
                                    Money.of(15))
                    ),
                    account.getOperations()
            );
        }

        @Test
        public void should_add_operation_when_withdraw_money() {
            Account account = Account.with(Money.of(10));

            account.withdrawMoney(Money.of((5)));

            assertIterableEquals(
                    Arrays.asList(
                            Operation.of(
                                    OperationType.ACCOUNT_CREATION,
                                    Money.of(10),
                                    Money.of(10)
                            ),
                            Operation.of(
                                    OperationType.WITHDRAW_MONEY,
                                    Money.of(5),
                                    Money.of(5))
                    ),
                    account.getOperations()
            );
        }

        @Test
        public void should_describe_operations() {
            Account account = Account.with(Money.of(10));

            account.depositMoney(Money.of((7)));

            account.withdrawMoney(Money.of((2)));

            account.depositMoney(Money.of((5)));

            assertIterableEquals(
                    Arrays.asList(
                            Operation.of(
                                    OperationType.ACCOUNT_CREATION,
                                    Money.of(10),
                                    Money.of(10)
                            ),
                            Operation.of(
                                    OperationType.DEPOSIT_MONEY,
                                    Money.of(7),
                                    Money.of(17)
                            ),
                            Operation.of(
                                    OperationType.WITHDRAW_MONEY,
                                    Money.of(2),
                                    Money.of(15)
                            ),
                            Operation.of(
                                OperationType.DEPOSIT_MONEY,
                                Money.of(5),
                                Money.of(20)
                            )
                    ),
                    account.getOperations()
            );
        }

    }

}
