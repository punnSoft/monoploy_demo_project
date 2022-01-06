package de.punn.monopoly.model.street;

import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class Goodsprings extends Street{

    public static final int POSITION = 11;
    public static final BigDecimal PURCHASE_PRICE = BigDecimal.valueOf(140L);
    public static final BigDecimal RENT = BigDecimal.TEN;
    public static final PropertyColor PROPERTY_COLOR = PropertyColor.PINK;

    public Goodsprings() {
        this.rent = Goodsprings.RENT;
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
        return Goodsprings.class.getSimpleName();
    }
}
