package arkham.mechanics;

import sanchopansa.list.CircularLinkedList;
import java.util.ArrayList;

/**
 * This class represents a game instance and contains most of its features: players, monsters, 
 * open gates, and various mechanisms created to control these parameters.
 * @author SanchoPansa
 */

public class ArkhamGame {
	private int playerCount;
	private CircularLinkedList<Investigator> playersList;
	private AncientOne ancientOne = new AncientOne("Азатот");
	
	private int gateCount = 0;
	private int mapMonsterCount = 0;
	private int outMonsterCount = 0;
	private int gateLimit;
	private int monsterLimit;
	private int outskirtsLimit;
	private int terrorLevel = 0;
	private int doomLevel = 0;

	// Flags and game states
	private boolean activeEnvironment = false;
	private boolean activeRumor = false;

	private boolean closedLocation = false;
	//private int monstersPerGate;
	
	// Victory conditions
	private int elderSignsOnMap = 0;
	
	/**
	 * Constructs an instance of Arkham Horror game
	 * @param players Number of players in instance of Game
	 */
	public ArkhamGame(int players)
	{
		setPlayersList(players);
	}

	public ArkhamGame(int players, AncientOne ancientOne) {
		setPlayersList(players);
		this.ancientOne = ancientOne;
	}
	/**
	 * Returns the number of players in game
	 * @return Number of players
	 */
	public int getPlayersList()
	{
		return playerCount;
	}
	
	/**
	 * Sets the number of players. Depending on number of player sets gate and monster limits
	 * There can be from 1 to 8 players. Any other value will throw an error
	 * @param playersList Number of Players
	 * @throws IllegalArgumentException If player count is not between 1 and 8
	 */
	public void setPlayersList(int playersList)
	{
		if(playersList <= 0 || playersList > 8)
			throw new IllegalArgumentException("Incorrect number of players");
		this.playerCount = playersList;
		
		this.monsterLimit = this.playerCount + 3;
		this.outskirtsLimit = 8 - this.playerCount;
		this.gateLimit = 9 - (int) (Math.ceil((this.playerCount) / 2.0));
	}
	
	/**
	 * This function initializes Investigators by their names and then puts instances of 
	 * Investigator class into circular linked list
	 * @param names Array of Investigator's names
	 */
	public void setInvestigators(ArrayList<String> names)
	{
		playersList = new CircularLinkedList<>();
		for(String x : names)
		{
			playersList.push(new Investigator(x));
		}
	}

	/**
	 * Returns Ancient One of this game.
	 * @return AncientOne class instance
	 */
	public AncientOne getAncientOne()
	{
		return this.ancientOne;
	}
	
	/**
	 * Initializes instance of AncientOne class by its name put as argument of function
	 * @param name Name of Ancient One
	 */
	public void setAncientOne(String name)
	{
		this.ancientOne = new AncientOne(name);
	}

	/**
	 * Sets Ancient One by instance of class AncientOne
	 * @param ancientOne instance of AncientOne class
	 */
	public void setAncientOne(AncientOne ancientOne)
	{
		this.ancientOne = ancientOne;
	}
	
	/**
	 * Returns circular linked list of investigators
	 * @return CircularLinkedList<Investigator>
	 */
	public CircularLinkedList<Investigator> getCList()
	{
		return this.playersList;
	}
	
	/**
	 * Returns current terror level in Arkham
	 * @return Terror level as number
	 */
	public int getTerrorLevel() {
		return this.terrorLevel;
	}

	/**
	 * Returns Gate limit in this game. It depends on number of players
	 * @return Gate limit for current game
	 */
	public int getGateLimit() {
		return gateLimit;
	}

	/**
	 * Returns monster limit in this game. It depends on number of players
	 * @return Capacity of main map for monsters
	 */
	public int getMonsterLimit() {
		return monsterLimit;
	}

	/**
	 * Returns number of monsters on main map
	 * @return Quantity of monsters on the main map
	 */
	public int getMapMonsterCount() {
		return mapMonsterCount;
	}

	/**
	 * Returns number of monsters in the Outskirts
	 * @return Number of monsters in the Outskirts location
	 */
	public int getOutMonsterCount()
	{
		return this.outMonsterCount;
	}
	
	/**
	 * Returns limit of monsters in the Outskirts. Depends on number of players
	 * @return Capacity of Outskirts
	 */
	public int getOutskirtsLimit() {
		return outskirtsLimit;
	}

	/**
	 * Returns current state of Doom track. If it exceeds monster Awakening, then the Ancient One is here.
	 * @return Number of Doom tokens
	 */
	public int getDoomLevel() {
		return doomLevel;
	}

	/**
	 * Returns current number of opened Gates
	 * @return Number of opened Gates on map
	 */
	public int getGateCount()
	{
		return this.gateCount;
	}
	
