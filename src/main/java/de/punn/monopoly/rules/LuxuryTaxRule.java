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
@Rule(name = "luxury tax rule", description = "If our position at square 38 we have to pay $75")
public class LuxuryTaxRule {

    private static final int LUXURY_TAX_SQUARE = 38;
    private static final BigDecimal TAX = BigDecimal.valueOf(75L);

    @Condition
    public boolean isSquareRelevant(@Fact("player") Player player) {

        return LUXURY_TAX_SQUARE == player.getSquarePosition();
    }

    @Action
    public void changeBalance(@Fact("player") Player player) {

        var newBalance = player.getBalance().subtract(TAX);
        player.setBalance(newBalance);
        log.info("luxury tax rule has fired! Player {}'s balance is {} now.", player.getName(), player.getBalance());
    }
}
