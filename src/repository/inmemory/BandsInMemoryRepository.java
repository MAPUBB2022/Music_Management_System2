package repository.inmemory;

import interfaces.ICrudRepository;
import model.album.Band;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BandsInMemoryRepository implements ICrudRepository<Integer, Band>
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
		this.bandList.add(entity);
	}
	
	@Override
	public void remove(Band entity)
	{
		this.bandList.remove(entity);
	}
	
	@Override
	public void update(Integer index, Band entity)
	{
		this.bandList.set(index, entity);
	}
	
	@Override
	public Band findByID(Integer index)
	{
		return this.bandList.get(index);
	}
	
	@Override
	public List<Band> findAll()
	{
		return this.bandList;
	}
}
