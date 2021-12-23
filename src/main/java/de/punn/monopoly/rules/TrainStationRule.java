package de.punn.monopoly.rules;

import de.punn.monopoly.model.Player;
import de.punn.monopoly.model.trainstation.*;
import lombok.extern.slf4j.Slf4j;
import org.jeasy.rules.annotation.Action;
import org.jeasy.rules.annotation.Condition;
import org.jeasy.rules.annotation.Fact;
import org.jeasy.rules.annotation.Rule;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
@Component
@Rule(name = "train station rule", description = "If our position at a train station square we can buy it or we have to pay a rent.")
public class TrainStationRule {

    private final WestTrainStation westTrainStation;

    private final EastTrainStation eastTrainStation;

    private final SouthTrainStation southTrainStation;

    private final NorthTrainStation northTrainStation;

    private final List<TrainStation> trainStations;

    public TrainStationRule(WestTrainStation westTrainStation, EastTrainStation eastTrainStation,
                            SouthTrainStation southTrainStation, NorthTrainStation northTrainStation) {
        this.westTrainStation = westTrainStation;
        this.eastTrainStation = eastTrainStation;
        this.southTrainStation = southTrainStation;
        this.northTrainStation = northTrainStation;

        this.trainStations = List.of(this.westTrainStation, this.eastTrainStation, this.southTrainStation,
                this.northTrainStation);
    }


    @Condition
    public boolean isSquareRelevant(@Fact("player") Player player) {

        return this.trainStations.stream()
                .anyMatch(trainStation -> trainStation.getSquarePosition() == player.getSquarePosition());
    }

    @Action
    public void buyOrPayRentForTrainStation(@Fact("player") Player player) {

        this.trainStations.stream()
                .filter(trainStation -> trainStation.getSquarePosition() == player.getSquarePosition())
                .findFirst()
                .ifPresentOrElse(trainStation -> buyOrPayRentForTrainStation(player, trainStation),
                        () -> log.warn("No train station for given square position {} found!", player.getSquarePosition()));
    }

    private void buyOrPayRentForTrainStation(Player player, TrainStation trainStation) {

        if (trainStation.isAvailable()) {

            player.setBalance(player.getBalance().subtract(trainStation.getPurchasePrice()));
            trainStation.setOwner(player);
            player.getPropertyList().add(trainStation);
            log.info("train station rule has fired! Player bought {}. Player {}'s balance is {} now.",
                    trainStation.getName(), player.getName(), player.getBalance());

        } else if (trainStation.getOwner() != player) {

            var rentToPay = trainStation.getRentByPossession();
            player.setBalance(player.getBalance().subtract(rentToPay));

            var trainStationOwner = trainStation.getOwner();
            trainStationOwner.setBalance(trainStationOwner.getBalance().add(rentToPay));
            log.info("train station rule has fired! Player {} paid the rent of {} to train station owner {}. Player {}'s balance is {} now.",
                    player.getName(), rentToPay, trainStationOwner.getName(), player.getName(), player.getBalance());
        }
    }
}
