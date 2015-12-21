package dlgdev.weighttracker.views.checkers;

import android.database.Cursor;

import org.joda.time.DateTime;

public interface WeightTrackerController {

	void requestNewEntry(double lastValue);

	void loadEntries(Cursor data);

	void addNewEntry(DateTime date, String weight);
}
