package de.punn.monopoly.rules;

import de.punn.monopoly.model.Player;
import de.punn.monopoly.model.PlayerSpec;
import de.punn.monopoly.model.street.HooverDam;
import de.punn.monopoly.model.street.TheStrip;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verifyNoInteractions;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class StreetRuleTest {

    private StreetRule streetRule;
    private HooverDam hooverDam;

    @Mock
    private TheStrip theStrip;

    @BeforeEach
    void setUp() {

        this.hooverDam = new HooverDam();
        this.streetRule = new StreetRule(this.hooverDam, this.theStrip);
    }

    @Test
    void shouldRelevantBecauseSquarePositionIsHooverDamSquare() {
        Player testPlayer = PlayerSpec.valid()
                .squarePosition(HooverDam.POSITION)
                .build();

        assertThat(this.streetRule.isSquareRelevant(testPlayer)).isTrue();
    }

    @Test
    void shouldRelevantBecauseSquarePositionIsTheStripSquare() {
        Player testPlayer = PlayerSpec.valid()
                .squarePosition(TheStrip.POSITION)
                .build();

        when(this.theStrip.getSquarePosition()).thenReturn(TheStrip.POSITION);
        assertThat(this.streetRule.isSquareRelevant(testPlayer)).isTrue();
    }

    @Test
    void shouldNotRelevantBecauseSquarePositionIsGoSquare() {
        Player testPlayer = PlayerSpec.valid()
                .squarePosition(0)
                .build();

        when(this.theStrip.getSquarePosition()).thenReturn(TheStrip.POSITION);
        assertThat(this.streetRule.isSquareRelevant(testPlayer)).isFalse();
    }

    @Test
    void shouldBuyTheHooverDamStreet() {
        Player testPlayer = PlayerSpec.valid()
                .balance(HooverDam.PURCHASE_PRICE)
                .squarePosition(HooverDam.POSITION)
                .build();

        this.streetRule.buyOrPayRentForStreet(testPlayer);

        assertThat(testPlayer.getBalance()).isEqualByComparingTo(BigDecimal.ZERO);
        assertThat(testPlayer.getPropertyList().contains(this.hooverDam)).isTrue();
        assertThat(this.hooverDam.getOwner()).isEqualTo(testPlayer);
        assertThat(this.hooverDam.isAvailable()).isFalse();

        verifyNoInteractions(this.theStrip);
    }

    @Test
    void shouldPaidRentForTheHooverDam() {

        Player streetOwner = PlayerSpec.valid()
                .balance(BigDecimal.ZERO)
                .name(Player.PlayerName.car)
                .squarePosition(7)
                .build();

        streetOwner.getPropertyList().add(this.hooverDam);
        this.hooverDam.setOwner(streetOwner);

        Player testPlayer = PlayerSpec.valid()
                .balance(HooverDam.RENT)
                .squarePosition(HooverDam.POSITION)
                .build();

        this.streetRule.buyOrPayRentForStreet(testPlayer);

        assertThat(testPlayer.getBalance()).isEqualByComparingTo(BigDecimal.ZERO);
        assertThat(this.hooverDam.getOwner()).isEqualTo(streetOwner);
        assertThat(this.hooverDam.isAvailable()).isFalse();
        assertThat(streetOwner.getBalance()).isEqualByComparingTo(HooverDam.RENT);

        verifyNoInteractions(this.theStrip);
    }

    @Test
    void shouldPaidDoubleRentForTheHooverDam() {

        Player streetOwner = PlayerSpec.valid()
                .balance(BigDecimal.ZERO)
                .name(Player.PlayerName.car)
                .squarePosition(7)
                .build();

        streetOwner.getPropertyList().add(this.hooverDam);
        streetOwner.getPropertyList().add(this.theStrip);
        this.hooverDam.setOwner(streetOwner);

        when(this.theStrip.getPropertyColor()).thenReturn(TheStrip.PROPERTY_COLOR);

        Player testPlayer = PlayerSpec.valid()
                .balance(HooverDam.RENT.multiply(BigDecimal.valueOf(2L)))
                .squarePosition(HooverDam.POSITION)
                .build();

        this.streetRule.buyOrPayRentForStreet(testPlayer);

        assertThat(testPlayer.getBalance()).isEqualByComparingTo(BigDecimal.ZERO);
        assertThat(this.hooverDam.getOwner()).isEqualTo(streetOwner);
        assertThat(this.hooverDam.isAvailable()).isFalse();
        assertThat(streetOwner.getBalance()).isEqualByComparingTo(HooverDam.RENT.multiply(BigDecimal.valueOf(2L)));
    }
}