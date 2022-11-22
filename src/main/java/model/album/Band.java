package model.album;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Band
{
	private String name;
	private Date formationDate;
	private String origin;
	private List<Artist> artistList;
	
	public Band(String name)
	{
		this.name = name;
		this.formationDate = null;
		this.origin = "";
		this.artistList = new ArrayList<>();
	}
	
	public String getName()
	{
		return name;
	}
	
	public void setName(String name)
	{
		this.name = name;
	}
	
	public Date getFormationDate()
	{
		return formationDate;
	}
	
	public void setFormationDate(Date formationDate)
	{
		this.formationDate = formationDate;
	}
	
	public String getOrigin()
	{
		return origin;
	}
	
	public void setOrigin(String origin)
	{
		this.origin = origin;
	}
	
	public List<Artist> getArtistList()
	{
		return artistList;
	}
	
	public void setArtistList(List<Artist> artistList)
	{
		this.artistList = artistList;
	}
}
