package model.song;

import model.album.Artist;
import model.album.Band;

import java.util.Date;

public class Pop extends Song
{
	public Pop(){};

	public Pop(String name, float rating, Date releaseDate, Artist artist)
	{
		super(name, rating, releaseDate, artist);
	}
	
	public Pop(String name, float rating, Date releaseDate, Band band_singers)
	{
		super(name, rating, releaseDate, band_singers);
	}
}
