package io.sanchopansa.arkham.common;

public enum Expansion {
    VANILLA("Ужас Аркхэма"),
    CotDP("Проклятие Тёмного Фараона"), // Curse of the Dark Pharaoh
    DUNWICH("Ужас Данвича"),
    TKiY("Король в Жёлтом"), // The King in Yellow
    KINGSPORT("Ужас Кингспорта"),
    INNSMOUTH("Ужас Иннсмута"),
    MISKATONIC("Ужас Мискатоника"),
    TBGotW("Чёрная Коза Чащоб"), // The Black Goat of the Woods
    TLatT("Затаившийся у Порога"); // The Lurker at the Threshold

    private final String expansionName;

    Expansion(String name) {
        this.expansionName = name;
    }

    public String getExpansionName() {
        return expansionName;
    }

    public static Expansion fromString(String name) {
        for(Expansion e: Expansion.values()) {
            if(name.equalsIgnoreCase(e.expansionName)) {
                return e;
            }
        }
        return Expansion.VANILLA;
    }
}
