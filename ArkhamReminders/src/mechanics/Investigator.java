package mechanics;

/**
 * This class describes a single investigator (representative of Player in the world of Arkham Horror)
 * @author SanchoPansa
 *
 */
public class Investigator 
{
	// Основные характеристики
	private final String name;     // Имя
	private Statistic stamina;     // Максимальное Здоровье
	private Statistic sanity;      // Максимальный Рассудок
	private Statistic money;       // Капитал
	private boolean alive = true;  // Статус: Жив?

	// Вторичные характеристики
	private Blessing blessing = Blessing.NO_EFFECT; // Благословение
	private boolean retain = false;                 // Гонорар
	private boolean loan = false;                   // Ссуда
	private boolean canLoan = true;                 // Возможность брать ссуду
	private boolean silverTwilight = false;         // Членство в ЛСС
	private boolean sheriff = false;                // Статус шерифа

	
	public Investigator(String name)
	{
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
	
	public void killInvest()
	{
		this.alive = true;
	}

	/**
	 * Обозначает объект характеристики Сыщика и операции с ними
	 */
	public class Statistic {

		private int current;
		private int maximum;

		Statistic(int initialValue) {
			maximum = initialValue;
			current = maximum;
		}

		public int getCurrent() {
			return current;
		}

		public void setCurrent(int current) {
			this.current = current;
		}

		public int getMaximum() {
			return maximum;
		}

		public void setMaximum(int maximum) {
			this.maximum = maximum;
		}

		// Математическое суммирование. Текущее значение не может превысить максимум или быть меньше нуля
		public void add(int value) {
			current += value;
			if(current >= maximum)
				current = maximum;
			if(current < 0)
				current = 0;
		}

		@Override
		public String toString() {
			return String.format("%d / %d", current, maximum);
		}
	}
}
