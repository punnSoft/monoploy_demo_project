package de.punn.monopoly.rules;

import de.punn.monopoly.model.Player;
import de.punn.monopoly.model.PlayerSpec;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;

class GoSquareRuleTest {

    private GoSquareRule goSquareRule;

    @BeforeEach
    void setUp() {
        this.goSquareRule = new GoSquareRule();
    }

    @Test
    void shouldRelevantBecauseSquarePositionIsGoSquare() {
        Player testPlayer = PlayerSpec.valid()
                .balance(BigDecimal.valueOf(200L))
                .build();

        assertThat(this.goSquareRule.isSquareRelevant(testPlayer)).isTrue();
    }

    @Test
    void shouldRelevantBecausePassedSquarePosition() {
        Player testPlayer = PlayerSpec.valid()
                .balance(BigDecimal.valueOf(200L))
                .squarePosition(39)
                .build();

        assertThat(this.goSquareRule.isSquareRelevant(testPlayer)).isTrue();
    }


    @Test
    void shouldIncreaseBalanceWithGoSquareValue() {

        Player testPlayer = PlayerSpec.valid()
                .squarePosition(39)
                .build();
        BigDecimal expectedBalance = BigDecimal.valueOf(1700L);

        this.goSquareRule.changeBalance(testPlayer);

        assertThat(testPlayer.getBalance()).isEqualByComparingTo(expectedBalance);
    }
}