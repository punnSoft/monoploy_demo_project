package de.punn.monopoly.service;

import de.punn.monopoly.model.Player;
import de.punn.monopoly.model.PlayerSpec;
import de.punn.monopoly.rules.LuxuryTaxRule;
import de.punn.monopoly.tool.Dice;
import org.jeasy.rules.api.Rules;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.MockedStatic;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mockStatic;

@ExtendWith(MockitoExtension.class)
class GameTest {

    private Game game;

    @BeforeEach
    void setUp() {
        var rules = new Rules(new LuxuryTaxRule());
        this.game = new Game(rules);
    }

    @Test
    void shouldPlayAroundMonopolyAndFireRuleLuxuryTax() {
        MockedStatic<Dice> diceMockedStatic = mockStatic(Dice.class);
        diceMockedStatic.when(Dice::rollDice).thenReturn(1);

        List<Player> players = List.of(PlayerSpec.valid()
                .squarePosition(37)
                .balance(BigDecimal.valueOf(75L))
                .playerPassedGo(Boolean.FALSE)
                .build()
        );

        Player winner = this.game.playARoundMonopoly(players);

        assertThat(winner).isNotNull();
        assertThat(winner.getBalance()).isEqualByComparingTo(BigDecimal.ZERO);
        assertThat(winner.getSquarePosition()).isEqualTo(38);
    }
}