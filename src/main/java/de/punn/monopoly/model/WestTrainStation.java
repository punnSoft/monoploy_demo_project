package de.punn.monopoly.model;

import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotNull;

@NoArgsConstructor
@Component
public class WestTrainStation extends TrainStation {

    private static final int POSITION = 15;

    private Player owner = null;

    @Override
    public boolean isAvailable() { return owner == null; }

    @Override
    public int getSquarePosition() {
        return POSITION;
    }

    @Override
    public String getName() {
        return WestTrainStation.class.getSimpleName();
    }

    @Override
    public Player getOwner() {
        return owner;
    }

    @Override
    public void setOwner(@NotNull Player player) {
        this.owner = player;
    }
}
