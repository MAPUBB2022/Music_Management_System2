package model.album;

public class Artist
{
	private String name;
	private String stageName; //Unique
	private Float salary;
	
	public Artist(String stageName)
	{
		this.stageName = stageName;
		this.name = "";
		this.salary = 0f;
	}
	
	public String getName()
	{
		return name;
	}
	
	public void setName(String name)
	{
		this.name = name;
	}
	
	public String getStageName()
	{
		return stageName;
	}
	
	public void setStageName(String stageName)
	{
		this.stageName = stageName;
	}
	
	public Float getSalary()
	{
		return salary;
	}
	
	public void setSalary(Float salary)
	{
		this.salary = salary;
	}
	
	@Override
	public String toString()
	{
		return "Artist: " + stageName + " - " + name + "\nSalary: " + salary + "\n";
	}
}
