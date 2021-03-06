package dlgdev.weighttracker.domain.controllers.checkers;

import android.database.Cursor;

import org.joda.time.DateTime;

public interface WeightTrackerRequirements {

	void requestNewEntry();

	void loadEntries(Cursor data);

	void addNewEntry(DateTime date, String weight);
}
