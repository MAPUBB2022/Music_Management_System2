package repository.inmemory;

import interfaces.ICrudRepository;
import model.album.Artist;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ArtistsInMemoryRepository implements ICrudRepository<String, Artist>
{
	private List<Artist> artistList;
	
	public ArtistsInMemoryRepository()
	{
		this.artistList = populateArtists();
	}
	
	private List<Artist> populateArtists()
	{
		Artist a1 = new Artist("Eminem");
		Artist a2 = new Artist("50 Cent");
		Artist a3 = new Artist("Kanye West");
		Artist a4 = new Artist("Travis Scott");
		Artist a5 = new Artist("Pitbull");
		Artist a6 = new Artist("Macklemore");
		Artist a7 = new Artist("Kid Cudi");
		Artist a8 = new Artist("Madcon");
		Artist a9 = new Artist("Lil Wayne");
		Artist a10 = new Artist("Maroon 5");
		Artist a11 = new Artist("Daft Punk");
		Artist a12 = new Artist("Sean Paul");
		Artist a13 = new Artist("Ludwig van Beethoven");
		Artist a14 = new Artist("Wolfgang Amadeus Mozart");
		return new ArrayList<>(Arrays.asList(a1, a2, a3, a4, a5, a6, a7, a8, a9, a10, a11, a12, a13, a14));
	}
	
	
	@Override
	public void add(Artist entity) throws Error
	{
		if(!this.artistList.contains(entity)) {
			this.artistList.add(entity);
			return;
		}
		throw new Error("[Error] Artist doesn't exists!");

	}
	
	@Override
	public void remove(Artist entity) throws Error
	{
		if(findByID(entity.getName())!=null){
			this.artistList.remove(entity);
			return;
		}
		throw new Error("[Error] Artist doesn't exists!");
	}
	
	@Override
	public Artist update(String name, Artist entity) throws Error
	{
		Artist artist = findByID(name);
		if(artist != null) {
			this.artistList.set(this.artistList.indexOf(artist), entity);
			return artist;
		}
		throw new Error("[Error] Artist doesn't exists!");
	}
	
	@Override
	public Artist findByID(String name)
	{
		for(Artist artist: artistList){
			if(artist.getName().equals(name))
				return artist;
		}
		throw new Error("[Error] Artist doesn't exists!");
	}
	
	@Override
	public List<Artist> findAll()
	{
		if (!artistList.isEmpty())
			return artistList;
		throw new Error("[Error] Artist list is empty!");
	}
}
