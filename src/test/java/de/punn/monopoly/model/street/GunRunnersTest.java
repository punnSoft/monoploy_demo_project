package de.punn.monopoly.model.street;

import de.punn.monopoly.model.Property;
import org.junit.jupiter.api.BeforeEach;

import java.math.BigDecimal;

class GunRunnersTest extends StreetTest {

    @BeforeEach
    void setUp() {
        this.classUnderTest = new GunRunners();
        this.expectedName = "GunRunners";
        this.expectedPropertyColor = Property.PropertyColor.YELLOW;
        this.expectedPurchasePrice = BigDecimal.valueOf(280L);
        this.expectedSquarePosition = 29;
        this.expectedRent = BigDecimal.valueOf(24L);
    }
}