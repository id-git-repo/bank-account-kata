package org.entretien.kata;

import org.entretien.kata.exceptions.AmountNotAllowedException;
import org.entretien.kata.exceptions.OverdraftIsAlreadyUsedException;
import org.entretien.kata.exceptions.OverdraftLimitExceededException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

public class AccountTest {

    @Test
    public void create_account_with_initial_money_amount() {
        Account account = Account.with(Money.of(10.5));

        Assertions.assertEquals(Money.of(10.5), account.getBalance());
    }

    @Nested
    class DepositMoney {

        @Test
        public void can_not_deposit_money_giving_amount_less_than_one_cent() {
            Account account = Account.with(Money.of(10));
            Money money = Money.of(0.008);

            Assertions.assertThrows(AmountNotAllowedException.class, () -> account.depositMoney(money));
        }

        @Test
        public void can_deposit_money_giving_allowed_amount() {
            Account account = Account.with(Money.of(10));
            Money money = Money.of(1);

            account.depositMoney(money);

            Assertions.assertEquals(Money.of(11), account.getBalance());
        }

        @Test
        public void can_make_two_deposit_money_giving_allowed_amounts() {
            Account account = Account.with(Money.of(10));

            Money firstAmount = Money.of(1);
            account.depositMoney(firstAmount);

            Money secondAmount = Money.of(5);
            account.depositMoney(secondAmount);

            Assertions.assertEquals(Money.of(16), account.getBalance());
        }

        @Test
        public void can_make_three_deposit_money_giving_allowed_amounts() {
            Account account = Account.with(Money.of(10));

            Money firstAmount = Money.of(5);
            account.depositMoney(firstAmount);

            Money secondAmount = Money.of(10);
            account.depositMoney(secondAmount);

            Money thirdAmount = Money.of(4);
            account.depositMoney(thirdAmount);

            Assertions.assertEquals(Money.of(29), account.getBalance());
        }
    }

    @Nested
    class WithdrawMoney {

        @Test
        public void can_not_withdraw_money_when_overdraft_is_already_used() {
            Account account = Account.with(Money.of(-1));
            Money money = Money.of(10);

            Assertions.assertThrows(OverdraftIsAlreadyUsedException.class, () -> account.withdrawMoney(money));
        }

        @Test
        public void can_withdraw_money_giving_allowed_amount() {
            Account account = Account.with(Money.of(10));
            Money money = Money.of(5);

            account.withdrawMoney(money);

            Assertions.assertEquals(Money.of(5), account.getBalance());
        }

        @Test
        public void can_not_withdraw_money_when_overdraft_limit_exceeded() {
            Account account = Account.with(Money.of(10));
            Money money = Money.of(100);

            Assertions.assertThrows(OverdraftLimitExceededException.class, () -> account.withdrawMoney(money));
        }

        @Test
        public void can_make_two_withdraw_money_giving_allowed_amounts() {
            Account account = Account.with(Money.of(10));

            Money firstAmount = Money.of(1);
            account.withdrawMoney(firstAmount);

            Money secondAmount = Money.of(5);
            account.withdrawMoney(secondAmount);

            Assertions.assertEquals(Money.of(4), account.getBalance());
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

            Assertions.assertEquals(Money.of(-9), account.getBalance());
        }
    }


}
