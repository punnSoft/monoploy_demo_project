package de.punn.monopoly.model.street;

import de.punn.monopoly.model.Property;
import org.junit.jupiter.api.BeforeEach;

import java.math.BigDecimal;

class JacobsTownTest extends StreetTest {

    @BeforeEach
    void setUp() {
        this.classUnderTest = new JacobsTown();
        this.expectedName = "JacobsTown";
        this.expectedPropertyColor = Property.PropertyColor.YELLOW;
        this.expectedPurchasePrice = BigDecimal.valueOf(260L);
        this.expectedSquarePosition = 27;
        this.expectedRent = BigDecimal.valueOf(22L);
    }
}