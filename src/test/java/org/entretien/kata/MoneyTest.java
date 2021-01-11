package org.entretien.kata;

import org.entretien.kata.exceptions.AmountNotAllowedException;
import org.entretien.kata.exceptions.OverdraftIsAlreadyUsedException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class MoneyTest {

    @Test
    public void can_not_create_money_with_negative_amount() {
        assertThrows(AmountNotAllowedException.class, () -> Money.of(-1));
    }

    @Test
    public void can_create_money_with_allowed_amount() {
        Assertions.assertDoesNotThrow(() -> Money.of(1));
    }

}
