package dlgdev.weighttracker.di;

import dagger.Module;
import dagger.Provides;
import dlgdev.weighttracker.domain.WeightEntryRepository;
import dlgdev.weighttracker.domain.WeightEntryRepositoryImpl;
import dlgdev.weighttracker.views.checkers.WeightTrackerActions;
import dlgdev.weighttracker.views.checkers.WeightTrackerActivity;
import dlgdev.weighttracker.views.checkers.WeightTrackerController;
import dlgdev.weighttracker.views.checkers.WeightTrackerControllerImpl;

@Module
public class WeightCheckerActivityModule {
	WeightTrackerActivity activity;

	public WeightCheckerActivityModule(WeightTrackerActivity activity) {
		this.activity = activity;
	}

	@Provides WeightTrackerController provideController(WeightTrackerControllerImpl impl) {
		return impl;
	}

	@Provides WeightTrackerActions provideActionsCallback() {
		return activity;
	}

	@Provides WeightEntryRepository provideRepository() {
		return new WeightEntryRepositoryImpl(activity.getContentResolver());
	}
}
