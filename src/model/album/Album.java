package model.album;

import model.song.Song;
import interfaces.Profitable;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Album implements Profitable
{
	private String title;
	private Artist artist;
	private Band band;
	private List<Song> songList;
	private String language;
	private Float productionCost;
	private Date releaseDate;
	private Integer copiesSold;
	private Float discPrice;
	
	public Album(String title, Artist artist)
	{
		this.title = title;
		this.artist = artist;
		this.band = null;
		this.songList = new ArrayList<>();
		this.language = "";
		this.productionCost = 0f;
		this.releaseDate = null;
		this.copiesSold = 0;
		this.discPrice = 0f;
	}
	
	public Album(String title, Band band)
	{
		this.title = title;
		this.artist = null;
		this.band = band;
		this.songList = new ArrayList<>();
		this.language = "";
		this.productionCost = 0f;
		this.releaseDate = null;
		this.copiesSold = 0;
		this.discPrice = 0f;
	}
	
	public String getTitle()
	{
		return title;
	}
	
	public void setTitle(String title)
	{
		this.title = title;
	}
	
	public Artist getArtist()
	{
		return artist;
	}
	
	public void setArtist(Artist artist)
	{
		this.artist = artist;
	}
	
	public Band getBand()
	{
		return band;
	}
	
	public void setBand(Band band)
	{
		this.band = band;
	}
	
	public List<Song> getSongList()
	{
		return songList;
	}
	
	public void setSongList(List<Song> songList)
	{
		this.songList = songList;
	}
	
	public String getLanguage()
	{
		return language;
	}
	
	public void setLanguage(String language)
	{
		this.language = language;
	}
	
	public Float getProductionCost()
	{
		return productionCost;
	}
	
	public void setProductionCost(Float productionCost)
	{
		this.productionCost = productionCost;
	}
	
	public Date getReleaseDate()
	{
		return releaseDate;
	}
	
	public void setReleaseDate(Date releaseDate)
	{
		this.releaseDate = releaseDate;
	}
	
	public Integer getCopiesSold()
	{
		return copiesSold;
	}
	
	public void setCopiesSold(Integer copiesSold)
	{
		this.copiesSold = copiesSold;
	}
	
	public Float getDiscPrice()
	{
		return discPrice;
	}
	
	public void setDiscPrice(Float discPrice)
	{
		this.discPrice = discPrice;
	}
	
	public void addSong(Song song)
	{
		this.songList.add(song);
	}
	
	@Override
	public float calculateProfit()
	{
		return copiesSold * discPrice;
	}
	
	@Override
	public String toString()
	{
		String singer = this.artist != null ? this.artist.getName() : this.band.getName();
		StringBuilder endString = new StringBuilder("Album: " + title + " by " + singer + "\nLanguage: " + language + "\nRelease Date: " + releaseDate + "\nCopies Sold: " + copiesSold + "\nProduction Cost: " + productionCost + "\nPrice per Disc" + discPrice + "\n");
		for (Song song : this.songList)
			endString.append(song.getName()).append("; ");
		return endString.append("\n").toString();
	}
}

