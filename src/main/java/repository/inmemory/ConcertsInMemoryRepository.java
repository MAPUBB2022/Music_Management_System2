package repository.inmemory;

import interfaces.ICrudRepository;
import model.album.Artist;
import model.concert.Concert;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ConcertsInMemoryRepository implements ICrudRepository<Integer, Concert>
{
	private List<Concert> concertList;
	
	public ConcertsInMemoryRepository() throws ParseException
	{
		this.concertList = populateConcerts();
	}
	
	private List<Concert> populateConcerts() throws ParseException
	{
		Artist c1a1 = new Artist("Martyn Ware");
		Artist c1a2 = new Artist("Ian Craig Marsh");
		Artist c1a3 = new Artist("Glenn Gregory");
		List<Artist> c1Artists = new ArrayList<>(Arrays.asList(c1a1, c1a2, c1a3));
		
		Artist c2a1 = new Artist("Baba Dochia");
		Artist c2a2 = new Artist("Smiley");
		List<Artist> c2Artists = new ArrayList<>(Arrays.asList(c2a1, c2a2));
		
		Concert c1 = new Concert("Heaven 17", c1Artists, "Liverpool, Great Britain", new SimpleDateFormat("dd.MM.yyyy").parse("26.11.2012"), 2500);
		Concert c2 = new Concert("Baba Dochia", c2Artists, "Cluj-Napoca, Cluj", new SimpleDateFormat("dd.MM.yyyy").parse("05.11.2012"), 800);
		return new ArrayList<>(Arrays.asList(c1, c2));
	}
	
	@Override
	public void add(Concert entity)
	{
		this.concertList.add(entity);
	}
	
	@Override
	public void remove(Concert entity)
	{
		this.concertList.remove(entity);
	}
	
	@Override
	public void update(Integer index, Concert entity)
	{
		this.concertList.set(index, entity);
	}
	
	@Override
	public Concert findByID(Integer index)
	{
		return this.concertList.get(index);
	}
	
	@Override
	public List<Concert> findAll()
	{
		return this.concertList;
	}
}
