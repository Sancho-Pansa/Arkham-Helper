package arkham.mechanics;

import sql.AccessDBConnect;

/**
 * This class describes the main monster - one of the Ancient Ones
 * @author SanchoPansa
 */

public class AncientOne
{
	private String name;
	private byte awakening;
	private String followers;
	private String featName;
	private String featDesc;
	
	
	private final AccessDBConnect ACCDB = new AccessDBConnect();  
	
	public AncientOne(String name)
	{
		this.setName(name);
	}
	

	public void setName(String name)
	{
		this.name = name;
		this.awakening = new Byte(ACCDB.getAncientField("Awakening", name));
		this.followers = ACCDB.getAncientField("FollowerBonuses", name);
		this.featDesc = ACCDB.getAncientField("FeatureDesc", name);
		this.featName = ACCDB.getAncientField("FeatureName", name);
	}
	
	public String getName() 
	{
		return name;
	}


	public byte getAwakening() 
	{
		return awakening;
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

}
