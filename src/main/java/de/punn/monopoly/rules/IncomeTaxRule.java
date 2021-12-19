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
@Rule(name = "Income tax rule", description = "If our position at square 5 we have to pay a minimum of $200 or 10 percent of the wealth")
public class IncomeTaxRule {

    private static final int INCOME_TAX_SQUARE = 4;
    private static final BigDecimal TAX = BigDecimal.valueOf(200L);
    private static final BigDecimal TEN_PERCENT = BigDecimal.valueOf(0.10);

    @Condition
    public boolean isSquareRelevant(@Fact("player") Player player) {
        return INCOME_TAX_SQUARE == player.getSquarePosition();
    }

    @Action
    public void changeBalance(@Fact("player") Player player) {

        var newBalance = player.getBalance()
                .subtract(calculateValueToPay(player));
        player.setBalance(newBalance);
        log.info("Income tax rule has fired! Player {}'s balance is {} now.", player.getName(), player.getBalance());
    }

    private BigDecimal calculateValueToPay(Player player) {

        var tenPercentOfWealth = player.getBalance().multiply(TEN_PERCENT);
        if ( tenPercentOfWealth.compareTo(TAX) == 1) {
            return tenPercentOfWealth;
        }

        return TAX;
    }
}
