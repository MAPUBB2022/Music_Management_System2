package repository.inmemory;

import interfaces.ICrudRepository;
import model.album.Artist;
import model.album.Band;
import model.concert.Concert;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ConcertsInMemoryRepository implements ICrudRepository<String, Concert>
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
	public boolean add(Concert entity)
	{
		if(!this.concertList.contains(entity)) {
			this.concertList.add(entity);
			return true;
		}
		return false;
	}
	
	@Override
	public boolean remove(Concert entity)
	{
		if(findByID(entity.getName()) != null) {
			this.concertList.remove(entity);
			return true;
		}
		return false;
	}
	
	@Override
	public Concert update(String name, Concert entity)
	{
		Concert concert = findByID(name);
		if(concert != null){
			this.concertList.set(this.concertList.indexOf(concert), entity);
			return concert;
		}
		return null;
	}
	
	@Override
	public Concert findByID(String name)
	{
		for(Concert concert: concertList){
			if(concert.getName().equals(name))
				return concert;
		}
		return null;
	}
	
	@Override
	public List<Concert> findAll()
	{
		return this.concertList;
	}
}