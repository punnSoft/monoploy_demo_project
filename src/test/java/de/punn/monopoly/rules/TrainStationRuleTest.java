package de.punn.monopoly.rules;

import de.punn.monopoly.model.Player;
import de.punn.monopoly.model.PlayerSpec;
import de.punn.monopoly.model.trainstation.EastTrainStation;
import de.punn.monopoly.model.trainstation.NorthTrainStation;
import de.punn.monopoly.model.trainstation.SouthTrainStation;
import de.punn.monopoly.model.trainstation.WestTrainStation;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class TrainStationRuleTest {

    private TrainStationRule trainStationRule;

    private WestTrainStation westTrainStation;

    @Mock
    private EastTrainStation eastTrainStation;
    @Mock
    private SouthTrainStation southTrainStation;
    @Mock
    private NorthTrainStation northTrainStation;

    @BeforeEach
    void setUp() {
        this.westTrainStation = new WestTrainStation();
        this.trainStationRule = new TrainStationRule(this.westTrainStation, this.eastTrainStation,
                this.southTrainStation, this.northTrainStation);
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

        when(this.eastTrainStation.getSquarePosition()).thenReturn(35);
        when(this.southTrainStation.getSquarePosition()).thenReturn(5);
        when(this.northTrainStation.getSquarePosition()).thenReturn(25);

        Player testPlayer = PlayerSpec.valid()
                .squarePosition(0)
                .build();

        assertThat(this.trainStationRule.isSquareRelevant(testPlayer)).isFalse();

        verify(this.eastTrainStation).getSquarePosition();
        verify(this.southTrainStation).getSquarePosition();
        verify(this.northTrainStation).getSquarePosition();
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

        verifyNoInteractions(this.eastTrainStation, this.northTrainStation, this.southTrainStation);
    }

    @Test
    void shouldPaidRentForTheWestTrainStation() {

        Player trainStationOwner = PlayerSpec.valid()
                .balance(BigDecimal.ZERO)
                .name(Player.PlayerName.car)
                .squarePosition(7)
                .build();
        trainStationOwner.getPropertyList().add(this.westTrainStation);
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

        verifyNoInteractions(this.eastTrainStation, this.northTrainStation, this.southTrainStation);
    }
}