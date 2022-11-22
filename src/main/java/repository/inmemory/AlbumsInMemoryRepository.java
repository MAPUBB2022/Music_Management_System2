package repository.inmemory;

import interfaces.ICrudRepository;
import model.album.Album;
import model.album.Artist;
import model.album.Band;
import model.song.Rap;
import model.song.Rock;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class AlbumsInMemoryRepository implements ICrudRepository<String, Album>
{
	private List<Album> inMemoryAlbums;
	
	public AlbumsInMemoryRepository() throws ParseException
	{
		this.inMemoryAlbums = populateAlbums();
	}
	
	private List<Album> populateAlbums() throws ParseException
	{
		Band band = new Band("Black Sabbath");
		Album a1 = new Album("Paranoid", band);
		a1.addSong(new Rock("War Pigs", "", new SimpleDateFormat("dd.MM.yyyy").parse("01.06.2012"), band));
		a1.addSong(new Rock("Paranoid", "", new SimpleDateFormat("dd.MM.yyyy").parse("02.06.2013"), band));
		a1.addSong(new Rock("Iron Man", "", new SimpleDateFormat("dd.MM.yyyy").parse("04.06.2015"), band));
		
		Artist artist = new Artist("Eminem");
		Album a2 = new Album("The Eminem Show", artist);
		a2.addSong(new Rap("Without Me", "", new SimpleDateFormat("dd.MM.yyyy").parse("01.06.2012"), artist));
		a2.addSong(new Rap("Sing For The Moment", "", new SimpleDateFormat("dd.MM.yyyy").parse("03.07.2012"), artist));
		a2.addSong(new Rap("Till I Collapse", "", new SimpleDateFormat("dd.MM.yyyy").parse("09.08.2012"), artist));
		
		return new ArrayList<>(Arrays.asList(a1, a2));
	}
	
	@Override
	public void add(Album entity)
	{
		if(findByID(entity.getTitle()) == null)
			this.inMemoryAlbums.add(entity);
	}
	
	@Override
	public void remove(Album entity)
	{
		if(findByID(entity.getTitle()) != null)
			this.inMemoryAlbums.remove(entity);
	}
	
	@Override
	public void update(String name, Album entity)
	{
		Album album = findByID(name);
		if(album != null)
			this.inMemoryAlbums.set(this.inMemoryAlbums.indexOf(album), entity);
	}
	
	@Override
	public Album findByID(String name)
	{
		for(Album album: inMemoryAlbums){
			if(album.getTitle().equals(name))
				return album;
		}
		return null;
	}
	
	@Override
	public List<Album> findAll()
	{
		return this.inMemoryAlbums;
	}
}
