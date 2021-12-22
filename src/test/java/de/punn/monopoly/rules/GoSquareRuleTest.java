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
        Player testPlayer = PlayerSpec.valid().build();

        assertThat(this.goSquareRule.isSquareRelevant(testPlayer)).isTrue();
    }

    @Test
    void shouldRelevantBecausePassedSquarePosition() {
        Player testPlayer = PlayerSpec.valid()
                .squarePosition(5)
                .build();

        assertThat(this.goSquareRule.isSquareRelevant(testPlayer)).isTrue();
    }

    @Test
    void shouldNotRelevantBecausePassedNotSquarePosition() {
        Player testPlayer = PlayerSpec.valid()
                .squarePosition(10)
                .playerPassedGo(Boolean.FALSE)
                .build();

        assertThat(this.goSquareRule.isSquareRelevant(testPlayer)).isFalse();
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