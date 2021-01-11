package org.entretien.kata;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class MoneyTest {

    @Test
    public void add_money_amount() {
        Money money = Money.of(10);

        Money sum = money.add(Money.of(4.5));

        Assertions.assertEquals(Money.of(14.5), sum);
    }

    @Test
    public void subtract_money_amount() {
        Money money = Money.of(10);

        Money result = money.subtract(Money.of(5));

        Assertions.assertEquals(Money.of(5), result);
    }
}
