package repository.inmemory;

import interfaces.ICrudRepository;
import model.album.Artist;
import model.album.Band;
import model.label.MusicLabel;
import model.song.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.SimpleTimeZone;

public class SongsInMemoryRepository implements ICrudRepository<String, Song>
{
	private List<Song> songList;
	
	public SongsInMemoryRepository() throws ParseException
	{
		this.songList = populateSongs();
	}
	
	private List<Song> populateSongs() throws ParseException
	{
		Rap s1 = new Rap("Mercy", "Good", new SimpleDateFormat("dd.MM.yyyy").parse("06.06.2012"), new Artist("Kanye West"));
		Rap s2 = new Rap("Not Afraid", "Good", new SimpleDateFormat("dd.MM.yyyy").parse("11.09.2010"), new Artist("Eminem"));
		Pop s3 = new Pop("Don't Worry", "Good", new SimpleDateFormat("dd.MM.yyyy").parse("27.07.2017"), new Artist("Madcon"));
		Pop s4 = new Pop("Raisa", "Good", new SimpleDateFormat("dd.MM.yyyy").parse("01.07.2008"), new Band("Fly Project"));
		Classical s5 = new Classical("Für Elise", "Top", new SimpleDateFormat("dd.MM.yyyy").parse("27.04.1810"), new Artist("Ludwig van Beethoven"));
		Rock s6 = new Rock("Immigrant Song", "Good", new SimpleDateFormat("dd.MM.yyyy").parse("01.02.1972"), new Band("Led Zeppelin"));
		Rock s7 = new Rock("War Pigs", "Good", new SimpleDateFormat("dd.MM.yyyy").parse("01.10.2014"), new Band("Black Sabbath"));
		return new ArrayList<>(Arrays.asList(s1, s2, s3, s4, s5, s6, s7));
	}
	
	@Override
	public boolean add(Song entity)
	{
		if(!this.songList.contains(entity)) {
			this.songList.add(entity);
			return true;
		}
		return false;
	}
	
	@Override
	public boolean remove(Song entity)
	{
		if(findByID(entity.getName()) != null) {
			this.songList.remove(entity);
			return true;
		}
		return false;
	}
	
	@Override
	public Song update(String name, Song entity) {
		Song song = findByID(name);
		if (song != null) {
			this.songList.set(this.songList.indexOf(song), entity);
			return song;
		}
		return null;
	}

	@Override
	public Song findByID(String name)
	{
		for(Song song: songList){
			if(song.getName().equals(name))
				return song;
		}
		return null;
	}
	
	@Override
	public List<Song> findAll()
	{
		if (!songList.isEmpty())
			return this.songList;
		return null;
	}
}
