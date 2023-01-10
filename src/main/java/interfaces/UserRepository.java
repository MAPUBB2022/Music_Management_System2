package interfaces;

import model.users.User;

/**
 * Extends the ICrudRepository<ID, E> Interface by implementing function for User Authentication
 * (Username and Password).
 */
public interface UserRepository extends ICrudRepository<String, User>
{
	/**
	 * Identifies User from Repository by Username and Password.
	 * @param username Username.
	 * @param password Password.
	 * @return - User Object, if Object was successfully found in the Repository, null otherwise.
	 */
	User findByUsernameAndPassword(String username, String password);
}
