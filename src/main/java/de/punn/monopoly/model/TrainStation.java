package de.punn.monopoly.model;

import java.math.BigDecimal;

public abstract class TrainStation implements Property {

    private static final BigDecimal PURCHASE_PRICE = BigDecimal.valueOf(200L);
    private static final BigDecimal RENT = BigDecimal.valueOf(25L);

    public BigDecimal getPurchasePrice() {
        return PURCHASE_PRICE;
    }

    public BigDecimal getRent() {
        return RENT;
    }
}
