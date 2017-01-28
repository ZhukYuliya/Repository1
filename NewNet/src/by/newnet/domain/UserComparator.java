package by.newnet.domain;

import java.util.Comparator;

public class UserComparator implements Comparator<User> {

	@Override
	public int compare(User user1, User user2) {
		int n;
		n = user1.getRole().getRoleCoef() - (user2.getRole().getRoleCoef());
		if (n != 0) {
			return n;
		} else {
			n = user1.getSecondName().compareTo(user2.getSecondName());
			if (n != 0) {
				return n;
			} else
				return user1.getFirstName().compareTo(user2.getFirstName());
		}
	}
}
