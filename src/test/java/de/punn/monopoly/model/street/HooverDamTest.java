package de.punn.monopoly.model.street;

import de.punn.monopoly.model.Property;
import org.junit.jupiter.api.BeforeEach;

import java.math.BigDecimal;

class HooverDamTest extends StreetTest{

    @BeforeEach
    void setUp() {
        this.classUnderTest = new HooverDam();
        this.expectedName = "HooverDam";
        this.expectedPropertyColor = Property.PropertyColor.BLUE;
        this.expectedPurchasePrice = BigDecimal.valueOf(350L);
        this.expectedSquarePosition = 37;
        this.expectedRent = BigDecimal.valueOf(70L);
    }

}