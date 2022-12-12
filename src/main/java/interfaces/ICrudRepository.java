package interfaces;

import model.album.Artist;

import java.sql.SQLException;
import java.util.List;

public interface ICrudRepository<ID, E>
{
	boolean add(E entity) throws SQLException;
	
	boolean remove(E entity) throws SQLException;
	
	E update(ID id, E entity) throws SQLException;

	String toString();
	
	E findByID(ID id) throws SQLException;

	List<E> findAll() throws SQLException;
}
