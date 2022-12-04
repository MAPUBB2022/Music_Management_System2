package interfaces;

import java.util.List;

public interface ICrudRepository<ID, E>
{
	boolean add(E entity);
	
	boolean remove(E entity);
	
	E update(ID id, E entity);
	
	E findByID(ID id);
	
	List<E> findAll();
	
	String toString();
}
