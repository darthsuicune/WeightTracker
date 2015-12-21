package dlgdev.weighttracker.views.checkers;


import android.database.Cursor;
import android.support.v4.app.DialogFragment;

import org.joda.time.DateTime;

import javax.inject.Inject;

import dlgdev.weighttracker.domain.WeightEntryRepository;
import dlgdev.weighttracker.views.entry.NewWeightEntryDialog;

public class WeightTrackerControllerImpl implements WeightTrackerController {
	private static final String TAG_NEW_WEIGHT_DIALOG = "tag_new_weight_dialog";
	WeightEntryRepository repository;
	WeightTrackerActions actions;

	@Inject public WeightTrackerControllerImpl(WeightTrackerActions actions,
											   WeightEntryRepository repository) {
		this.actions = actions;
		this.repository = repository;
	}

	@Override public void requestNewEntry(double lastValue) {
		DialogFragment fragment = NewWeightEntryDialog.newInstance(lastValue);
		fragment.show(actions.fragmentManager(), TAG_NEW_WEIGHT_DIALOG);
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
