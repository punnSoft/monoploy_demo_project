package de.punn.monopoly.service;

import de.punn.monopoly.config.TestTags;
import de.punn.monopoly.model.Player;
import de.punn.monopoly.model.PlayerSpec;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@TestTags.IntegrationTest
class GameTest {

    @Autowired
    private Game game;

    @Test
    void shouldStartGameOneRoundWith2Players() {

        Player playerOne = PlayerSpec.valid()
                .name(Player.PlayerName.horse)
                .build();

        Player playerTwo = PlayerSpec.valid()
                .name(Player.PlayerName.car)
                .build();

        List<Player> players = List.of(playerOne, playerTwo);

        var winner = this.game.playARoundMonopoly(players);

        players.forEach(player -> {
            assertThat(player.getBalance()).isGreaterThan(BigDecimal.ZERO);
            assertThat(player.isPlayerPassedGo()).isFalse();
            assertThat(player.getSquarePosition()).isGreaterThan(1);
            assertThat(player.getSquarePosition()).isLessThan(12);
        });

        assertThat(winner).isNotNull();
        assertThat(players.contains(winner)).isTrue();
    }
}