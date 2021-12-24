package de.punn.monopoly.model.trainstation;

import de.punn.monopoly.model.Property;
import lombok.extern.slf4j.Slf4j;

import java.math.BigDecimal;

@Slf4j
public abstract class TrainStation implements Property {

    private static final BigDecimal PURCHASE_PRICE = BigDecimal.valueOf(200L);
    private static final BigDecimal RENT = BigDecimal.valueOf(25L);

    public BigDecimal getPurchasePrice() {
        return PURCHASE_PRICE;
    }

    public BigDecimal getRent() {
        return RENT;
    }

    public BigDecimal getRentByPossession() {

        Long numberOfPossession = this.getOwner().getPropertyList().stream()
                .filter(TrainStation.class::isInstance)
                .count();

        switch (numberOfPossession.intValue()){
            case 1:
                return getRent();
            case 2:
                return BigDecimal.valueOf(50L);
            case 3:
                return BigDecimal.valueOf(100L);
            case 4:
                return BigDecimal.valueOf(200L);
            default:
                log.warn("More than four train stations as possessions found! Value is {}. Fallback to simple value of rent!",
                        numberOfPossession.intValue());
                return getRent();
        }
    }

    public boolean isAvailable() { return this.getOwner() == null; }

    public PropertyColor getPropertyColor() { return PropertyColor.NONE; }
}
