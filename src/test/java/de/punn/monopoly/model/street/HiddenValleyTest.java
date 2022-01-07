package de.punn.monopoly.model.street;

import de.punn.monopoly.model.Property;
import org.junit.jupiter.api.BeforeEach;

import java.math.BigDecimal;

class HiddenValleyTest extends StreetTest {

    @BeforeEach
    void setUp() {
        this.classUnderTest = new HiddenValley();
        this.expectedName = "HiddenValley";
        this.expectedPropertyColor = Property.PropertyColor.YELLOW;
        this.expectedPurchasePrice = BigDecimal.valueOf(260L);
        this.expectedSquarePosition = 26;
        this.expectedRent = BigDecimal.valueOf(22L);
    }
}