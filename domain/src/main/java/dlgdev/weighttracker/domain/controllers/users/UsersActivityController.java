package dlgdev.weighttracker.domain.controllers.users;

import java.util.List;

import dlgdev.weighttracker.domain.models.User;

public interface UsersActivityController {

	void loadUsers(List<User> data);

	void addUser();

	void selectUser(User user);
}