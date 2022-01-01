package de.punn.monopoly.model.street;

import de.punn.monopoly.model.Player;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Component
public class TheStrip extends Street{

    public static final int POSITION = 39;
    public static final BigDecimal PURCHASE_PRICE = BigDecimal.valueOf(400L);
    public static final BigDecimal RENT = BigDecimal.valueOf(100L);
    public static final PropertyColor PROPERTY_COLOR = PropertyColor.BLUE;

    private Player owner;

    public TheStrip() {
        this.rent = TheStrip.RENT;
    }

    @Override
    public BigDecimal getPurchasePrice() {
        return PURCHASE_PRICE;
    }

    @Override
    public int getSquarePosition() {
        return POSITION;
    }

    @Override
    public PropertyColor getPropertyColor() {
        return PROPERTY_COLOR;
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
