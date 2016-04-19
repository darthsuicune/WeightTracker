package dlgdev.weighttracker.domain.models.checkers;

import android.database.Cursor;

import org.joda.time.DateTime;

public interface WeightTrackerRequirements {

	void requestNewEntry(double lastValue);

	void loadEntries(Cursor data);

	void addNewEntry(DateTime date, String weight);
}
