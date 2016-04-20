package dlgdev.weighttracker.dagger;

import android.support.v7.app.AppCompatActivity;

import dagger.Module;
import dagger.Provides;
import dlgdev.weighttracker.domain.NavigationController;
import dlgdev.weighttracker.views.AppNavigation;

@Module
public class NavigationControllerModule {
	private AppCompatActivity activity;

	public NavigationControllerModule(AppCompatActivity activity) {
		this.activity = activity;
	}

	@Provides NavigationController provideNavigationController(AppNavigation navigation) {
		return navigation;
	}

	@Provides AppCompatActivity provideActivity() {
		return activity;
	}
}
