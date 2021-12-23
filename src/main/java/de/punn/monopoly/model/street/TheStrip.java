package de.punn.monopoly.model.street;

import de.punn.monopoly.model.Player;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Component
public class TheStrip extends Street{

    private static final int POSITION = 39;
    private static final BigDecimal PURCHASE_PRICE = BigDecimal.valueOf(400L);
    private static final BigDecimal RENT = BigDecimal.valueOf(25L);

    private Player owner;

    @Override
    public BigDecimal getPurchasePrice() {
        return PURCHASE_PRICE;
    }

    @Override
    public BigDecimal getRent() {
        return RENT;
    }

    @Override
    public int getSquarePosition() {
        return POSITION;
    }

    @Override
    public String getName() {
        return TheStrip.class.getSimpleName();
    }

    @Override
    public Player getOwner() {
        return owner;
    }

    @Override
    public void setOwner(@NotNull Player player) {
        this.owner = player;
    }
}
