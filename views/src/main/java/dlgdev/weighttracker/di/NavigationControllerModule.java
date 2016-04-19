package dlgdev.weighttracker.di;

import android.content.Context;
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

	@Provides Context provideContext() {
		return activity;
	}
}
