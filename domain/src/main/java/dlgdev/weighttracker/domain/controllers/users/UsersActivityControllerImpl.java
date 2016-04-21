package dlgdev.weighttracker.domain.controllers.users;

import java.util.List;

import javax.inject.Inject;

import dlgdev.weighttracker.domain.NavigationController;
import dlgdev.weighttracker.domain.models.User;

public class UsersActivityControllerImpl implements UsersActivityController {

	private final UsersActivityActions actions;
	NavigationController navigation;

	@Inject UsersActivityControllerImpl(UsersActivityActions actions,
										NavigationController navigation) {
		this.actions = actions;
		this.navigation = navigation;
	}

	@Override public void loadUsers(List<User> data) {
		actions.loadUserList(data);
	}

	@Override public void addUser() {

	}

	@Override public void selectUser(User user) {
		navigation.navigateToUserStats(user);
	}
}
