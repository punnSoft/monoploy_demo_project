package de.punn.monopoly.model.street;

import de.punn.monopoly.model.Property;
import org.junit.jupiter.api.BeforeEach;

import java.math.BigDecimal;

class TheStripTest extends StreetTest {

    @BeforeEach
    void setUp() {
        this.classUnderTest = new TheStrip();
        this.expectedName = "TheStrip";
        this.expectedPropertyColor = Property.PropertyColor.BLUE;
        this.expectedPurchasePrice = BigDecimal.valueOf(400L);
        this.expectedSquarePosition = 39;
        this.expectedRent = BigDecimal.valueOf(100L);
    }
}