package io.sanchopansa.arkham.investigators;

import io.sanchopansa.arkham.AbstractGameElement;
import io.sanchopansa.arkham.Expansion;
import io.sanchopansa.arkham.Phase;
import io.sanchopansa.arkham.cards.*;
import io.sanchopansa.arkham.monsters.Gate;
import io.sanchopansa.arkham.monsters.Monster;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

/**
 * Класс для описания Сыщика
 *
 * @author SanchoPansa
 */
public class Investigator extends AbstractGameElement {
    // Основные характеристики
    private final String name;
    private final String title;
    private final Stat stamina;
    private final Stat sanity;
    private final Skill SS; // Speed-Sneak
    private final Skill FW; // Fight-Will
    private final Skill LL; // Lore-Luck
    private final Stat focus;
    private final InvestigatorAbility ability;
    private boolean alive = true;

    // Вторичные характеристики
    private Blessing blessing = Blessing.NONE; // Благословение
    private boolean hasRetainer = false;            // Гонорар
    private boolean hasLoan = false;                   // Ссуда
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
    private final Queue<Monster> monsterTrophies = new ArrayDeque<>();
    private final Queue<Gate> gateTrophies = new ArrayDeque<>();

    public Investigator(Expansion e,
                        String name,
                        String title,
                        int stamina,
                        int sanity,
                        int focus,
                        int minSpeed,
                        int maxSneak,
                        int minFight,
                        int maxWill,
                        int minLore,
                        int maxLuck,
                        String abilityName,
                        String abilityDescription,
                        Phase abilityPhase

    ) {
        super(e);
        this.name = name;
        this.title = title;
        this.stamina = new Stat(stamina);
        this.sanity = new Stat(sanity);
        this.focus = new Stat(focus);
        this.SS = new Skill(minSpeed, maxSneak);
        this.FW = new Skill(minFight, maxWill);
        this.LL = new Skill(minLore, maxLuck);
        this.ability = new InvestigatorAbility(this, abilityName, abilityDescription, abilityPhase);
    }

    public Investigator(String name,
                        String title,
                        int stamina,
                        int sanity,
                        int focus,
                        int minSpeed,
                        int maxSneak,
                        int minFight,
                        int maxWill,
                        int minLore,
                        int maxLuck,
                        String abilityName,
                        String abilityDescription,
                        Phase abilityPhase
    ) {
        super(Expansion.VANILLA);
        this.name = name;
        this.title = title;
        this.stamina = new Stat(stamina);
        this.sanity = new Stat(sanity);
        this.focus = new Stat(focus);
        this.SS = new Skill(minSpeed, maxSneak, this.focus);
        this.FW = new Skill(minFight, maxWill, this.focus);
        this.LL = new Skill(minLore, maxLuck, this.focus);
        this.ability = new InvestigatorAbility(this, abilityName, abilityDescription, abilityPhase);
    }

    public String getName() {
        return name;
    }

    public String getTitle() {
        return title;
    }

    public Stat getStamina() {
        return stamina;
    }

    public Stat getSanity() {
        return sanity;
    }

    public InvestigatorAbility getAbility() {
        return ability;
    }

    public Skill getSS() {
        return SS;
    }

    public Skill getFW() {
        return FW;
    }

    public Skill getLL() {
        return LL;
    }

    public int getSpeed() {
        return this.SS.getBlueValue();
    }

    public int getSneak() {
        return this.SS.getRedValue();
    }

    public int getForce() {
        return this.FW.getBlueValue();
    }

    public int getWill() {
        return this.FW.getRedValue();
    }

    public int getLore() {
        return this.LL.getBlueValue();
    }

    public int getLuck() {
        return this.LL.getRedValue();
    }

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    public void addMoney(int money) {
        this.money += money;
    }

    public int getClueTokens() {
        return clueTokens;
    }

    public void setClueTokens(int clueTokens) {
        this.clueTokens = clueTokens;
    }

    public void addClueTokens (int clueTokens) {
        this.clueTokens += clueTokens;
    }

    public boolean hasRetainer() {
        return this.hasRetainer;
    }

    public void setRetainer(boolean flag) {
        this.hasRetainer = flag;
    }

    public void discardRetain() {
        this.hasRetainer = false;
    }

    public boolean hasLoan() {
        return this.hasLoan;
    }

    public void setLoan(boolean flag) {
        this.hasLoan = flag;
    }

    public boolean canLoan() {
        return this.canLoan;
    }

    public void returnLoan() {
        this.hasLoan = false;
    }

    public void discardLoan() {
        this.hasLoan = false;
        this.canLoan = false;
    }

    public boolean isBlessed() {
        return this.blessing == Blessing.BLESSED;
    }

    public boolean isCursed() {
        return this.blessing == Blessing.CURSED;
    }

    public boolean isSheriff() {
        return this.sheriff;
    }

    public void setSheriff(boolean flag) {
        this.sheriff = flag;
    }

    public boolean isInSilverTwilight() {
        return this.silverTwilight;
    }

