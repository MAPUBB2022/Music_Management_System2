package interfaces;

import java.util.List;

public interface ICrudRepository<ID, E>
{
	void add(E entity);
	
	void remove(E entity);
	
	void update(ID id, E entity);

	String toString();
	
	E findByID(ID id);
	
	List<E> findAll();
}
