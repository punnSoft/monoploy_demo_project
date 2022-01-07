package de.punn.monopoly.model.street;

import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class JacobsTown extends Street{

    public static final int POSITION = 27;
    public static final BigDecimal PURCHASE_PRICE = BigDecimal.valueOf(260L);
    public static final BigDecimal RENT = BigDecimal.valueOf(22L);
    public static final PropertyColor PROPERTY_COLOR = PropertyColor.YELLOW;

    public JacobsTown() {
        this.rent = JacobsTown.RENT;
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
        return JacobsTown.class.getSimpleName();
    }
}
