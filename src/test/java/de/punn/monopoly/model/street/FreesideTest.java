package de.punn.monopoly.model.street;

import de.punn.monopoly.model.Property;
import org.junit.jupiter.api.BeforeEach;

import java.math.BigDecimal;

class FreesideTest extends StreetTest {

    @BeforeEach
    void setUp() {
        this.classUnderTest = new Freeside();
        this.expectedName = "Freeside";
        this.expectedPropertyColor = Property.PropertyColor.PINK;
        this.expectedPurchasePrice = BigDecimal.valueOf(160L);
        this.expectedSquarePosition = 14;
        this.expectedRent = BigDecimal.valueOf(12L);
    }
}