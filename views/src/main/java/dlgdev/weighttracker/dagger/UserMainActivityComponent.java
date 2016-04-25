package dlgdev.weighttracker.dagger;

import dagger.Component;
import dlgdev.weighttracker.views.users.UserMainActivity;

@Component(modules = {UserMainActivityModule.class, NavigationControllerModule.class,
RepositoriesModule.class})
public interface UserMainActivityComponent {
	void inject(UserMainActivity activity);
}
