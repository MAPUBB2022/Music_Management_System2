import model.album.Album;
import model.album.Artist;
import repository.inmemory.AlbumsInMemoryRepository;
import view.UserUI;

import java.sql.SQLException;
import java.text.ParseException;

public class Main
{
	public static void main(String[] args) throws ParseException, SQLException {
		System.out.println("Hello world!");
		AlbumsInMemoryRepository albumsInMemoryRepository = new AlbumsInMemoryRepository();
//		albumsInMemoryRepository.add(new Album("ceva", new Artist("bob")));
		for (Album album : albumsInMemoryRepository.findAll())
			System.out.println(album.toString());
//		System.out.println(albumsInMemoryRepository.findAll().toString());

		UserUI ui = new UserUI(1,true);
		ui.switchMenu();
	}
}