package de.punn.monopoly.model;

import java.math.BigDecimal;

public interface Property {

    enum PropertyColor {
        NONE,BLUE,ORANGE,YELLOW,PINK,GREEN,RED,PURPLE,GRAY
    }

    BigDecimal getPurchasePrice();

    boolean isAvailable();

    BigDecimal getRent();

    int getSquarePosition();

    String getName();

    Player getOwner();

    void setOwner(Player player);

    PropertyColor getPropertyColor();
}
