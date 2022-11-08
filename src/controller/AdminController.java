package controller;

import interfaces.IAdminController;
import interfaces.ICrudRepository;
import model.album.Album;
import model.album.Artist;
import model.album.Band;
import model.concert.Concert;
import model.label.MusicLabel;
import model.song.Song;
import model.users.User;
import java.util.ArrayList;
import java.util.List;

public class AdminController implements IAdminController
{
	private List<Artist> artistList;

	private List<Album> albumList;
	private List<Concert> concertList;
	private List<Song> songList;
	private List<Band> bandList;
	private List<MusicLabel> labelList;
	private List<User> userList;

	public AdminController()
	{
		this.albumList = new ArrayList<>();
		this.artistList = new ArrayList<>();
		this.concertList = new ArrayList<>();
		this.songList = new ArrayList<>();
	}
	@Override
	public String addAlbum(Album album)
	{
		if(this.albumList.contains(album))
			return "[ERROR] Album already contained in List\n";
		this.albumList.add(album);
		return "[] Album added\n";
	}
	
	@Override
	public String deleteAlbum(Album album)
	{
		if (!this.albumList.contains(album))
			return "[ERROR] Song not in List\n";
		this.albumList.remove(album);
		return "[] Album deleted\n";
	}
	
	@Override
	public void modifyAlbum(Album album)
	{

	}
	
	@Override
	public String addArtist(Artist artist)
	{
		if(this.artistList.contains(artist))
			return "[ERROR] Artist already contained in List\n";
		this.artistList.add(artist);
		return "[] Artist added\n";
	}
	
	@Override
	public String deleteArtist(Artist artist)
	{
		if (!this.artistList.contains(artist))
			return "[ERROR] Artist not in List\n";
		this.artistList.remove(artist);
		return "[] Artist deleted\n";
	}
	
	@Override
	public void modifyArtist(Artist artist)
	{
	
	}
	
	@Override
	public String addBand(Band band)
	{
		if(this.bandList.contains(band))
			return "[ERROR] Band already contained in List\n";
		this.bandList.add(band);
		return "[] Band added\n";
	}
	
	@Override
	public String deleteBand(Band band)
	{
		if (!this.bandList.contains(band))
			return "[ERROR] Band not in List\n";
		this.bandList.remove(band);
		return "[] Band deleted\n";
	}
	
	@Override
	public void modifyBand(Band band)
	{
	
	}
	
	@Override
	public String addConcert(Concert concert)
	{
		if(this.concertList.contains(concert))
			return "[ERROR] Concert already contained in List\n";
		this.concertList.add(concert);
		return "[] Concert added\n";
	}
	
	@Override
	public String deleteConcert(Concert concert)
	{
		if (!this.concertList.contains(concert))
			return "[ERROR] Concert not in List\n";
		this.concertList.remove(concert);
		return "[] Concert deleted\n";
	}
	
	@Override
	public void modifyConcert(Concert concert)
	{
	
	}
	
	@Override
	public String addMusicLabel(MusicLabel musicLabel)
	{
		if(this.labelList.contains(musicLabel))
			return "[ERROR] Label already contained in List\n";
		this.labelList.add(musicLabel);
		return "[] Label added\n";
	}
	
	@Override
	public String deleteMusicLabel(MusicLabel musicLabel)
	{
		if (!this.labelList.contains(musicLabel))
			return "[ERROR] Label not in List\n";
		this.labelList.remove(musicLabel);
		return "[] Label deleted\n";
	}
	
	@Override
	public void modifyMusicLabel(MusicLabel musicLabel)
	{
	
	}
	
	@Override
	public String addSong(Song song)
	{
		if(this.songList.contains(song))
			return "[ERROR] Song already contained in List\n";
		this.songList.add(song);
		return "[] Song added\n";
	}
	
	@Override
	public String deleteSong(Song song)
	{
		if (!this.songList.contains(song))
			return "[ERROR] Song not in List\n";
		this.songList.remove(song);
		return "[] Song deleted\n";
	}
	
	@Override
	public void modifySong(Song song)
	{
	
	}
	
	@Override
	public String addUser(User user)
	{
		if(this.userList.contains(user))
			return "[ERROR] User already contained in List\n";
		this.userList.add(user);
		return "[] User added\n";
	}
	
	@Override
	public String deleteUser(User user)
	{
		if (!this.userList.contains(user))
			return "[ERROR] User not in List\n";
		this.userList.remove(user);
		return "[] User deleted\n";
	}
	
	@Override
	public void modifyUser(User user)
	{
	
	}

	@Override
	public void sortAlbumsByRevenue()
	{

	}

	@Override
	public void sortSongsByRating()
	{

	}

	@Override
	public void sortSongsByReleaseDate()
	{

	}

	@Override
	public void sortArtistsByName()
	{

	}

	@Override
	public void sortAlbumsByReleaseDate()
	{

	}
	
	@Override
	public void showConcerts() throws NullPointerException
	{

	}
	
	@Override
	public void sortAlbumsByProductionCost()
	{
	
	}
	
	@Override
	public void showArtists()
	{
	
	}
	
	@Override
	public void showAlbums()
	{
	
	}
	
	@Override
	public void showAlbumsForArtist(Artist artist)
	{
	
	}
	
	@Override
	public void showUpcomingConcerts()
	{
	
	}
}
