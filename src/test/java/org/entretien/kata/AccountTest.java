package org.entretien.kata;

import org.entretien.kata.exceptions.AmountNotAllowedException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

public class AccountTest {

    @Test
    public void create_account_with_initial_money_amount() {

        Money money = Money.of(10.5);

        Account account = Account.with(money);

        Assertions.assertEquals(Money.of(10.5), account.getBalance());
    }

    @Nested
    class DepositMoney {

        @Test
        public void can_not_deposit_money_giving_amount_less_than_one_cent() {
            Money money = Money.of(0.008);
            Account account = Account.with(Money.of(10));

            Assertions.assertThrows(AmountNotAllowedException.class, () -> account.depositMoney(money));
        }

        @Test
        public void can_deposit_money_giving_allowed_amount() throws AmountNotAllowedException {
            Account account = Account.with(Money.of(10));
            Money money = Money.of(1);

            account.depositMoney(money);

            Assertions.assertEquals(Money.of(11), account.getBalance());
        }

        @Test
        public void can_make_two_deposit_money_giving_allowed_amounts() throws AmountNotAllowedException {
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

}
