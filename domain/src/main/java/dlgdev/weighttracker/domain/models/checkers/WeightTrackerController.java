package dlgdev.weighttracker.domain.models.checkers;


import android.database.Cursor;

import org.joda.time.DateTime;

import javax.inject.Inject;

import dlgdev.weighttracker.domain.NavigationController;
import dlgdev.weighttracker.domain.WeightEntryRepository;

public class WeightTrackerController implements WeightTrackerRequirements {

	WeightEntryRepository repository;
	WeightTrackerActions actions;
	NavigationController navigation;

	@Inject public WeightTrackerController(WeightTrackerActions actions,
										   WeightEntryRepository repository,
										   NavigationController navigation) {
		this.actions = actions;
		this.repository = repository;
		this.navigation = navigation;
	}

	@Override public void requestNewEntry(double lastValue) {
		navigation.requestEntryData(lastValue);
	}

	@Override public void loadEntries(Cursor data) {
		if(data != null && data.getCount() > 0) {
			actions.showList(data);
		} else {
			actions.showEmptyList();
		}
	}

	@Override public void addNewEntry(DateTime date, String weight) {
		repository.addNewEntry(date, weight);
	}
}
