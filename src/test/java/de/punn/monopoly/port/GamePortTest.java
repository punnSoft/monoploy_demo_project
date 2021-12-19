package de.punn.monopoly.port;

import de.punn.monopoly.config.TestTags;
import de.punn.monopoly.event.income.GameConfig;
import de.punn.monopoly.event.outgoing.GameResult;
import de.punn.monopoly.model.Player;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import javax.validation.ConstraintViolationException;
import java.util.Arrays;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@TestTags.IntegrationTest
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
class GamePortTest {

    @Autowired
    private GamePort gamePort;

    @BeforeEach
    void setUp() {
    }

    @Test
    void shouldThrowConstraintViolationExceptionForInvalidGameConfig() {

        var gameConfig = new GameConfig();
        gameConfig.setRounds(0);
        gameConfig.setPlayerNames(Arrays.stream(Player.PlayerName.values())
                .map(Enum::name)
                .collect(Collectors.toList()));

        assertThrows(ConstraintViolationException.class, () -> gamePort.startGame(gameConfig));
    }

    @Test
    void shouldStartAGameReturnAValidResult() {

        var gameConfig = new GameConfig();
        gameConfig.setRounds(1);
        gameConfig.setPlayerNames(Arrays.stream(Player.PlayerName.values())
                .map(Enum::name)
                .collect(Collectors.toList()));

        ResponseEntity<GameResult> gameResultResponseEntity = gamePort.startGame(gameConfig);

        assertThat(gameResultResponseEntity.getStatusCode()).isEqualByComparingTo(HttpStatus.OK);
        assertThat(gameResultResponseEntity.getBody()).isNotNull();
        assertThat(gameResultResponseEntity.getBody().getWinner()).isNotBlank();
        assertThat(gameResultResponseEntity.getBody().getBalance()).isNotNull();
        assertThat(gameResultResponseEntity.getBody().getPlayedRounds()).isEqualTo(gameConfig.getRounds());
    }
}