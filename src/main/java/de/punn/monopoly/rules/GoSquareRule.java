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
@Rule(name = "Go square rule", description = "If our position at square 0 or we passed the square we have to get $1500")
public class GoSquareRule {

    private static final BigDecimal MONEY_VALUE = BigDecimal.valueOf(200L);

    @Condition
    public boolean isSquareRelevant(@Fact("player") Player player) {
        return player.isPlayerPassedGo();
    }

    @Action
    public void changeBalance(@Fact("player") Player player) {

        var newBalance = player.getBalance().add(MONEY_VALUE);
        player.setBalance(newBalance);
        player.setPlayerPassedGo(Boolean.FALSE);
        log.info("Go square rule has fired! Player {}'s balance is {} now.", player.getName(), player.getBalance());
    }
}
