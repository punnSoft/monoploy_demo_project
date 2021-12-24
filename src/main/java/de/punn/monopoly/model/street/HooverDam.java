package de.punn.monopoly.model.street;

import de.punn.monopoly.model.Player;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@NoArgsConstructor
@Component
public class HooverDam extends Street{

    private static final int POSITION = 37;
    private static final BigDecimal PURCHASE_PRICE = BigDecimal.valueOf(350L);
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
        return HooverDam.class.getSimpleName();
    }

    @Override
    public Player getOwner() {
        return owner;
    }

    @Override
    public void setOwner(@NotNull Player player) {
        this.owner = player;
    }

    @Override
    public PropertyColor getPropertyColor() {
        return PropertyColor.BLUE;
    }
}
