import model.album.Album;
import model.album.Artist;
import repository.inmemory.AlbumsInMemoryRepository;

import java.text.ParseException;

public class Main
{
	public static void main(String[] args) throws ParseException
	{
		System.out.println("Hello world!");
		AlbumsInMemoryRepository albumsInMemoryRepository = new AlbumsInMemoryRepository();
		albumsInMemoryRepository.add(new Album("ceva", new Artist("bob")));
		for (Album album : albumsInMemoryRepository.findAll())
			System.out.println(album.toString());
//		System.out.println(albumsInMemoryRepository.findAll().toString());
	}
}