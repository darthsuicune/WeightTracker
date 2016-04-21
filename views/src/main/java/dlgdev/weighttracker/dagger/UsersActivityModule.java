package dlgdev.weighttracker.dagger;

import dagger.Module;
import dagger.Provides;
import dlgdev.weighttracker.views.users.UsersActivity;
import dlgdev.weighttracker.domain.controllers.users.UsersActivityActions;
import dlgdev.weighttracker.domain.controllers.users.UsersActivityController;
import dlgdev.weighttracker.domain.controllers.users.UsersActivityControllerImpl;

@Module
public class UsersActivityModule {
	private UsersActivity activity;

	public UsersActivityModule(UsersActivity activity) {
		this.activity = activity;
	}
	@Provides UsersActivityController provideController(UsersActivityControllerImpl implementation) {
		return implementation;
	}

	@Provides UsersActivityActions provideActions() {
		return activity;
	}
}