    public void bless() {
        this.blessing = this.blessing == Blessing.CURSED ? Blessing.NONE : Blessing.BLESSED;
    }

    public void curse() {
        this.blessing = this.blessing == Blessing.BLESSED ? Blessing.NONE : Blessing.CURSED;
    }

    public void joinSilverTwilight() {
        this.silverTwilight = true;
    }

    public void leaveSilverTwilight() {
        this.silverTwilight = false;
    }

    public Stat getFocus() {
        return focus;
    }

    public void refillFocus() {
        this.focus.refill();
    }

    public List<CommonItem> getCommonItems() {
        return commonItems;
    }

    public void addCommonItem(CommonItem item) {
        this.commonItems.add(item);
    }

    public boolean removeCommonItem(CommonItem item) {
        return this.commonItems.remove(item);
    }

    public List<UniqueItem> getUniqueItems() {
        return uniqueItems;
    }

    public void addUniqueItem(UniqueItem item) {
        this.uniqueItems.add(item);
    }

    public boolean removeUniqueItem(UniqueItem item) {
        return this.uniqueItems.remove(item);
    }

    public List<Spell> getSpells() {
        return spells;
    }

    public void addSpell(Spell spell) {
        this.spells.add(spell);
    }

    public boolean removeSpell(Spell spell) {
        return this.spells.remove(spell);
    }

    public List<SkillCard> getSkills() {
        return skills;
    }

    public void addSkill(SkillCard skill) {
        this.skills.add(skill);
    }

    public boolean removeSkill(SkillCard skill) {
        return this.skills.remove(skill);
    }

    public List<Ally> getAllies() {
        return allies;
    }

    public void addAlly(Ally ally) {
        this.allies.add(ally);
    }

    public boolean removeAlly(Ally ally) {
        return this.allies.remove(ally);
    }

    public List<AbstractCard> getSpecials() {
        return specials;
    }

    public void addSpecials(AbstractCard card) {
        this.specials.add(card);
    }

    public boolean removeSpecials(AbstractCard card) {
        return this.specials.remove(card);
    }

    public Queue<Monster> getMonsterTrophies() {
        return monsterTrophies;
    }

    public void addMonsterTrophy(Monster monster) {
        this.monsterTrophies.add(monster);
    }

    public boolean removeMonsterTrophy(Monster monster) {
        return this.monsterTrophies.remove(monster);
    }

    public Queue<Gate> getGateTrophies() {
        return gateTrophies;
    }

    public void addGateTrophy(Gate gate) {
        this.gateTrophies.add(gate);
    }

    public boolean removeGateTrophy(Gate gate) {
        return this.gateTrophies.remove(gate);
    }

    public boolean isAlive() {
        return this.alive;
    }

    public void kill() {
        this.alive = false;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Investigator {");
        sb.append("\n\tname='").append(name).append('\'');
        sb.append(",\n\ttitle='").append(title).append('\'');
        sb.append(",\n\texpansion=").append(expansion);
        sb.append(",\n\tstamina=").append(stamina);
        sb.append(",\n\tsanity=").append(sanity);
        sb.append(",\n\tSneak/Speed=").append(SS);
        sb.append(",\n\tFight/Will=").append(FW);
        sb.append(",\n\tLore/Luck=").append(LL);
        sb.append(",\n\tfocus=").append(focus);
        sb.append(",\n\tability=").append(ability);
        sb.append(",\n\tblessing=").append(blessing);
        sb.append(",\n\thas retainer=").append(hasRetainer);
        sb.append(",\n\thas loan=").append(hasLoan);
        sb.append(",\n\tcan loan=").append(canLoan);
        sb.append(",\n\tis cultist=").append(silverTwilight);
        sb.append(",\n\tis sheriff=").append(sheriff);
        sb.append(",\n\tmoney=").append(money);
        sb.append(",\n\tclue tokens=").append(clueTokens);
        sb.append(",\n\tcommon items=").append(commonItems);
        sb.append(",\n\tunique items=").append(uniqueItems);
        sb.append(",\n\tspells=").append(spells);
        sb.append(",\n\tskills=").append(skills);
        sb.append(",\n\tallies=").append(allies);
        sb.append(",\n\tspecials=").append(specials);
        sb.append(",\n\tmonster trophies=").append(monsterTrophies);
        sb.append(",\n\tgate trophies=").append(gateTrophies);
        sb.append("\n}");
        return sb.toString();
    }

    /**
     * Троичное состояние Благословения у игрока
     */
    private enum Blessing {
        NONE,    // Нет эффекта
        BLESSED, // Благословлен
        CURSED   // Проклят
    }

    public record InvestigatorAbility(Investigator investigator, String name, String description, Phase phase) {
        @Override
        public String toString() {
            return "InvestigatorAbility {" +
                    "\t\nname='" + name + '\'' +
                    ",\t\ndescription='" + description + '\'' +
                    ",\t\nphase=" + phase +
                    "\n}";
        }
    }


}
