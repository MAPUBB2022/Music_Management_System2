package model.label;

import model.album.Album;
import model.album.Artist;
import model.concert.Concert;

import java.util.ArrayList;
import java.util.List;

public class MusicLabel
{
	private String name;
	private String address;
	private Float revenue;
	
	/** many-to-many **/
	private List<Artist> artistList;
	
	/** one-to-many **/
	private List<Album> albumList;
	
	/** one-to-many **/
	private List<Concert> upcomingEvents;
	
	public MusicLabel(String name, String address)
	{
		this.name = name; //Unique
		this.address = address;
		this.revenue = 0f;
		this.artistList = new ArrayList<>();
		this.albumList = new ArrayList<>();
		this.upcomingEvents = new ArrayList<>();
	}
	
	public String getName()
	{
		return name;
	}
	
	public void setName(String name)
	{
		this.name = name;
	}
	
	public String getAddress()
	{
		return address;
	}
	
	public void setAddress(String address)
	{
		this.address = address;
	}
	
	public Float getRevenue()
	{
		return revenue;
	}
	
	public void setRevenue(Float revenue)
	{
		this.revenue = revenue;
	}
	
	public List<Artist> getArtistList()
	{
		return artistList;
	}
	
	public void setArtistList(List<Artist> artistList)
	{
		this.artistList = artistList;
	}
	
	public List<Album> getAlbumList()
	{
		return albumList;
	}
	
	public void setAlbumList(List<Album> albumList)
	{
		this.albumList = albumList;
	}
	
	public List<Concert> getUpcomingEvents()
	{
		return upcomingEvents;
	}
	
	public void setUpcomingEvents(List<Concert> upcomingEvents)
	{
		this.upcomingEvents = upcomingEvents;
	}
	
	@Override
	public String toString()
	{
		String artists = this.artistList.toString();
		String albums = this.albumList.toString();
		String concerts = this.upcomingEvents.toString();
		return "Music Label: " + name + "\nAddress: " + address + "\nRevenue: " + revenue + "\nArtists: " + artists + "\nAlbums: " + albums + "\nUpcoming Events: " + concerts + "\n";
	}
}
