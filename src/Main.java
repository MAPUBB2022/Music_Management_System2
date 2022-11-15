import controller.UserController;
import model.album.Album;
import model.album.Artist;
import repository.inmemory.AlbumsInMemoryRepository;
import repository.inmemory.ArtistsInMemoryRepository;
import repository.inmemory.ConcertsInMemoryRepository;
import repository.inmemory.SongsInMemoryRepository;
import view.UserUI;

import java.text.ParseException;

public class Main
{
	public static void main(String[] args) throws ParseException
	{
//		AlbumsInMemoryRepository albumsInMemoryRepository = new AlbumsInMemoryRepository();
//		albumsInMemoryRepository.add(new Album("ceva", new Artist("bob")));
//		for (Album album : albumsInMemoryRepository.findAll())
//			System.out.println(album.toString());
//		System.out.println(albumsInMemoryRepository.findAll().toString());
//		UserController controller = new UserController(albumsInMemoryRepository,new ArtistsInMemoryRepository(),new ConcertsInMemoryRepository(), new SongsInMemoryRepository());
//		System.out.println(controller.showArtists());
		UserUI ui = new UserUI("In Memory");
		ui.Menu();
	}
}