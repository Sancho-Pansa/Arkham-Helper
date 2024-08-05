package io.sanchopansa.arkham.mechanics.investigators;

import io.sanchopansa.arkham.mechanics.AbstractGameElement;
import io.sanchopansa.arkham.mechanics.Expansion;
import io.sanchopansa.arkham.mechanics.cards.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Класс для описания Сыщика
 *
 * @author SanchoPansa
 */
public class Investigator extends AbstractGameElement {
    // Основные характеристики
    private final String name;
    private final String title;
    private final Statistic stamina;
    private final Statistic sanity;
    private final Skill SS; // Speed-Sneak
    private final Skill FW; // Fight-Will
    private final Skill LL; // Lore-Luck
    private final int focus;
    private boolean alive = true;

    // Вторичные характеристики
    private Blessing blessing = Blessing.NO_EFFECT; // Благословение
    private boolean hasRetainer = false;            // Гонорар
    private boolean loan = false;                   // Ссуда
    private boolean canLoan = true;                 // Возможность брать ссуду
    private boolean silverTwilight = false;         // Членство в ЛСС
    private boolean sheriff = false;                // Статус шерифа

    // Имущество
    private int money = 0;
    private int clueTokens = 0;
    private final List<CommonItem> commonItems = new ArrayList<>();
    private final List<UniqueItem> uniqueItems = new ArrayList<>();
    private final List<Spell> spells = new ArrayList<>();
    private final List<SkillCard> skills = new ArrayList<>();
    private final List<Ally> allies = new ArrayList<>();
    private final List<AbstractCard> specials = new ArrayList<>();

    public Investigator(Expansion e,
                        String name,
                        String title,
                        int stamina,
                        int sanity,
                        int minSpeed,
                        int maxSneak,
                        int minFight,
                        int maxWill,
                        int minLore,
                        int maxLuck,
                        int focus
    ) {
        super(e);
        this.name = name;
        this.title = title;
        this.stamina = new Statistic(stamina);
        this.sanity = new Statistic(sanity);
        this.SS = new Skill(minSpeed, maxSneak);
        this.FW = new Skill(minFight, maxWill);
        this.LL = new Skill(minLore, maxLuck);
        this.focus = focus;
    }

    public String getName() {
        return name;
    }

    public Statistic getStamina() {
        return stamina;
    }

    public Statistic getSanity() {
        return sanity;
    }

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    public int getClueTokens() {
        return clueTokens;
    }

    public void setClueTokens(int clueTokens) {
        this.clueTokens = clueTokens;
    }

    public void setRetain() {
        this.hasRetainer = true;
    }

    public void setLoan() {
        this.loan = true;
    }

    public void setSheriff() {
        this.sheriff = true;
    }

    public boolean isBlessed() {
        return this.blessing == Blessing.BLESSED;
    }

    public boolean isCursed() {
        return this.blessing == Blessing.CURSED;
    }

    public boolean isAlive() {
        return this.alive;
    }

    public boolean isSheriff() {
        return this.sheriff;
    }

    public boolean hasRetain() {
        return this.hasRetainer;
    }

    public boolean hasLoan() {
        return this.loan;
    }

    public boolean canLoan() {
        return this.canLoan;
    }

    public boolean isInSilverTwilight() {
        return this.silverTwilight;
    }

    // Получить благословение
    public void bless() {
        this.blessing = this.blessing == Blessing.CURSED ? Blessing.NO_EFFECT : Blessing.BLESSED;
    }

    // Получить проклятие
    public void curse() {
        this.blessing = this.blessing == Blessing.BLESSED ? Blessing.NO_EFFECT : Blessing.CURSED;
    }

    public void joinSilverTwilight() {
        this.silverTwilight = true;
    }

    public void discardRetain() {
        this.hasRetainer = false;
    }

    public void returnLoan() {
        this.loan = false;
    }

    public void discardLoan() {
        this.loan = false;
        this.canLoan = false;
    }

    public int getFocus() {
        return focus;
    }

    public List<CommonItem> getCommonItems() {
        return commonItems;
    }

    public List<UniqueItem> getUniqueItems() {
        return uniqueItems;
    }

    public List<Spell> getSpells() {
        return spells;
    }

    public List<SkillCard> getSkills() {
        return skills;
    }

    public List<Ally> getAllies() {
        return allies;
    }

    public List<AbstractCard> getSpecials() {
        return specials;
    }

    public void kill() {
        this.alive = false;
    }

    /**
     * Троичное состояние Благословения у игрока
     */
    private enum Blessing {
        NO_EFFECT,    // Нет эффекта
        BLESSED, // Благословлен
        CURSED   // Проклят
    }

    /**
     * Заготовка для класса, описывающего способность Сыщика
     *
     * @param name
     * @param description
     */
    public record Ability(String name, String description) {
    }
}
