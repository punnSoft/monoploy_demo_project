package de.punn.monopoly.service;

import de.punn.monopoly.model.Player;
import de.punn.monopoly.tool.Dice;
import lombok.extern.slf4j.Slf4j;
import org.jeasy.rules.api.Facts;
import org.jeasy.rules.api.Rules;
import org.jeasy.rules.api.RulesEngine;
import org.jeasy.rules.core.DefaultRulesEngine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;

@Service
@Slf4j
public class Game {

    private static final int GO_SQUARE = 0;

    @Autowired
    private Rules rules;

    public Player playARoundMonopoly(List<Player> players) {

        RulesEngine rulesEngine = new DefaultRulesEngine();
        rulesEngine.getParameters().setSkipOnFirstAppliedRule(Boolean.FALSE);

        players.forEach(player -> {
            movePlayer(player);

            Facts facts = new Facts();
            facts.put("player", player);
            rulesEngine.fire(this.rules, facts);
        });

        printResult(players);

        return getWinnerOfThisRound(players);
    }

    private Player getWinnerOfThisRound(List<Player> players) {
        var maxBalance = players.stream()
                .map(Player::getBalance)
                .max(Comparator.naturalOrder())
                .get();

        return players.stream()
                .filter(player -> player.getBalance().compareTo(maxBalance)==0)
                .sorted(Comparator.comparing(Player::getName))
                .findFirst()
                .get();
    }

    private void printResult(List<Player> players) {

        log.info("-----------------");
        log.info("Result per round:");
        log.info("-----------------");

        players.forEach(player ->
                log.info("Player {} has a balance of {} and is at position {}.",
                        player.getName().name(),
                        player.getBalance(),
                        player.getSquarePosition()));
    }

    private void movePlayer(Player player) {

        var diceRoll = Dice.rollDice();
        player.setSquarePosition((player.getSquarePosition() + diceRoll) % 39);

        if (player.getSquarePosition() == 0 || (player.getSquarePosition() - diceRoll) <= GO_SQUARE) {
            player.setPlayerPassedGo(Boolean.TRUE);
        }
    }
}
