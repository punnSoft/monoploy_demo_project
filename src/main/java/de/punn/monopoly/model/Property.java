package de.punn.monopoly.model;

import java.math.BigDecimal;

public interface Property {

    BigDecimal getPurchasePrice();

    boolean isAvailable();

    BigDecimal getRent();

    int getSquarePosition();

    String getName();

    Player getOwner();

    void setOwner(Player player);
}
