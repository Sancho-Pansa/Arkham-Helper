package io.sanchopansa.arkham.mechanics;

/**
 * Класс для описания Сыщика
 * @author SanchoPansa
 *
 */
public class Investigator extends AbstractGameElement
{
	// Основные характеристики
	private final String name;     // Имя
	private Statistic stamina;     // Здоровье
	private Statistic sanity;      // Рассудок
	private Statistic money;       // Капитал
	private boolean alive = true;  // Статус: Жив?

	// Вторичные характеристики
	private Blessing blessing = Blessing.NO_EFFECT; // Благословение
	private boolean retain = false;                 // Гонорар
	private boolean loan = false;                   // Ссуда
	private boolean canLoan = true;                 // Возможность брать ссуду
	private boolean silverTwilight = false;         // Членство в ЛСС
	private boolean sheriff = false;                // Статус шерифа

	
	public Investigator(String name, Expansion e)
	{
		super(e);
		this.name = name;
	}

	public Statistic getStamina() {
		return stamina;
	}

	public Statistic getSanity() {
		return sanity;
	}

	public Statistic getMoney() {
		return money;
	}

	public String getName() {
		return name;
	}
	
	public void setRetain()
	{
		this.retain = true;
	}
	
	public void setLoan()
	{
		this.loan = true;
	}
	
	public void setSheriff()
	{
		this.sheriff = true;
	}

	public boolean isBlessed()
	{
		return this.blessing == Blessing.BLESSED;
	}
	
	public boolean isCursed()
	{
		return this.blessing == Blessing.CURSED;
	}
	
	public boolean isAlive()
	{
		return this.alive;
	}
	
	public boolean isSheriff()
	{
		return this.sheriff;
	}
	
	public boolean hasRetain()
	{
		return this.retain;
	}
	
	public boolean hasLoan()
	{
		return this.loan;
	}
	
	public boolean canLoan()
	{
		return this.canLoan;
	}
	
	public boolean isInSilverTwilight()
	{
		return this.silverTwilight;
	}

	// Получить благословение
	public void bless()
	{
		this.blessing = this.blessing == Blessing.CURSED ? Blessing.NO_EFFECT : Blessing.BLESSED;
	}

	// Получить проклятие
	public void curse()
	{
		this.blessing = this.blessing == Blessing.BLESSED ? Blessing.NO_EFFECT : Blessing.CURSED;
	}
	
	public void joinSilverTwilight()
	{
		this.silverTwilight = true;
	}
	
	public void discardRetain()
	{
		this.retain = false;
	}

	public void returnLoan() { this.loan = false; }
	
	public void discardLoan()
	{
		this.loan = false;
		this.canLoan = false;
	}
	
	public void kill()
	{
		this.alive = false;
	}
}
