package repository.inmemory;

import interfaces.ICrudRepository;
import model.song.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SongsInMemoryRepository implements ICrudRepository<String, Song>
{
	private final List<Song> songList;
	
	public SongsInMemoryRepository() throws ParseException
	{
		this.songList = populateSongs();
	}
	
	private List<Song> populateSongs() throws ParseException
	{
		ArtistsInMemoryRepository repo = new ArtistsInMemoryRepository();
		BandsInMemoryRepository repoBand = new BandsInMemoryRepository();
		Rap s1 = new Rap("Mercy", 7.5f, new SimpleDateFormat("dd.MM.yyyy").parse("06.06.2012"), repo.findByID("Kanye West"));
		Rap s2 = new Rap("Not Afraid", 6f, new SimpleDateFormat("dd.MM.yyyy").parse("11.09.2010"), repo.findByID("Eminem"));
		Pop s3 = new Pop("Don't Worry", 9f, new SimpleDateFormat("dd.MM.yyyy").parse("27.07.2017"), repo.findByID("Madcon"));
		Classical s4 = new Classical("Für Elise", 9.5f, new SimpleDateFormat("dd.MM.yyyy").parse("27.04.1810"), repo.findByID("Ludwig van Beethoven"));
		Rock s5 = new Rock("Immigrant Song", 7f, new SimpleDateFormat("dd.MM.yyyy").parse("01.02.1972"), repoBand.findByID("Led Zeppelin"));
		Rock s6 = new Rock("War Pigs", 8.25f, new SimpleDateFormat("dd.MM.yyyy").parse("01.10.2014"), repoBand.findByID("Black Sabbath"));
		Rock s7 = new Rock("Paranoid", 9f, new SimpleDateFormat("dd.MM.yyyy").parse("01.06.2012"), repoBand.findByID("Black Sabbath"));
		Rock s8 = new Rock("Iron Man", 7f, new SimpleDateFormat("dd.MM.yyyy").parse("04.06.2015"), repoBand.findByID("Black Sabbath"));
		Rap s9 = new Rap("Without Me", 9.25f, new SimpleDateFormat("dd.MM.yyyy").parse("01.06.2012"), repo.findByID("Eminem"));
		Rap s10 = new Rap("Sing For The Moment", 9f, new SimpleDateFormat("dd.MM.yyyy").parse("03.07.2012"), repo.findByID("Eminem"));
		Rap s11 = new Rap("Till I Collapse", 9.4f, new SimpleDateFormat("dd.MM.yyyy").parse("09.08.2012"), repo.findByID("Eminem"));
		
		s1.setRelatedSongs(Arrays.asList(s2, s3));
		s1.setStreamCount(5000000);
		s2.setRelatedSongs(Arrays.asList(s1, s9, s10, s11));
		s2.setStreamCount(8000000);
		s3.setRelatedSongs(Arrays.asList(s1, s2, s4));
		s3.setStreamCount(2648200);
		s4.setRelatedSongs(List.of(s3));
		s4.setStreamCount(16381000);
		s5.setRelatedSongs(Arrays.asList(s6, s7, s8));
		s5.setStreamCount(29374010);
		s6.setRelatedSongs(Arrays.asList(s5, s7, s8));
		s6.setStreamCount(397204180);
		s7.setRelatedSongs(Arrays.asList(s5, s6, s8));
		s7.setStreamCount(284916301);
		s8.setRelatedSongs(Arrays.asList(s5, s6, s7));
		s8.setStreamCount(27300);
		s9.setRelatedSongs(Arrays.asList(s1, s2, s10, s11));
		s9.setStreamCount(381539);
		s10.setRelatedSongs(Arrays.asList(s1, s2, s9, s11));
		s10.setStreamCount(73652001);
		s11.setRelatedSongs(Arrays.asList(s1, s2, s9, s10));
		s11.setStreamCount(8163682);
		
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
		if (this.songList == null) return "";
		StringBuilder endString = new StringBuilder();
		for (Song song : this.songList)
			endString.append(song.toString()).append("\n");
		return endString.toString();
	}
}
