package dlgdev.weighttracker.views.users;

import java.util.List;

import dlgdev.weighttracker.domain.models.User;

public interface UsersActivityActions {
	void loadUserList(List<User> userList);
}
