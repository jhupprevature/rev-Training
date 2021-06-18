package fixtures;

public abstract class Fixture {
	protected String name;
	protected String shortDescription;
	protected String longDescription;

	public Fixture(String name, String shortDesc, String longDesc) {
		this.name = name;
		this.shortDescription = shortDesc;
		this.longDescription = longDesc;
	}

	public String getName() { return name; }
	public void setName(String name) { this.name = name; }

	public String getShortDescription() { return shortDescription; }
	public void setShortDescription(String shortDescription) {
		this.shortDescription = shortDescription;
	}

	public String getLongDescription() { return longDescription; }
	public void setLongDescription(String longDescription) { 
		this.longDescription = longDescription;
	}
	
	//TODO any other classes to be made from this one?
	//Any other functionality?
	
}