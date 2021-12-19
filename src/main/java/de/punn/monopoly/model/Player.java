package de.punn.monopoly.model;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

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
    boolean playerPassedGo = Boolean.TRUE;
}
