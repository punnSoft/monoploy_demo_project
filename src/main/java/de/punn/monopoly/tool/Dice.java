package de.punn.monopoly.tool;

import org.springframework.stereotype.Component;

import java.util.Random;

@Component
public class Dice {

    public static int rollDice () {
        Random dice = new Random();
        return dice.nextInt(10) + 2;
    }

}
