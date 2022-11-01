package model.song;


import model.album.Artist;
import model.album.Band;

import java.util.Date;
import java.util.List;

public abstract class Song
{
	private String name;
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
		this.tags = null;
		this.lyrics = null;
		this.relatedSongs = null;
		this.streamCount = null;
	}
	
	public Song(String name, String rating, Date releaseDate, Band band_singers)
	{
		this.name = name;
		this.rating = rating;
		this.releaseDate = releaseDate;
		this.band_singers = band_singers;
		this.singer = null;
		this.tags = null;
		this.lyrics = null;
		this.relatedSongs = null;
		this.streamCount = null;
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
	
	public Band getBand_singers()
	{
		return band_singers;
	}
	
	public void setBand_singers(Band band_singers)
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
		return "Music{" + "name='" + name + '\'' + ", rating='" + rating + '\'' + ", apparitionDate=" + releaseDate + '}';
	}
}
