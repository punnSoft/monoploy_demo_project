package de.punn.monopoly.service;

import de.punn.monopoly.event.incoming.GameConfig;
import de.punn.monopoly.event.outgoing.GameResult;
import de.punn.monopoly.model.Player;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@Service
public class GameManager {

    private final Game game;

    public GameResult startGame (GameConfig gameConfig) throws IllegalArgumentException{

        List<Player> players = new ArrayList<>();
        gameConfig.getPlayerNames()
                .forEach(playerName -> players.add(Player.builder()
                        .name(Player.PlayerName.valueOf(playerName))
                        .build()));

        var winner = this.playMonopoly(players, gameConfig.getRounds());

        return GameResult.builder()
                .winner(winner.getName().name())
                .balance(winner.getBalance())
                .playedRounds(gameConfig.getRounds())
                .build();
    }

    private Player playMonopoly(List<Player> players, int rounds){
        Player player = Player.builder().build();
        while (rounds > 0) {
          player = this.game.playARoundMonopoly(players);
          rounds--;
        }
        return player;
    }


}
