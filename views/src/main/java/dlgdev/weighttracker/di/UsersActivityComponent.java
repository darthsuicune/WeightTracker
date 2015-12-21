package dlgdev.weighttracker.di;

import dagger.Component;
import dlgdev.weighttracker.views.users.UsersActivity;

@Component(modules = {UsersActivityModule.class})
public interface UsersActivityComponent {
	void inject(UsersActivity activity);
}