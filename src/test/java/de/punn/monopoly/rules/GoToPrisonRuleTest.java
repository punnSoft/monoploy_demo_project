package de.punn.monopoly.rules;

import de.punn.monopoly.model.Player;
import de.punn.monopoly.model.PlayerSpec;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;

class GoToPrisonRuleTest {

    private GoToPrisonRule goToPrisonRule;

    @BeforeEach
    void setUp() {

        this.goToPrisonRule = new GoToPrisonRule();
    }

    @Test
    void shouldRelevantBecauseSquarePositionIsGoToPrisonSquare() {
        Player testPlayer = PlayerSpec.valid()
                .squarePosition(30)
                .build();

        assertThat(this.goToPrisonRule.isSquareRelevant(testPlayer)).isTrue();
    }

    @Test
    void shouldNotRelevantBecauseSquarePositionIsNotGoToPrisonSquare() {
        Player testPlayer = PlayerSpec.valid()
                .squarePosition(31)
                .build();

        assertThat(this.goToPrisonRule.isSquareRelevant(testPlayer)).isFalse();
    }

    @Test
    void shouldReduceBalanceWithGoToPrisonFineAndMovePlayerToSquareInJail (){
        Player testPlayer = PlayerSpec.valid()
                .balance(BigDecimal.valueOf(50L))
                .squarePosition(30)
                .build();
        this.goToPrisonRule.changeBalance(testPlayer);

        assertThat(testPlayer.getBalance()).isEqualByComparingTo(BigDecimal.ZERO);
        assertThat(testPlayer.getSquarePosition()).isEqualTo(GoToPrisonRule.IN_JAIL_SQUARE);
    }
}