package dlgdev.weighttracker.views.users;

import java.util.List;

import dlgdev.weighttracker.domain.models.User;

public interface UsersActivityController {

	void userListLoaded(List<User> data);

	void addUser();
}