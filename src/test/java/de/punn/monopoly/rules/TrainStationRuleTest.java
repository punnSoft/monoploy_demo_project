package de.punn.monopoly.rules;

import de.punn.monopoly.model.Player;
import de.punn.monopoly.model.PlayerSpec;
import de.punn.monopoly.model.WestTrainStation;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
class TrainStationRuleTest {

    private TrainStationRule trainStationRule;
    private WestTrainStation westTrainStation;

    @BeforeEach
    void setUp() {
        this.westTrainStation = new WestTrainStation();
        this.trainStationRule = new TrainStationRule(this.westTrainStation);
    }

    @Test
    void shouldRelevantBecauseSquarePositionIsWestTrainStationSquare() {
        Player testPlayer = PlayerSpec.valid()
                .squarePosition(15)
                .build();

        assertThat(this.trainStationRule.isSquareRelevant(testPlayer)).isTrue();
    }

    @Test
    void shouldRelevantBecauseSquarePositionIsNotATrainStationSquare() {
        Player testPlayer = PlayerSpec.valid()
                .squarePosition(0)
                .build();

        assertThat(this.trainStationRule.isSquareRelevant(testPlayer)).isFalse();
    }

    @Test
    void shouldBuyTheWestTrainStation() {
        Player testPlayer = PlayerSpec.valid()
                .balance(BigDecimal.valueOf(200L))
                .squarePosition(15)
                .build();

        this.trainStationRule.buyOrPayRentForTrainStation(testPlayer);

        assertThat(testPlayer.getBalance()).isEqualByComparingTo(BigDecimal.ZERO);
        assertThat(testPlayer.getPropertyList().contains(this.westTrainStation)).isTrue();
        assertThat(this.westTrainStation.getOwner()).isEqualTo(testPlayer);
        assertThat(this.westTrainStation.isAvailable()).isFalse();
    }

    @Test
    void shouldPaidRentForTheWestTrainStation() {

        Player trainStationOwner = PlayerSpec.valid()
                .balance(BigDecimal.ZERO)
                .name(Player.PlayerName.car)
                .squarePosition(7)
                .build();
        this.westTrainStation.setOwner(trainStationOwner);

        Player testPlayer = PlayerSpec.valid()
                .balance(BigDecimal.valueOf(25L))
                .squarePosition(15)
                .build();

        this.trainStationRule.buyOrPayRentForTrainStation(testPlayer);

        assertThat(testPlayer.getBalance()).isEqualByComparingTo(BigDecimal.ZERO);
        assertThat(this.westTrainStation.getOwner()).isEqualTo(trainStationOwner);
        assertThat(this.westTrainStation.isAvailable()).isFalse();
        assertThat(trainStationOwner.getBalance()).isEqualByComparingTo(BigDecimal.valueOf(25L));
    }
}