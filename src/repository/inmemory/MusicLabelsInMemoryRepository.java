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
		MusicLabel m1 = new MusicLabel("Sony Music Entertainment", "New York City, New York");
		MusicLabel m2 = new MusicLabel("Warner Music Group", "New York City, New York");
		MusicLabel m3 = new MusicLabel("Universal Music Group", "Hilversum, Netherlands");
		MusicLabel m4 = new MusicLabel("Independent Music Labels", "New York City, New York");
		MusicLabel m5 = new MusicLabel("Atlantic Records", "New York City, New York");
		MusicLabel m6 = new MusicLabel("Virgin Records", "Los Angeles, California");
		MusicLabel m7 = new MusicLabel("Interscope", "Santa Monica, California");
		MusicLabel m8 = new MusicLabel("RCA Records", "New York City, New York");
		return new ArrayList<>(Arrays.asList(m1, m2, m3, m4, m5, m6, m7, m8));
	}
	
	@Override
	public void add(MusicLabel entity)
	{
		if (findByID(entity.getName()) == null) this.musicLabelList.add(entity);
	}
	
	@Override
	public void remove(MusicLabel entity)
	{
		if (findByID(entity.getName()) != null) this.musicLabelList.remove(entity);
	}
	
	@Override
	public void update(String name, MusicLabel entity)
	{
		MusicLabel musicLabel = findByID(name);
		if (musicLabel != null) this.musicLabelList.set(this.musicLabelList.indexOf(musicLabel), entity);
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
		StringBuilder endString = new StringBuilder();
		for (MusicLabel musicLabel : this.musicLabelList)
			endString.append(musicLabel.toString()).append("\n");
		return endString.toString();
	}
}
