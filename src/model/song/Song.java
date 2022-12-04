package model.song;


import model.album.Artist;
import model.album.Band;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public abstract class Song
{
	private String name; //Unique
	private String rating;
	private Date releaseDate;
	private Artist singer;
	private Band band_singers;
	
	private List<Tags> tags;
	
	private String lyrics;
	
	private List<Song> relatedSongs;
	
	private Integer streamCount;
	
	public Song(String name, String rating, Date releaseDate, Artist artist)
	{
		this.name = name;
		this.rating = rating;
		this.releaseDate = releaseDate;
		this.singer = artist;
		this.band_singers = null;
		this.tags = new ArrayList<>();
		this.lyrics = "";
		this.relatedSongs = new ArrayList<>();
		this.streamCount = 0;
	}
	
	public Song(String name, String rating, Date releaseDate, Band bandSingers)
	{
		this.name = name;
		this.rating = rating;
		this.releaseDate = releaseDate;
		this.band_singers = bandSingers;
		this.singer = null;
		this.tags = new ArrayList<>();
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
	
	public String getRating()
	{
		return rating;
	}
	
	public void setRating(String rating)
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
		return band_singers;
	}
	
	public void setBandSingers(Band band_singers)
	{
		this.band_singers = band_singers;
	}
	
	public List<Tags> getTags()
	{
		return tags;
	}
	
	public void setTags(List<Tags> tags)
	{
		this.tags = tags;
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
		String tags = "";
		String related = "";
		for (Tags tag : this.tags)
			//noinspection StringConcatenationInLoop
			tags += tag + " ";
		related += relatedSongs.toString();
		StringBuilder endString = new StringBuilder(name + " by ");
		if (this.singer != null) endString.append(this.singer.getStageName());
		else endString.append(this.band_singers.getName());
		endString.append("\nRating: ").append(rating).append("\nTags: ").append(tags).append("\nRelease Date: ").append(releaseDate).append("\nStream Count: ").append(streamCount).append("\nLyrics: ").append(lyrics).append("\nRelated Songs: ").append(related);
		return endString.append("\n").toString();
	}
}
