package interfaces;

import java.util.List;

/**
 * Interface represents the Basic Building Block of the Repositories, implementing the CRUD Functions:
 * Create (add), Read (findById, findAll, toString), Update (update), and Delete (remove).
 *
 * @param <ID> Template Parameter for Unique Object Identifier.
 * @param <E>  Template Parameter for Object Type.
 */
public interface ICrudRepository<ID, E>
{
	/**
	 * Adds Object to Repository.
	 *
	 * @param entity Object to be added.
	 * @return True, if the Object was successfully added, False otherwise.
	 */
	boolean add(E entity);
	
	/**
	 * Removes Object from Repository.
	 *
	 * @param entity Object to be deleted.
	 * @return True, if the Object was successfully deleted, False otherwise.
	 */
	boolean remove(E entity);
	
	/**
	 * Updates an existing Object.
	 *
	 * @param id     Object Identifier.
	 * @param entity Object to Replace With.
	 * @return Replaced Object, if the Object was successfully updated, null otherwise.
	 */
	E update(ID id, E entity);
	
	/**
	 * Finds an Object in the Repository.
	 *
	 * @param id Object Identifier.
	 * @return Object, if the Object was successfully found, null otherwise.
	 */
	E findByID(ID id);
	
	/**
	 * Returns a List of All Object from the Repository.
	 *
	 * @return List of Repository Objects.
	 */
	List<E> findAll();
	
	/**
	 * Transforms Object in a human-readable Format.
	 *
	 * @return String of Object.
	 */
	String toString();
}
