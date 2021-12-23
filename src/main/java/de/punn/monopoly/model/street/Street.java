package de.punn.monopoly.model.street;

import de.punn.monopoly.model.Property;

public abstract class Street implements Property {

    public boolean isAvailable() { return this.getOwner() == null; }
}
