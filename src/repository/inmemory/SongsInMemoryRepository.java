package repository.inmemory;

import interfaces.ICrudRepository;
import model.album.Artist;
import model.album.Band;
import model.song.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SongsInMemoryRepository implements ICrudRepository<String, Song>
{
	private List<Song> songList;
	
	public SongsInMemoryRepository() throws ParseException
	{
		this.songList = populateSongs();
	}
	
	private List<Song> populateSongs() throws ParseException
	{
		ArtistsInMemoryRepository repo = new ArtistsInMemoryRepository();
		BandsInMemoryRepository repoBand = new BandsInMemoryRepository();
		Rap s1 = new Rap("Mercy", "Good", new SimpleDateFormat("dd.MM.yyyy").parse("06.06.2012"), repo.findByID("Kanye West"));
		Rap s2 = new Rap("Not Afraid", "Good", new SimpleDateFormat("dd.MM.yyyy").parse("11.09.2010"), repo.findByID("Eminem"));
		Pop s3 = new Pop("Don't Worry", "Good", new SimpleDateFormat("dd.MM.yyyy").parse("27.07.2017"), repo.findByID("Madcon"));
		Classical s4 = new Classical("Für Elise", "Top", new SimpleDateFormat("dd.MM.yyyy").parse("27.04.1810"), repo.findByID("Ludwig van Beethoven"));
		Rock s5 = new Rock("Immigrant Song", "Good", new SimpleDateFormat("dd.MM.yyyy").parse("01.02.1972"), repoBand.findByID("Led Zeppelin"));
		Rock s6 = new Rock("War Pigs", "Good", new SimpleDateFormat("dd.MM.yyyy").parse("01.10.2014"), repoBand.findByID("Black Sabbath"));
		Rock s7 = new Rock("Paranoid", "Top", new SimpleDateFormat("dd.MM.yyyy").parse("01.06.2012"), repoBand.findByID("Black Sabbath"));
		Rock s8 = new Rock("Iron Man", "Ok", new SimpleDateFormat("dd.MM.yyyy").parse("04.06.2015"), repoBand.findByID("Black Sabbath"));
		Rap s9 = new Rap("Without Me", "Good", new SimpleDateFormat("dd.MM.yyyy").parse("01.06.2012"), repo.findByID("Eminem"));
		Rap s10 = new Rap("Sing For The Moment", "Ok", new SimpleDateFormat("dd.MM.yyyy").parse("03.07.2012"), repo.findByID("Eminem"));
		Rap s11 = new Rap("Till I Collapse", "Top", new SimpleDateFormat("dd.MM.yyyy").parse("09.08.2012"), repo.findByID("Eminem"));
		s1.setRelatedSongs(Arrays.asList(s2, s3));
		s2.setRelatedSongs(Arrays.asList(s1, s9, s10, s11));
		s3.setRelatedSongs(Arrays.asList(s1, s2, s4));
		s4.setRelatedSongs(List.of(s3));
		s5.setRelatedSongs(Arrays.asList(s6, s7, s8));
		s6.setRelatedSongs(Arrays.asList(s5, s7, s8));
		s7.setRelatedSongs(Arrays.asList(s5, s6, s8));
		s8.setRelatedSongs(Arrays.asList(s5, s7, s6));
		s9.setRelatedSongs(Arrays.asList(s1, s2, s10, s11));
		s10.setRelatedSongs(Arrays.asList(s1, s2, s9, s11));
		s11.setRelatedSongs(Arrays.asList(s1, s2, s9, s10));
		
		return new ArrayList<>(Arrays.asList(s1, s2, s3, s4, s5, s6, s7, s8, s9, s10, s11));
	}
	
	@Override
	public boolean add(Song entity)
	{
		if (findByID(entity.getName()) == null) {
			this.songList.add(entity);
			return true;
		}
		return false;
	}
	
	@Override
	public boolean remove(Song entity)
	{
		if (findByID(entity.getName()) != null) {
			this.songList.remove(entity);
			return true;
		}
		return false;
	}
	
	@Override
	public Song update(String name, Song entity)
	{
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
		for (Song song : this.songList)
			if (song.getName().equals(name)) return song;
		return null;
	}
	
	@Override
	public List<Song> findAll()
	{
		return this.songList;
	}
	
	@Override
	public String toString()
	{
		StringBuilder endString = new StringBuilder();
		for (Song song : this.songList)
			endString.append(song.toString()).append("\n");
		return endString.toString();
	}
}
