package arkham.mechanics;

import java.util.Set;

/**
 * This class describes the main monster - one of the Ancient Ones
 * @author SanchoPansa
 */

public class AncientOne
{
	private String name;
	private final int awakening;
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

	@Override
	public String toString() {
		StringBuilder sBuilder = new StringBuilder();
		sBuilder
				.append(getClass().getName())
				.append("@")
				.append(Integer.toHexString(hashCode()))
				.append(this.hashCode())
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
}
