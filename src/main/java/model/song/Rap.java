package model.song;

import model.album.Artist;
import model.album.Band;

import java.util.Date;

public class Rap extends Song
{
	public Rap(String name, String rating, Date releaseDate, Artist artist)
	{
		super(name, Float.parseFloat(rating), releaseDate, artist);
	}
	
	public Rap(String name, String rating, Date releaseDate, Band band_singers)
	{
		super(name, Float.parseFloat(rating), releaseDate, band_singers);
	}
}
