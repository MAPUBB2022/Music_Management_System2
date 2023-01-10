import interfaces.UserRepository;
import model.users.User;
import repository.inmemory.UserInMemoryRepository;
import repository.jdbc.JdbcUserRepository;
import view.UserUI;

import java.text.ParseException;
import java.util.Scanner;

public class Main
{
	private static final int MAX_AUTHENTICATION_TRIES = 3;
	
	public static void main(String[] args) throws ParseException
	{
		System.out.println("[WARNING] Choose Saving Method:\n1 - In Memory\n2 - JSON File\n3 - Database(JDBC)\n\nChoice: ");
		Scanner scanner = new Scanner(System.in);
		int saveMethod = Integer.parseInt(scanner.next());

		int counter = 0;
		while (saveMethod < 1 || saveMethod > 3 && counter < MAX_AUTHENTICATION_TRIES) {
			System.out.println("[WARNING] Invalid Input, Try Again\n\nChoice: ");
			counter++;
			saveMethod = Integer.parseInt(scanner.next());
		}

		if (counter == MAX_AUTHENTICATION_TRIES) {
			System.out.println("[AUTHENTICATION] No more attempts...\n");
			return;
		}

		String username, password;
		System.out.println("Now, let's authenticate...\nUsername: ");
		username = scanner.next();
		System.out.println("Password: ");
		password = scanner.next();

		UserRepository userRepository = null;

		switch (saveMethod) {
			case 1 -> userRepository = new UserInMemoryRepository();
			case 2 -> System.out.println("[WARNING] Not yet implemented");
			case 3 -> userRepository = new JdbcUserRepository();
		}

		assert userRepository != null;
		User user = userRepository.findByUsernameAndPassword(username, password);

		if (user == null) {
			System.out.println("[ERROR] Invalid Credentials...\n");
			return;
		}

		UserUI ui = new UserUI(saveMethod, user);
		ui.switchMenu();
	}
}