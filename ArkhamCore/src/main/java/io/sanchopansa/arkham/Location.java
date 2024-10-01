package io.sanchopansa.arkham;

import java.util.ArrayList;
import java.util.List;

public class Location extends AbstractGameElement {
    private final String name;
    private final List<LocationType> locationType = new ArrayList<>();
    private final String description;
    private final boolean isStreet;
    private final boolean isStable;

    private boolean isSealed = false;
    private boolean isOpened = true;
    private boolean hasActivity = false;

    public Location(Expansion e,
                    String name,
                    String description,
                    //List<LocationType> locationTypes,
                    boolean isStreet,
                    boolean isStable
    ) {
        super(e);
        this.name = name;
        this.description = description;
        //this.locationType.addAll(locationTypes);
        this.isStreet = isStreet;
        this.isStable = isStable;
    }

    public String getName() {
        return name;
    }

    public List<LocationType> getLocationType() {
        return locationType;
    }

    public String getDescription() {
        return description;
    }

    public boolean isStreet() {
        return isStreet;
    }

    public boolean isStable() {
        return isStable;
    }

    public boolean isSealed() {
        return isSealed;
    }

    public void seal() {
        isSealed = true;
    }

    public void removeSeal() {
        isSealed = false;
    }

    public boolean isOpened() {
        return isOpened;
    }

    public void open() {
        isOpened = true;
    }

    public void close() {
        isOpened = false;
    }

    public boolean hasActivity() {
        return hasActivity;
    }

    public void setActivity(boolean hasActivity) {
        this.hasActivity = hasActivity;
    }

    public enum LocationType {
        ALLY,
        BLESSING,
        COMMON_ITEM,
        CLUE,
        MONEY,
        SANITY,
        SKILL,
        SPELL,
        STAMINA,
        UNIQUE_ITEM
    }
}
