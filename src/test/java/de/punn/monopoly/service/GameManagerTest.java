package de.punn.monopoly.service;

import de.punn.monopoly.event.incoming.GameConfig;
import de.punn.monopoly.event.outgoing.GameResult;
import de.punn.monopoly.model.Player;
import de.punn.monopoly.model.PlayerSpec;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class GameManagerTest {

    @InjectMocks
    private GameManager gameManager;

    @Mock
    private Game game;

    @Test
    void shouldStartAGameAboutTenRoundsAndReturnGameResult (){

        GameConfig gameConfig = new GameConfig();
        gameConfig.setRounds(10);
        gameConfig.setPlayerNames(Arrays.stream(Player.PlayerName.values())
                .map(Enum::name)
                .collect(Collectors.toList()));

        Player player = PlayerSpec.valid().build();

        when(game.playARoundMonopoly(anyList())).thenReturn(player);

        GameResult gameResult = this.gameManager.startGame(gameConfig);

        verify(game, times(10)).playARoundMonopoly(anyList());

        assertThat(gameResult).isNotNull();
        assertThat(gameResult.getWinner()).isEqualTo(player.getName().toString());
        assertThat(gameResult.getBalance()).isEqualTo(player.getBalance());
        assertThat(gameResult.getPlayedRounds()).isEqualTo(gameConfig.getRounds());
    }

}