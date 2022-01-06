package de.punn.monopoly.model.street;

import de.punn.monopoly.model.Property;
import org.junit.jupiter.api.BeforeEach;

import java.math.BigDecimal;

class PrimmTest extends StreetTest {

    @BeforeEach
    void setUp() {
        this.classUnderTest = new Primm();
        this.expectedName = "Primm";
        this.expectedPropertyColor = Property.PropertyColor.PINK;
        this.expectedPurchasePrice = BigDecimal.valueOf(140L);
        this.expectedSquarePosition = 12;
        this.expectedRent = BigDecimal.TEN;
    }
}