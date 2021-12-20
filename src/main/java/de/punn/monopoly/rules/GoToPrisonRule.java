package de.punn.monopoly.rules;

import de.punn.monopoly.model.Player;
import lombok.extern.slf4j.Slf4j;
import org.jeasy.rules.annotation.Action;
import org.jeasy.rules.annotation.Condition;
import org.jeasy.rules.annotation.Fact;
import org.jeasy.rules.annotation.Rule;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Slf4j
@Component
@Rule(name = "Go to prison rule", description = "If our position at square 30, we have to move to square 10 and have to pay a fine of $50.")
public class GoToPrisonRule {

    public static final int IN_JAIL_SQUARE = 10;
    private static final int GO_TO_PRISON_SQUARE = 30;
    private static final BigDecimal FINE_VALUE = BigDecimal.valueOf(50L);

    @Condition
    public boolean isSquareRelevant(@Fact("player") Player player) {

        return GO_TO_PRISON_SQUARE == player.getSquarePosition();
    }

    @Action
    public void changeBalance(@Fact("player") Player player) {

        var newBalance = player.getBalance().subtract(FINE_VALUE);
        player.setBalance(newBalance);
        player.setSquarePosition(IN_JAIL_SQUARE);
        log.info("Go to prison rule has fired! Player {}'s balance is {} now.", player.getName(), player.getBalance());
    }
}
