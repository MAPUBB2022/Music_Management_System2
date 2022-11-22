package repository.inmemory;

import interfaces.ICrudRepository;
import model.album.Artist;
import model.album.Band;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BandsInMemoryRepository implements ICrudRepository<String, Band>
{
	private List<Band> bandList;
	
	public BandsInMemoryRepository()
	{
		this.bandList = populateBands();
	}
	
	private List<Band> populateBands()
	{
		Band b1 = new Band("The Rolling Stones");
		Band b2 = new Band("Led Zeppelin");
		Band b3 = new Band("The Beatles");
		return new ArrayList<>(Arrays.asList(b1, b2, b3));
	}
	
	@Override
	public void add(Band entity)
	{
		if(!this.bandList.contains(entity))
			this.bandList.add(entity);
	}
	
	@Override
	public void remove(Band entity)
	{
		if(findByID(entity.getName()) != null)
			this.bandList.remove(entity);
	}
	
	@Override
	public void update(String name, Band entity)
	{
		Band band = findByID(name);
		if(band != null)
			this.bandList.set(this.bandList.indexOf(band), entity);
	}
	
	@Override
	public Band findByID(String name)
	{
		for(Band band: bandList){
			if(band.getName().equals(name))
				return band;
		}
		return null;
	}

	@Override
	public List<Band> findAll()
	{
		return this.bandList;
	}
}
