package de.punn.monopoly.rules;

import de.punn.monopoly.model.Player;
import de.punn.monopoly.model.PlayerSpec;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;

class IncomeTaxRuleTest {

    private IncomeTaxRule incomeTaxRule;

    @BeforeEach
    void setUp() {
        this.incomeTaxRule = new IncomeTaxRule();
    }

    @Test
    void shouldRelevantBecauseSquarePositionIsIncomeTaxSquare() {
        Player testPlayer = PlayerSpec.valid()
                .balance(BigDecimal.valueOf(200L))
                .squarePosition(4)
                .build();

        assertThat(this.incomeTaxRule.isSquareRelevant(testPlayer)).isTrue();
    }

    @Test
    void shouldNotRelevantBecauseSquarePositionIsIsNotIncomeTaxSquare() {
        Player testPlayer = PlayerSpec.valid()
                .balance(BigDecimal.valueOf(200L))
                .squarePosition(24)
                .build();

        assertThat(this.incomeTaxRule.isSquareRelevant(testPlayer)).isFalse();
    }

    @Test
    void shouldReduceBalanceWithMinimumValue() {

        Player testPlayer = PlayerSpec.valid()
                .balance(BigDecimal.valueOf(200L))
                .squarePosition(39)
                .build();
        this.incomeTaxRule.changeBalance(testPlayer);

        assertThat(testPlayer.getBalance()).isEqualByComparingTo(BigDecimal.ZERO);
    }

    @Test
    void shouldReduceBalanceWithHigherValueThanMinimum() {

        Player testPlayer = PlayerSpec.valid()
                .balance(BigDecimal.valueOf(5000L))
                .squarePosition(39)
                .build();
        BigDecimal expectedBalance = BigDecimal.valueOf(4500L);

        this.incomeTaxRule.changeBalance(testPlayer);

        assertThat(testPlayer.getBalance()).isEqualByComparingTo(expectedBalance);
    }
}