package mechanics;

import sql.AccessDBConnect;

/**
 * This class describes a single investigator (representative of Player in the world of Arkham Horror)
 * @author SanchoPansa
 *
 */
public class Investigator 
{
	//NB: Эта переменная static final, но поля внутри неё могут меняться (если им не прописать final).
	public final static AccessDBConnect ACCDB = new AccessDBConnect();
	
	private final String name;
	private int maxHealth;
	private int maxSanity;
	private final int initMoney;
	private byte blessing = 0;
	private boolean killed = false;
	
	private boolean retain = false;
	private boolean loan = false;
	private boolean canLoan = true;
	private boolean silverTwilight = false;
	private boolean sheriff = false;
	
	private int health;
	private int sanity;
	private int money;
	
	public Investigator(String name)
	{
		this.name = name;
		this.maxHealth = new Integer(ACCDB.getInvestField("Health", name));
		this.health = this.maxHealth;
		this.maxSanity = new Integer(ACCDB.getInvestField("Sanity", name));
		this.sanity = this.maxSanity;
		this.initMoney = new Integer(ACCDB.getInvestField("InitMoney", name));
		this.money = this.initMoney;
	}
	
	public int getHealth() {
		return health;
	}

	public void setHealth(int health) {
		this.health = health;
	}

	public int getSanity() {
		return sanity;
	}

	public void setSanity(int sanity) {
		this.sanity = sanity;
	}

	public int getMoney() {
		return money;
	}

	public void setMoney(int money) {
		this.money = money;
	}

	public String getName() {
		return name;
	}

	public int getMaxHealth() {
		return maxHealth;
	}

	public int getMaxSanity() {
		return maxSanity;
	}

	public int getInitMoney() {
		return initMoney;
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
		return this.blessing == 1;
	}
	
	public boolean isCursed()
	{
		return this.blessing == -1;
	}
	
	public boolean isKilled()
	{
		return this.killed;
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
	
	public boolean isTwilight()
	{
		return this.silverTwilight;
	}
	
	public void bless()
	{
		if(this.blessing < 1)
			this.blessing++;
	}
	
	public void curse()
	{
		if(this.blessing > -1)
			this.blessing--;
	}
	
	public void setTwilight()
	{
		this.silverTwilight = true;
	}
	
	public void damage()
	{
		if(this.health > 0)
			this.health--;
	}
	
	public void heal()
	{
		if(this.health < this.maxHealth)
			this.health++;
	}
	
	public void addSanity()
	{
		if(this.sanity < this.maxSanity)
			this.sanity++;
	}
	
	public void minusSanity()
	{
		if(this.sanity > 0)
			this.sanity--;
	}
	
	public void minusMaxHealth()
	{
		if(this.maxHealth > 0)
			this.maxHealth--;
		if(this.health > this.maxHealth)
			this.health = this.maxHealth;
	}
	
	public void minusMaxSanity()
	{
		if(this.maxSanity > 0)
			this.maxSanity--;
		if(this.maxSanity < this.sanity)
			this.sanity = this.maxSanity;
	}
	
	public void discardRetain()
	{
		this.retain = false;
	}
	
	public void discardLoan()
	{
		this.loan = false;
		this.canLoan = false;
	}
	
	public void killInvest()
	{
		this.killed = true;
	}
}
