package repository.inmemory;

import interfaces.ICrudRepository;
import model.album.Album;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class AlbumsInMemoryRepository implements ICrudRepository<String, Album>
{
	private final List<Album> inMemoryAlbums;
	
	public AlbumsInMemoryRepository() throws ParseException
	{
		this.inMemoryAlbums = populateAlbums();
	}
	
	private List<Album> populateAlbums() throws ParseException
	{
		BandsInMemoryRepository bands = new BandsInMemoryRepository();
		ArtistsInMemoryRepository artists = new ArtistsInMemoryRepository();
		SongsInMemoryRepository songs = new SongsInMemoryRepository();
		
		Album a1 = new Album("Paranoid", bands.findByID("Black Sabbath"));
		a1.setCopiesSold(500000);
		a1.setLanguage("English");
		a1.setDiscPrice(6.5f);
		a1.setProductionCost(800000f);
		a1.setReleaseDate(new SimpleDateFormat("dd.MM.yyyy").parse("10.01.2008"));
		a1.addSong(songs.findByID("War Pigs"));
		a1.addSong(songs.findByID("Paranoid"));
		a1.addSong(songs.findByID("Iron Man"));
		
		Album a2 = new Album("The Eminem Show", artists.findByID("Eminem"));
		a2.setCopiesSold(554000);
		a2.setLanguage("English");
		a2.setDiscPrice(8.5f);
		a2.setProductionCost(895000f);
		a2.setReleaseDate(new SimpleDateFormat("dd.MM.yyyy").parse("10.03.2010"));
		a2.addSong(songs.findByID("Without Me"));
		a2.addSong(songs.findByID("Sing For The Moment"));
		a2.addSong(songs.findByID("Till I Collapse"));
		
		return new ArrayList<>(Arrays.asList(a1, a2));
	}
	
	@Override
	public boolean add(Album entity)
	{
		if (findByID(entity.getTitle()) == null) {
			this.inMemoryAlbums.add(entity);
			return true;
		}
		return false;
	}
	
	@Override
	public boolean remove(Album entity)
	{
		if (findByID(entity.getTitle()) != null) {
			this.inMemoryAlbums.remove(entity);
			return true;
		}
		return false;
	}
	
	@Override
	public Album update(String name, Album entity)
	{
		Album album = findByID(name);
		if (album != null) {
			this.inMemoryAlbums.set(this.inMemoryAlbums.indexOf(album), entity);
			return album;
		}
		return null;
	}
	
	@Override
	public Album findByID(String name)
	{
		for (Album album : this.inMemoryAlbums)
			if (album.getTitle().equals(name)) return album;
		return null;
	}
	
	@Override
	public List<Album> findAll()
	{
		return this.inMemoryAlbums;
	}
	
	@Override
	public String toString()
	{
		if (this.inMemoryAlbums == null)
			return "";
		StringBuilder endString = new StringBuilder();
		for (Album album : this.inMemoryAlbums)
			endString.append(album.toString()).append("\n");
		return endString.toString();
	}
}
