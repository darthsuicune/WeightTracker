package dlgdev.weighttracker.dagger;

import dagger.Component;
import dlgdev.weighttracker.views.checkers.WeightTrackerActivity;

@Component(modules = {WeightCheckerActivityModule.class, NavigationControllerModule.class,
		DateFormatModule.class})
public interface WeightCheckerActivityComponent {
	void inject(WeightTrackerActivity activity);
}
