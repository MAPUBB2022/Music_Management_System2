package model.song;

import model.album.Artist;
import model.album.Band;

import java.util.Date;

public class Rock extends Song
{
	public Rock(String name, Float rating, Date releaseDate, Artist artist)
	{
		super(name, rating, releaseDate, artist);
	}
	
	public Rock(String name, Float rating, Date releaseDate, Band band_singers)
	{
		super(name, rating, releaseDate, band_singers);
	}
	
	@Override
	public String toString()
	{
		return super.toString();
	}
}
