package ClientClasses;

import java.util.Comparator;

public class SortNameUserList implements Comparator<User> {
    
	public int compare(User user1, User user2) {
        String firstName1 = user1.getFirstName();
        String firstName2 = user2.getFirstName();
        return firstName1.compareTo(firstName2);
    }
}