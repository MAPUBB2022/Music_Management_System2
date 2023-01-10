package repository.inmemory;

import interfaces.ICrudRepository;
import model.label.MusicLabel;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MusicLabelsInMemoryRepository implements ICrudRepository<String, MusicLabel>
{
	private List<MusicLabel> musicLabelList;
	
	public MusicLabelsInMemoryRepository()
	{
		this.musicLabelList = populateMusicLabels();
	}
	
	private List<MusicLabel> populateMusicLabels()
	{
		ArtistsInMemoryRepository repo = new ArtistsInMemoryRepository();
		
		MusicLabel m1 = new MusicLabel("Sony Music Entertainment", "New York City, New York");
		m1.setRevenue(2500000f);
		m1.setArtistList(Arrays.asList(repo.findByID("Eminem"), repo.findByID("50 Cent"), repo.findByID("Kanye West")));
		
		MusicLabel m2 = new MusicLabel("Warner Music Group", "New York City, New York");
		m2.setRevenue(2001000f);
		m2.setArtistList(Arrays.asList(repo.findByID("Travis Scott"), repo.findByID("Pitbull"), repo.findByID("Macklemore")));
		
		MusicLabel m3 = new MusicLabel("Universal Music Group", "Hilversum, Netherlands");
		m3.setRevenue(350000f);
		m3.setArtistList(Arrays.asList(repo.findByID("Kid Cudi"), repo.findByID("Madcon")));
		
		MusicLabel m4 = new MusicLabel("Independent Music Labels", "New York City, New York");
		m4.setRevenue(8495000f);
		m4.setArtistList(Arrays.asList(repo.findByID("Lil Wayne"), repo.findByID("Maroon 5")));
		
		MusicLabel m5 = new MusicLabel("Atlantic Records", "New York City, New York");
		m5.setRevenue(2948200f);
		m5.setArtistList(Arrays.asList(repo.findByID("Daft Punk"), repo.findByID("Sean Paul")));
		
		MusicLabel m6 = new MusicLabel("Virgin Records", "Los Angeles, California");
		m6.setRevenue(360000f);
		m6.setArtistList(Arrays.asList(repo.findByID("Ludwig van Beethoven"), repo.findByID("Wolfgang Amadeus Mozart")));
		
		MusicLabel m7 = new MusicLabel("Interscope", "Santa Monica, California");
		m7.setRevenue(5809300f);
		m7.setArtistList(Arrays.asList(repo.findByID("Mick Jagger"), repo.findByID("Keith Richards"), repo.findByID("Ron Wood")));
		
		MusicLabel m8 = new MusicLabel("RCA Records", "New York City, New York");
		m8.setRevenue(5039625f);
		m8.setArtistList(Arrays.asList(repo.findByID("Robert Plant"), repo.findByID("John Bonham"), repo.findByID("Jimmy Page"), repo.findByID("John Paul Jones")));
		
		return new ArrayList<>(Arrays.asList(m1, m2, m3, m4, m5, m6, m7, m8));
	}
	
	@Override
	public boolean add(MusicLabel entity)
	{
		if (findByID(entity.getName()) == null) {
			this.musicLabelList.add(entity);
			return true;
		}
		return false;
	}
	
	@Override
	public boolean remove(MusicLabel entity)
	{
		if (findByID(entity.getName()) != null) {
			this.musicLabelList.remove(entity);
			return true;
		}
		return false;
	}
	
	@Override
	public MusicLabel update(String name, MusicLabel entity)
	{
		MusicLabel musicLabel = findByID(name);
		if (musicLabel != null) {
			this.musicLabelList.set(this.musicLabelList.indexOf(musicLabel), entity);
			return musicLabel;
		}
		return null;
	}
	
	@Override
	public MusicLabel findByID(String name)
	{
		for (MusicLabel musicLabel : this.musicLabelList)
			if (musicLabel.getName().equals(name)) return musicLabel;
		return null;
	}
	
	@Override
	public List<MusicLabel> findAll()
	{
		return this.musicLabelList;
	}
	
	@Override
	public String toString()
	{
		if (this.musicLabelList == null) return "";
		StringBuilder endString = new StringBuilder();
		for (MusicLabel musicLabel : this.musicLabelList)
			endString.append(musicLabel.toString()).append("\n");
		return endString.toString();
	}
}
