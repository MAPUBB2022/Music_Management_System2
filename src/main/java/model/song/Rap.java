package model.song;

import model.album.Artist;
import model.album.Band;

import java.util.Date;

public class Rap extends Song
{
	public Rap(String name, Float rating, Date releaseDate, Artist artist)
	{
		super(name, rating, releaseDate, artist);
	}
	
	public Rap(String name, Float rating, Date releaseDate, Band band_singers)
	{
		super(name, rating, releaseDate, band_singers);
	}
	
	@Override
	public String toString()
	{
		return super.toString();
	}
}
