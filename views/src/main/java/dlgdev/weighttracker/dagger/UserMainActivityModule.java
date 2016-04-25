package dlgdev.weighttracker.dagger;

import dagger.Module;
import dagger.Provides;
import dlgdev.weighttracker.domain.controllers.users.UserDataCardPresenter;
import dlgdev.weighttracker.domain.controllers.users.UserDataCardPresenterImpl;
import dlgdev.weighttracker.domain.controllers.users.UserMainActivityController;
import dlgdev.weighttracker.domain.controllers.users.UserMainActivityControllerImpl;

@Module
public class UserMainActivityModule {

	@Provides
	UserMainActivityController provideController(UserMainActivityControllerImpl implementation) {
		return implementation;
	}

	@Provides UserDataCardPresenter provideDataCardInterface(UserDataCardPresenterImpl presenter) {
		return presenter;
	}
}
