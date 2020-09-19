package arkham.mechanics;

import java.util.ArrayList;
import list.CircularLinkedList;

/**
 * This class represents a game instance and contains most of its features: players, monsters, 
 * open gates, and various mechanisms created to control these parameters.
 * @author SanchoPansa
 *
 */

public class ArkhamGame
{
	private int players;
	private CircularLinkedList<Investigator> cll;
	private AncientOne ancientOne;
	
	private boolean AncientAwaken = false;
	private boolean mapLimitBreached = false;
	private boolean outskirtsLimitBreached = false;
	private boolean terrorRaised = false;
	
	private int clueNum = 5;
	private int gateNum = 0;
	private int monstersOnMap = 0;
	private int outskirtsMonsters = 0;
	private int gateLimit;
	private int monsterLimit;
	private int outskirtsLimit;
	private int terrorLevel = 0;
	private int doomTrack = 0;
	private int addedMonsters;
	
	// Victory conditions
	private int elderSignsOnMap = 0;
	
	/**
	 * Constructs an instance of Arkham Horror game.
	 * Non-zero and less than 9 player number is required
	 * @param players
	 */
	
	public ArkhamGame(int players)
	{
		setPlayers(players);
	}
	
	/**
	 * Returns the number of players in game
	 * @return
	 */
	public int getPlayers()
	{
		return players;
	}
	
	/**
	 * Sets the number of players. Note, there can be from 1 to 8 players, 
	 * any other number will be mistake and will throw exception
	 * @param players
	 * @throws IllegalArgumentException
	 */
	public void setPlayers(int players)
	{
		if(players <= 0 || players > 8)
			throw new IllegalArgumentException("Incorrect number of players");
		this.players = players;
		
		this.monsterLimit = this.players + 3;
		this.outskirtsLimit = 8 - this.players;
		this.gateLimit = 9 - (int) (Math.ceil((this.players) / 2.0));
	}
	
	/**
	 * This function initializes Investigators by their names and then puts instances of 
	 * Investigator class into circular linked list
	 * @param names
	 */
	public void setInvestigators(ArrayList<String> names)
	{
		this.cll = new CircularLinkedList<>();
		for(String x : names)
		{
			cll.push(new Investigator(x));
		}
		cll = cll.revert();
	}
	
	/**
	 * Initializes instance of AncientOne class by its name put as argument of function
	 * @param name
	 */
	public void setAncientOne(String name)
	{
		this.ancientOne = new AncientOne(name);
		if(name.equals("������"))
			this.clueNum = 8;
	}
	
	/**
	 * Sets an limit of monsters in outskirts to positive infinity (in Integer representation) 
	 * if terror level is 10 or more
	 */
	public void cancelOutskirtsLimit()
	{
		this.outskirtsLimit = (int) Double.POSITIVE_INFINITY;
	}
	
	/**
	 * Returns Ancient One of this game.
	 * @return
	 */
	public AncientOne getAncientOne()
	{
		return this.ancientOne;
	}
	
	/**
	 * Returns the number of clue investigator needs to seal the Gate. It changes, if Ancient One 
	 * is Hastur.
	 * @return
	 */
	public int getClueNum() {
		return clueNum;
	}
	
	/**
	 * Returns circular linked list of investigators
	 * @return CircularLinkedList<Investigator>
	 */
	public CircularLinkedList<Investigator> getCList()
	{
		return this.cll;
	}
	
	/**
	 * Returns current terror level in Arkham
	 * @return
	 */
	public int getTerrorLevel() {
		return this.terrorLevel;
	}

	/**
	 * Returns Gate limit in this game. It depends on number of players
	 * @return
	 */
	public int getGateLimit() {
		return gateLimit;
	}

	/**
	 * Returns monster limit in this game. It depends on number of players
	 * @return
	 */
	public int getMonsterLimit() {
		return monsterLimit;
	}

	/**
	 * Returns
	 * @return
	 */
	public int getMonstersOnMap() {
		return monstersOnMap;
	}

	/**
	 * Returns number of monsters on the outskirts
	 * @return
	 */
	public int getMonsteronOutskirts()
	{
		return this.outskirtsMonsters;
	}
	
	/**
	 * Returns number of monsters, which were created by Monster surge
	 * @return
	 */
	public int getAddedMonsters() {
		return addedMonsters;
	}

	/**
	 * Returns limit of monsters in the Outskirts. Depends on number of players
	 * @return
	 */
	public int getOutskirtsLimit() {
		return outskirtsLimit;
	}

	/**
	 * Returns current state of Doom track. If it exceeds monster Awakening, then the Ancient One is here.
	 * @return
	 */
	public int getDoomTrack() {
		return doomTrack;
	}

	/**
	 * Returns current number of opened Gates
	 * @return
	 */
	public int getGateNum()
	{
		return this.gateNum;
	}
	
