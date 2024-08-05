package io.sanchopansa.arkham.mechanics.monsters;

import io.sanchopansa.arkham.mechanics.AbstractGameElement;
import io.sanchopansa.arkham.mechanics.Expansion;

import java.util.Objects;
import java.util.Set;

/**
 * Класс, описывающий Древнего
 * @author SanchoPansa
 */

public class AncientOne extends AbstractGameElement
{
	private String name;
	private final int awakening;
	private Set<AncientTypes> types;
	private Monster followers;
	private String featName;
	private String featDesc;

	public AncientOne(String name, int awakening, Set<AncientTypes> types, Expansion expansion) {
        super(expansion);
        this.name = name;
		this.awakening = awakening;
		this.types = types;
	}

	public AncientOne(String name,
					  int awakening,
					  Set<AncientTypes> types,
					  Monster followers,
					  String featName,
					  String featDesc,
					  Expansion expansion) {
        super(expansion);
        this.name = name;
		this.awakening = awakening;
		this.types = types;
		this.followers = followers;
		this.featName = featName;
		this.featDesc = featDesc;
	}

	public void setName(String name)
	{
		this.name = name;
	}
	
	public String getName() 
	{
		return name;
	}


	public int getAwakening()
	{
		return awakening;
	}

	public Set<AncientTypes> getTypes() {
		return types;
	}

	public void setTypes(Set<AncientTypes> types) {
		this.types = types;
	}

	public Monster getFollowers()
	{
		return followers;
	}


	public String getFeatName() {
		return featName;
	}


	public String getFeatDesc() {
		return featDesc;
	}

	public void setFollowers(Monster followers) {
		this.followers = followers;
	}

	public void setFeatName(String featName) {
		this.featName = featName;
	}

	public void setFeatDesc(String featDesc) {
		this.featDesc = featDesc;
	}

	@Override
	public String toString() {
		StringBuilder sBuilder = new StringBuilder();
		sBuilder
				.append(getClass().getName())
				.append("@")
				.append(Integer.toHexString(hashCode()))
				.append("\n")
				.append("Name: ")
				.append(this.name)
				.append("\nDoom track: ")
				.append(this.awakening)
				.append("\nTypes: ")
				.append(this.types.toString())
				.append("\nFollowers: ")
				.append(this.followers)
				.append("\nFeature name: ")
				.append(this.featName)
				.append("\nFeature description: ")
				.append(this.featDesc);
		return sBuilder.toString();
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		AncientOne that = (AncientOne) o;
		return awakening == that.awakening &&
				name.equals(that.name);
	}

	@Override
	public int hashCode() {
		return Objects.hash(name, awakening);
	}

	public enum AncientTypes {
		NONE,
		SPAWN,
		INVESTIGATOR_RELATED,
		MONSTER_RELATED,
		GATE_RELATED,
		MAP_RELATED,
		ENVIRONMENT_RELATED
	}
}
