package de.punn.monopoly.model.street;

import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class GunRunners extends Street{

    public static final int POSITION = 29;
    public static final BigDecimal PURCHASE_PRICE = BigDecimal.valueOf(280L);
    public static final BigDecimal RENT = BigDecimal.valueOf(24L);
    public static final PropertyColor PROPERTY_COLOR = PropertyColor.YELLOW;

    public GunRunners() {
        this.rent = GunRunners.RENT;
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
        return GunRunners.class.getSimpleName();
    }
}
