package model.album;

public class Artist
{
	private String name;
	private String stage_name;
	private Float salary;
	
	public Artist(String stage_name)
	{
		this.stage_name = stage_name;
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
	
	public String getStage_name()
	{
		return stage_name;
	}
	
	public void setStage_name(String stage_name)
	{
		this.stage_name = stage_name;
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
		return "Artist: " + name + "\nSalary: " + salary + "\n";
	}

}