	/**
	 * Returns current number of Elder Signs on map. If number of the exceeds 6, game is won.
	 * @return Number of placed Elder Signs
	 */
	public int getElderSignsOnMap() {
		return elderSignsOnMap;
	}
	
	/**
	 * Checks condition for awakening of boss
	 * @return true, if doom track of game equals of exceeds doom track of Ancient One
	 */
	public boolean isAwaken()
	{
		return this.ancientOne.getAwakening() <= this.doomLevel;
	}

	public boolean isClosedLocation() {
		return closedLocation;
	}

	public void setClosedLocation(boolean closedLocation) {
		this.closedLocation = closedLocation;
	}

	/**
	 * Creates a Gate
	 * If there were no Gate, then it increments number of Gates and spawns 1-2 monsters
	 * If there was a Gate, then in initiates Monster Surge
	 * @param isThereGate true, if location already has a placed Gate
	 */
	public void createGate(boolean isThereGate)
	{
		if(isThereGate) // Monster Surge
			for(int i = 0; i < Integer.max(playerCount, gateCount); i++)
				spawnMonster();
		else
		{
			gateCount++;
			addDoom();
			if(playerCount > 4)
			{
				this.spawnMonster();
			}
			this.spawnMonster();
		}
	}
	
	/**
	 * This function emulates process of sealing of the Gate. If parameter is true 
	 * it is the Elder Sign is used to close the Gate.
	 * @param elderSign is Elder Sign item used?
	 */
	public void closeGate(boolean elderSign) {
		if(elderSign) {
			gateCount--;
			elderSignsOnMap++;
			doomLevel--;
		} else {
			gateCount--;
		}
	}
	
	/**
	 * Spawns a monster and observes, whether limit of monsters in Arkham or the Outskirts is breached.
	 */
	public void spawnMonster()
	{
		if(mapMonsterCount > monsterLimit) {
			if (outMonsterCount >= outskirtsLimit) {
				outMonsterCount = 0;
				addTerrorLevel();
			} else
				outMonsterCount++;
		}
		mapMonsterCount++;
	}
	
	/**
	 * Kill a monster from the main map
	 */
	public void killMonster()
	{
		this.mapMonsterCount--;
	}
	
	/**
	 * This function increments level of Terror in Arkham. If level is greater than 10, then it increments 
	 * Doom track.
	 */
	public void addTerrorLevel()
	{
		this.terrorLevel++;
		if(this.terrorLevel >= 10) {
			monsterLimit = Integer.MAX_VALUE;
			this.addDoom();
		}
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
		this.doomLevel++;
	}
	
	/**
	 * This function decrements Doom
	 */
	public void minusDoom()
	{
		if(this.doomLevel >= 0)
			this.doomLevel--;
	}

	public boolean isActiveEnvironment() {
		return activeEnvironment;
	}

	public void setActiveEnvironment(boolean activeEnvironment) {
		this.activeEnvironment = activeEnvironment;
	}

	public boolean isActiveRumor() {
		return activeRumor;
	}

	public void setActiveRumor(boolean activeRumor) {
		this.activeRumor = activeRumor;
	}

	/**
	 * Returns String with comprehensive information about current state of the game.
	 * This includes: Number of Gates, Number of Monsters both on Map and in Outskirts, Terror Track and collateral
	 * info about closed Locations, Doom Track, active Process and/or Rumor. Every piece of info is separated via
	 * return symbol.
	 * @return String with current game state description
	 */
	public String getState() {
		StringBuilder info = new StringBuilder();

		// Doom Track:
		info.append(String.format("Уровень безысходности: %d / %d\n", this.doomLevel, this.ancientOne.getAwakening()));
		info.append(String.format("Врат: %d / %d\n", this.gateCount, this.gateLimit));
		info.append(String.format("Монстров на карте: %d / %d\n", this.mapMonsterCount, this.monsterLimit));
		info.append(String.format("Монстров на Окраинах: %d / %d\n", this.outMonsterCount, this.outskirtsLimit));
		info.append(String.format("Уровень ужаса: %d / 10\n", this.terrorLevel));
		if(terrorLevel >= 3)
			info.append("Магазин закрыт\n");
		if(terrorLevel >= 6)
			info.append("Лавка древностей закрыта\n");
		if(terrorLevel >= 9)
			info.append("Старинная лавка волшбы закрыта\n");
		if(terrorLevel >= 10)
			info.append("Лимит монстров снят\n");
		if(this.activeEnvironment)
			info.append("Активный Процесс\n");
		if(this.activeRumor)
			info.append("Активный Слух\n");
		return info.toString();
	}

	@Override
	public String toString() {
		// TODO: Достаточное описание игры через toString()
		return "";
	}
}