	/**
	 * Returns current number of Elder Signs on map. If number of the exceeds 6, game is won.
	 * @return
	 */
	public int getElderSignsOnMap() {
		return elderSignsOnMap;
	}
	
	/**
	 * Returns true, if number of monsters in Arkham exceeds appropriate limit
	 * @return
	 */
	public boolean isMapLimit()
	{
		if(this.mapLimitBreached)
		{
			this.mapLimitBreached = false;
			return true;
		}
		else
			return this.mapLimitBreached;
	}
	
	/**
	 * Returns true, if number of monsters in the Outskirts exceeds relevant limit
	 * @return
	 */
	public boolean isOutskirtsLimit()
	{
		if(this.outskirtsLimitBreached)
		{
			this.outskirtsLimitBreached = false;
			return true;
		}
		return this.outskirtsLimitBreached;
	}
	
	/**
	 * Returns true, if level of terror raised
	 * @return
	 */
	public boolean isTerrorRaised()
	{
		if(this.terrorRaised)
		{
			this.terrorRaised = false;
			return true;
		}
		return this.terrorRaised;
	}
	
	/**
	 * Returns true, if Doom track reaches specific for every Ancient One number, which mean, 
	 * that Mythos is awakened.
	 * @return
	 */
	public boolean isAwaken()
	{
		return this.AncientAwaken;
	}

	/**
	 * Emulates process of creating Gate in Arkham. 
	 * If there were no Gate, then it increments number of Gates and spawns 1-2 monsters
	 * If there was a Gate, then in initiates Monster Surge
	 * @param isThereGate
	 */
	public void createGate(boolean isThereGate)
	{
		this.addedMonsters = 0;
		if(isThereGate)
			for(int i = 0; i < players || i < this.gateNum; i++)
			{
				this.addedMonsters++;
				this.spawnMonster();
			}
		else
		{
			this.gateNum++;
			if(gateNum > this.gateLimit)
				this.AncientAwaken = true;
			if(players > 4)
			{
				this.spawnMonster();
			}
			this.spawnMonster();
		}
	}
	
	/**
	 * This function decrements number of Gates on map (unless it is more than zero)
	 */
	public void closeGate()
	{
		if(this.gateNum > 0)
			this.gateNum--;
	}
	
	/**
	 * This function emulates process of sealing of the Gate. If parameter is true 
	 * it is the Elder Sign is used to close the Gate.
	 * @param hasElderSign
	 */
	public void sealGate(boolean hasElderSign)
	{
		if(hasElderSign)
		{
			this.gateNum--;
			this.elderSignsOnMap++;
			this.doomTrack--;
		}
		else
		{
			this.gateNum--;
			this.elderSignsOnMap++;
		}
	}
	
	/**
	 * This function spawns a monster and observes, whether limit of monsters in Arkham or the Outskirts is 
	 * breached.
	 */
	public void spawnMonster()
	{
		this.addedMonsters = 0;
		if(this.monsterLimit > this.monstersOnMap)
			this.monstersOnMap++;
		if(this.monstersOnMap > this.monsterLimit)
		{
			this.mapLimitBreached = true;
			this.addOutskirtsMonster();
		}
		System.out.println(this.monstersOnMap);
	}
	
	/**
	 * This function kill monster in Arkham (monsters in Outskirts are untargetable)
	 */
	public void killMonster()
	{
		this.monstersOnMap--;
	}
	
	/**
	 * Increments number of monsters in Outskirts area, unless their number exceeds corresponding limit
	 */
	public void addOutskirtsMonster()
	{
		this.outskirtsMonsters++;
		if(this.outskirtsMonsters > this.outskirtsLimit && this.terrorLevel < 10)
		{
			this.outskirtsMonsters = 0;
			this.addTerrorLevel();
			this.outskirtsLimitBreached = true;
			System.out.println("Limit breached");
		}
	}
	
	/**
	 * This function decrements number of monsters in Outskirts. It is used if Flying monster 
	 * is returning to the Arkham
	 */
	public void minusOutskirtsMonster()
	{
		this.outskirtsMonsters--;
	}
	
	/**
	 * This function increments level of Terror in Arkham. If level is greater than 10, then it increments 
	 * Doom track.
	 */
	public void addTerrorLevel()
	{
		this.terrorLevel++;
		this.terrorRaised = true;
		if(this.terrorLevel >= 10)
			this.addDoom();
	}
	
	/**
	 * This function decrements terror level (which happens quite rarely)
	 */
	public void minusTerrorLevel()
	{
		this.terrorLevel--;
	}
	
	/**
	 * This function increments Doom on Doom track
	 */
	public void addDoom()
	{
		this.doomTrack++;
		if(this.doomTrack >= this.ancientOne.getAwakening())
			this.AncientAwaken = true;
	}
	
	/**
	 * This function decrements Doom
	 */
	public void minusDoom()
	{
		if(this.doomTrack >= 0)
			this.doomTrack--;
	}
}
