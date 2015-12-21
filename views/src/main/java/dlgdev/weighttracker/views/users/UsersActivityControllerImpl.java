package dlgdev.weighttracker.views.users;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import dlgdev.weighttracker.domain.models.User;

public class UsersActivityControllerImpl implements UsersActivityController {

	private final UsersActivityActions actions;

	@Inject UsersActivityControllerImpl(UsersActivityActions actions) {
		this.actions = actions;
	}

	@Override public void userListLoaded(List<User> data) {
		actions.loadUserList(data);
	}

	@Override public void addUser() {

	}

	public void loadUsers() {
		List<User> userList = new ArrayList<>();
		actions.loadUserList(userList);
	}
}
