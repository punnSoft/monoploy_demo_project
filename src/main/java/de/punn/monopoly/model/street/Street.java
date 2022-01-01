package de.punn.monopoly.model.street;

import de.punn.monopoly.model.Property;

import java.math.BigDecimal;
import java.util.Map;

public abstract class Street implements Property {

    private static final Map<PropertyColor, Integer> propertyColorAndCountMap =
            Map.of(PropertyColor.BLUE, 2);

    BigDecimal rent = BigDecimal.ZERO;

    public boolean isAvailable() { return this.getOwner() == null; }

    public BigDecimal getRent() {

        var propertyColor = getPropertyColor();

        long numberOfPossession = this.getOwner().getPropertyList().stream()
                .filter(Street.class::isInstance)
                .filter(street -> propertyColor == street.getPropertyColor())
                .count();

        return numberOfPossession == propertyColorAndCountMap.get(propertyColor)
                ? rent.multiply(BigDecimal.valueOf(2L)) : rent;
    }
}
