package dlgdev.weighttracker.dagger;

import dagger.Component;
import dlgdev.weighttracker.views.users.UsersActivity;

@Component(modules = {UsersActivityModule.class, NavigationControllerModule.class})
public interface UsersActivityComponent {
	void inject(UsersActivity activity);
}
