package de.punn.monopoly.model.street;

import de.punn.monopoly.model.PlayerSpec;
import de.punn.monopoly.model.Property;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;

abstract class StreetTest {

    protected Street classUnderTest;
    protected BigDecimal expectedPurchasePrice;
    protected int expectedSquarePosition;
    protected Property.PropertyColor expectedPropertyColor;
    protected String expectedName;
    protected BigDecimal expectedRent;

    @Test
    void shouldGetRent() {
        var propertyOwner = PlayerSpec.valid().build();
        propertyOwner.getPropertyList().add(classUnderTest);
        classUnderTest.setOwner(propertyOwner);

        assertThat(this.classUnderTest.getRent()).isEqualByComparingTo(expectedRent);
    }

    @Test
    void shouldGetPurchasePrice() {
        assertThat(this.classUnderTest.getPurchasePrice()).isEqualByComparingTo(expectedPurchasePrice);
    }

    @Test
    void shouldGetSquarePosition() {
        assertThat(this.classUnderTest.getSquarePosition()).isEqualTo(expectedSquarePosition);
    }

    @Test
    void shouldGetPropertyColor() {
        assertThat(this.classUnderTest.getPropertyColor()).isEqualTo(expectedPropertyColor);
    }

    @Test
    void shouldGetName() {
        assertThat(this.classUnderTest.getName()).isEqualTo(expectedName);
    }
}
