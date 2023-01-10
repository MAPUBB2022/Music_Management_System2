package model.song;


import model.album.Artist;
import model.album.Band;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Song
{
	private String name; //Unique
	private Float rating;
	private Date releaseDate;
	private Artist singer;
	private Band bandSingers;
	private String lyrics;
	
	/**
	 * many-to-many
	 **/
	private List<Song> relatedSongs;
	
	private Integer streamCount;
	
	public Song() {}
	
	public Song(String name, Float rating, Date releaseDate, Artist artist)
	{
		this.name = name;
		this.rating = rating;
		this.releaseDate = releaseDate;
		this.singer = artist;
		this.bandSingers = null;
		this.lyrics = "";
		this.relatedSongs = new ArrayList<>();
		this.streamCount = 0;
	}
	
	public Song(String name, Float rating, Date releaseDate, Band bandSingers)
	{
		this.name = name;
		this.rating = rating;
		this.releaseDate = releaseDate;
		this.bandSingers = bandSingers;
		this.singer = null;
		this.lyrics = "";
		this.relatedSongs = new ArrayList<>();
		this.streamCount = 0;
	}
	
	public String getName()
	{
		return name;
	}
	
	public void setName(String name)
	{
		this.name = name;
	}
	
	public Float getRating()
	{
		return rating;
	}
	
	public void setRating(Float rating)
	{
		this.rating = rating;
	}
	
	public Date getReleaseDate()
	{
		return releaseDate;
	}
	
	public void setReleaseDate(Date releaseDate)
	{
		this.releaseDate = releaseDate;
	}
	
	public Artist getSinger()
	{
		return singer;
	}
	
	public void setSinger(Artist singer)
	{
		this.singer = singer;
	}
	
	public Band getBandSingers()
	{
		return bandSingers;
	}
	
	public void setBandSingers(Band band_singers)
	{
		this.bandSingers = band_singers;
	}
	
	public String getLyrics()
	{
		return lyrics;
	}
	
	public void setLyrics(String lyrics)
	{
		this.lyrics = lyrics;
	}
	
	public List<Song> getRelatedSongs()
	{
		return relatedSongs;
	}
	
	public void setRelatedSongs(List<Song> relatedSongs)
	{
		this.relatedSongs = relatedSongs;
	}
	
	public Integer getStreamCount()
	{
		return streamCount;
	}
	
	public void setStreamCount(Integer streamCount)
	{
		this.streamCount = streamCount;
	}
	
	@Override
	public String toString()
	{
		String related = "";
		if (this.relatedSongs != null) {
			for (Song song : this.relatedSongs)
				//noinspection StringConcatenationInLoop
				related += song.name + "; ";
		}
		StringBuilder endString = new StringBuilder(name + " by ");
		if (this.singer != null) endString.append(this.singer.getStageName());
		else endString.append(this.bandSingers.getName());
		endString.append("\nRating: ").append(rating).append("\nRelease Date: ").append(releaseDate).append("\nStream Count: ").append(streamCount).append("\nLyrics: ").append(lyrics).append("\nRelated Songs: ").append(related);
		return endString.append("\n").toString();
	}
}
