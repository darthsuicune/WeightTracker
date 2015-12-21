package dlgdev.weighttracker.di;

import dagger.Module;
import dagger.Provides;
import dlgdev.weighttracker.views.users.UsersActivity;
import dlgdev.weighttracker.views.users.UsersActivityActions;
import dlgdev.weighttracker.views.users.UsersActivityController;
import dlgdev.weighttracker.views.users.UsersActivityControllerImpl;

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
