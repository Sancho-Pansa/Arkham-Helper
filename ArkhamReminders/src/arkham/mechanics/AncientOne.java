package arkham.mechanics;

import java.util.HashSet;
import java.util.Set;

/**
 * This class describes the main monster - one of the Ancient Ones
 * @author SanchoPansa
 */

public class AncientOne
{
	private String name;
	private int awakening;
	private Set<AncientTypes> types;
	private String followers;
	private String featName;
	private String featDesc;
	
	
	public AncientOne(String name)
	{
		this.setName(name);
	}

	public AncientOne(String name, int awakening, Set<AncientTypes> types) {
		this.name = name;
		this.awakening = awakening;
		this.types = types;
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

	public String getFollowers()
	{
		return followers;
	}


	public String getFeatName() {
		return featName;
	}


	public String getFeatDesc() {
		return featDesc;
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
