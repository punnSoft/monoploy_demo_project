package de.punn.monopoly.model.trainstation;

import de.punn.monopoly.model.Player;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotNull;

@NoArgsConstructor
@Component
public class EastTrainStation extends TrainStation {

    private static final int POSITION = 35;

    private Player owner = null;

    @Override
    public int getSquarePosition() {
        return POSITION;
    }

    @Override
    public String getName() {
        return EastTrainStation.class.getSimpleName();
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
