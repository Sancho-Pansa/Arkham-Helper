package arkham.mechanics;

/**
 * This class describes the main monster - one of the Ancient Ones
 * @author SanchoPansa
 */

public class AncientOne
{
	private String name;
	private int awakening = 16;
	private String followers;
	private String featName;
	private String featDesc;
	
	
	public AncientOne(String name)
	{
		this.setName(name);
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
