package dlgdev.weighttracker.di;

import dagger.Component;
import dlgdev.weighttracker.views.checkers.WeightTrackerActivity;

@Component(modules = {WeightCheckerActivityModule.class})
public interface WeightCheckerActivityComponent {
	void inject(WeightTrackerActivity activity);
}
