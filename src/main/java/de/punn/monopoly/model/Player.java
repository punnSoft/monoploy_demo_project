package de.punn.monopoly.model;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Builder
@Data
public class Player {

    @AllArgsConstructor
    public enum PlayerName {
        dog("Dog"),
        iron("Iron"),
        battleship("Battleship"),
        cannon("Cannon"),
        magicHat("Magic Hat"),
        horse("Horse"),
        shoe("Shoe"),
        car("Car");

        private final String name;
    }

    private PlayerName name;

    @Builder.Default
    private BigDecimal balance = BigDecimal.valueOf(1500L);

    @Builder.Default
    private int squarePosition = 0;

    @Builder.Default
    private boolean playerPassedGo = Boolean.TRUE;

    @Builder.Default
    @Getter
    private List<Property> propertyList = new ArrayList<>();
}
