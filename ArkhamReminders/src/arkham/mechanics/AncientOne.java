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

	public AncientOne(String name, int awakening, Set<AncientTypes> types) {
		this.name = name;
		this.awakening = awakening;
		this.types = types;
	}

	public AncientOne(String name,
					  int awakening,
					  Set<AncientTypes> types,
					  String followers,
					  String featName,
					  String featDesc) {
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

	public void setFollowers(String followers) {
		this.followers = followers;
	}

	public void setFeatName(String featName) {
		this.featName = featName;
	}

	public void setFeatDesc(String featDesc) {
		this.featDesc = featDesc;
	}
}
