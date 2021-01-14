package org.entretien.kata.accounts.amounts;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class BalanceTest {

    @Test
    public void can_create_balance_with_negative_amount() {
        Assertions.assertDoesNotThrow(() -> Balance.of(-1));
    }

    @Test
    public void can_create_balance_from_money() {
        Money money = Money.of(10);

        Assertions.assertEquals(Balance.of(10), Balance.of(money));
    }

    @Test
    public void can_add_money_to_balance() {
        Balance balance = Balance.of(10);

        Balance result = balance.add(Money.of(5));

        Assertions.assertEquals(Balance.of(10 + 5), result);
    }

    @Test
    public void can_subtract_money_from_balance() {
        Balance balance = Balance.of(10);

        Balance result = balance.subtract(Money.of(5));

        Assertions.assertEquals(Balance.of(10 - 5), result);
    }

    @Test
    public void can_add_balance_to_balance() {
        Balance balance = Balance.of(10);

        Balance result = balance.add(Balance.of(5));

        Assertions.assertEquals(Balance.of(10 + 5), result);
    }

    @Test
    public void calculate_balance_negation() {
        Balance balance = Balance.of(10);

        Balance result = balance.negate();

        Assertions.assertEquals(Balance.of(-10), result);
    }

}
