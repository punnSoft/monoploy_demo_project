package de.punn.monopoly.model.street;

import de.punn.monopoly.model.Property;
import org.junit.jupiter.api.BeforeEach;

import java.math.BigDecimal;

class GoodspringsTest extends StreetTest {

    @BeforeEach
    void setUp() {
        this.classUnderTest = new Goodsprings();
        this.expectedName = "Goodsprings";
        this.expectedPropertyColor = Property.PropertyColor.PINK;
        this.expectedPurchasePrice = BigDecimal.valueOf(140L);
        this.expectedSquarePosition = 11;
        this.expectedRent = BigDecimal.TEN;
    }
}