package repository.inmemory;

import interfaces.ICrudRepository;
import model.concert.Concert;
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
	public boolean add(MusicLabel entity)
	{
		if(!this.musicLabelList.contains(entity)) {
			this.musicLabelList.add(entity);
			return true;
		}
		return false;
	}
	
	@Override
	public boolean remove(MusicLabel entity)
	{
		if(findByID(entity.getName()) != null) {
			this.musicLabelList.remove(entity);
			return true;
		}
		return false;
	}
	
	@Override
	public MusicLabel update(String name, MusicLabel entity)
	{
		MusicLabel label = findByID(name);
		if(label != null) {
			this.musicLabelList.set(this.musicLabelList.indexOf(label), entity);
			return label;
		}
		return null;
	}
	
	@Override
	public MusicLabel findByID(String name)
	{
		for(MusicLabel label: musicLabelList){
			if(label.getName().equals(name))
				return label;
		}
		return null;
	}
	
	@Override
	public List<MusicLabel> findAll()
	{
		return this.musicLabelList;
	}
}
