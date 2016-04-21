package dlgdev.weighttracker.dagger;

import android.content.ContentResolver;

import dagger.Module;
import dagger.Provides;
import dlgdev.weighttracker.domain.WeightEntryRepository;
import dlgdev.weighttracker.domain.WeightEntryRepositoryImpl;
import dlgdev.weighttracker.domain.controllers.checkers.WeightTrackerActions;
import dlgdev.weighttracker.domain.controllers.checkers.WeightTrackerController;
import dlgdev.weighttracker.domain.controllers.checkers.WeightTrackerRequirements;
import dlgdev.weighttracker.views.checkers.WeightTrackerActivity;

@Module
public class WeightCheckerActivityModule {
	WeightTrackerActivity activity;

	public WeightCheckerActivityModule(WeightTrackerActivity activity) {
		this.activity = activity;
	}

	@Provides WeightTrackerRequirements provideController(WeightTrackerController impl) {
		return impl;
	}

	@Provides WeightTrackerActions provideActionsCallback() {
		return activity;
	}

	@Provides WeightEntryRepository provideRepository(WeightEntryRepositoryImpl repo) {
		return repo;
	}

	@Provides ContentResolver provideResolver() {
		return activity.getContentResolver();
	}
}
