import interfaces.UserRepository;
import model.users.User;
import repository.inmemory.UserInMemoryRepository;
import view.UserUI;

import java.text.ParseException;
import java.util.Scanner;

public class Main
{
	public static void main(String[] args) throws ParseException
	{
		System.out.println("[WARNING] Choose Saving Method:\n1 - In Memory\n2 - JSON File\n3 - Database(JDBC)\n\nChoice: ");
		Scanner scanner = new Scanner(System.in);
		int saveMethod = Integer.parseInt(scanner.next());
		
		while (saveMethod < 1 || saveMethod > 3) {
			System.out.println("Invalid Input, Try Again\n\nChoice: ");
			saveMethod = Integer.parseInt(scanner.next());
		}
		
		String username, password;
		System.out.println("Now, let's authenticate...\nUsername: ");
		username = scanner.next();
		System.out.println("Password: ");
		password = scanner.next();
		UserRepository userRepository = null;
		
		switch (saveMethod) {
			case 1 -> userRepository = new UserInMemoryRepository();
			case 2 -> System.out.println("Not yet implemented");
			case 3 -> System.out.println("Not yet implemented!");
		}
		
		assert userRepository != null;
		User user = userRepository.findByUsernameAndPassword(username, password);
		
		if (user == null) {
			System.out.println("Invalid User!");
			return;
		}
		
		UserUI ui = user.isAdmin() ? new UserUI(saveMethod, true) : new UserUI(saveMethod, false);
		ui.switchMenu();
	}
}