package io.sanchopansa.arkham;

import io.sanchopansa.arkham.investigators.Investigator;
import io.sanchopansa.arkham.monsters.AncientOne;
import io.sanchopansa.arkham.monsters.Gate;

import java.util.ArrayDeque;
import java.util.List;
import java.util.Queue;

/**
 * Этот класс описывает состояние партии.
 * @author SanchoPansa
 */

public class Game {
	private final List<Investigator> playersList;
	private final AncientOne ancientOne;
	private final Queue<Gate> gates;
	
	private int gateCount = 0;
	private int mapMonsterCount = 0;
	private int outMonsterCount = 0;
	private int gateLimit;
	private int monsterLimit;
	private int outskirtsLimit;
	private int terrorLevel = 0;
	private int doomLevel = 0;

	// Флажки состояний игры
	private boolean activeEnvironment = false;
	private boolean activeRumor = false;

	private boolean closedLocation = false;
	//private int monstersPerGate;
	
	// Условия победы
	private int elderSignsOnMap = 0;

	public Game(List<Investigator> players, AncientOne ancientOne) {
		this.playersList = players;
		this.ancientOne = ancientOne;
		this.gates = new ArrayDeque<>(); // STUB
	}

	public List<Investigator> getPlayersList()
	{
		return playersList;
	}

	public AncientOne getAncientOne()
	{
		return this.ancientOne;
	}
	
	public int getTerrorLevel() {
		return this.terrorLevel;
	}

	public int getGateLimit() {
		return gateLimit;
	}

	public int getMonsterLimit() {
		return monsterLimit;
	}

	public int getMapMonsterCount() {
		return mapMonsterCount;
	}

	public int getOutMonsterCount()
	{
		return this.outMonsterCount;
	}
	
	public int getOutskirtsLimit() {
		return outskirtsLimit;
	}

	public int getDoomLevel() {
		return doomLevel;
	}

	public int getGateCount()
	{
		return this.gateCount;
	}
	
	public int getElderSignsOnMap() {
		return elderSignsOnMap;
	}
	
	public boolean isAwaken()
	{
		return this.ancientOne.getAwakening() <= this.doomLevel;
	}

	/**
	 * Создает врата на карте.
	 * Если
	 * If there was a Gate, then in initiates Monster Surge
	 * @param isThereGate true, if location already has a placed Gate
	 */
	public void createGate(boolean isThereGate)
	{
		int players = this.playersList.size();
		if(isThereGate) // Monster Surge
			for(int i = 0; i < Integer.max(players, gateCount); i++)
				spawnMonster();
		else
		{
			gateCount++;
			addDoom();
			if(players > 4)
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
			if(outMonsterCount >= outskirtsLimit) {
				outMonsterCount = 0;
				addTerrorLevel();
			} else
				outMonsterCount++;
		}
		mapMonsterCount++;
	}
	
	/**
	 * Kill a monster in the main map
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
