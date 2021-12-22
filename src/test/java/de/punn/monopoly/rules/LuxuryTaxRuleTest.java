package de.punn.monopoly.rules;

import de.punn.monopoly.model.Player;
import de.punn.monopoly.model.PlayerSpec;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;

class LuxuryTaxRuleTest {

    private LuxuryTaxRule luxuryTaxRule;

    @BeforeEach
    void setUp (){
        this.luxuryTaxRule = new LuxuryTaxRule();
    }

    @Test
    void shouldRelevantBecauseSquarePositionIsLuxuryTaxSquare (){
        Player testPlayer = PlayerSpec.valid()
                .squarePosition(38)
                .build();

        assertThat(this.luxuryTaxRule.isSquareRelevant(testPlayer)).isTrue();
    }

    @Test
    void shouldNotRelevantBecauseSquarePositionIsNotLuxuryTaxSquare (){
        Player testPlayer = PlayerSpec.valid()
                .squarePosition(9)
                .build();

        assertThat(this.luxuryTaxRule.isSquareRelevant(testPlayer)).isFalse();
    }

    @Test
    void shouldReduceBalanceWithLuxuryTax (){
        Player testPlayer = PlayerSpec.valid()
                .balance(BigDecimal.valueOf(75L))
                .squarePosition(38)
                .build();
        this.luxuryTaxRule.changeBalance(testPlayer);

        assertThat(testPlayer.getBalance()).isEqualByComparingTo(BigDecimal.ZERO);
    }

}