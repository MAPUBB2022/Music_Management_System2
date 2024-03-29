package model.album;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Band
{
	private String name; //Unique
	private Date formationDate;
	private String origin;
	
	/**
	 * one-to-many
	 **/
	private List<Artist> artistList;
	
	public Band() {}
	
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
	
	@Override
	public String toString()
	{
		StringBuilder endString = new StringBuilder("Band: " + name + "\nDate of Formation: " + formationDate + "\nOrigin: " + origin + "\n");
		for (Artist artist : this.artistList)
			endString.append(artist.toString());
		return endString.append("\n").toString();
	}
}
