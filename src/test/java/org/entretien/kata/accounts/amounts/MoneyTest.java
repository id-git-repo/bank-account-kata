package org.entretien.kata.accounts.amounts;

import org.entretien.kata.accounts.exceptions.AmountNotAllowedException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class MoneyTest {

    @Test
    public void can_not_create_money_with_negative_amount() {
        assertThrows(AmountNotAllowedException.class, () -> Money.of(-1));
    }

}
