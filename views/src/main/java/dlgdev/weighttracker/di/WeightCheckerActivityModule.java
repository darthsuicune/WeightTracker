package dlgdev.weighttracker.di;

import android.content.ContentResolver;

import dagger.Module;
import dagger.Provides;
import dlgdev.weighttracker.domain.WeightEntryRepository;
import dlgdev.weighttracker.domain.WeightEntryRepositoryImpl;
import dlgdev.weighttracker.domain.models.checkers.WeightTrackerActions;
import dlgdev.weighttracker.views.checkers.WeightTrackerActivity;
import dlgdev.weighttracker.domain.models.checkers.WeightTrackerRequirements;
import dlgdev.weighttracker.domain.models.checkers.WeightTrackerController;

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
