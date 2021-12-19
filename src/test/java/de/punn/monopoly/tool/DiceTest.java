package de.punn.monopoly.tool;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class DiceTest {

    @Test
    void shouldRollDice() {
        assertThat(Dice.rollDice()).isLessThan(12);
        assertThat(Dice.rollDice()).isGreaterThan(1);
    }
}