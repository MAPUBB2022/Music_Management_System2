package model.album;

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
		this.origin = null;
		this.artistList = null;
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
		StringBuilder str = new StringBuilder("Band - " + name + "\nFormation Date: " + formationDate + "\nOrigin: " + origin);
		if (artistList.size() > 0) {
			str.append("\nList of Artists: ");
			for (Artist artist : artistList)
				str.append(artist.toString()).append(" ");
		}
		return str.toString();
	}
}
