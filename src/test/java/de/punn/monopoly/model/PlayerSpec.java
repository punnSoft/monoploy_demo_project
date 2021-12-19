package de.punn.monopoly.model;

public class PlayerSpec {

    public static Player.PlayerBuilder valid() {
        return Player.builder()
                .name(Player.PlayerName.horse);
    }

}