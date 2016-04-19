package dlgdev.weighttracker.domain.models.checkers;

import android.database.Cursor;

public interface WeightTrackerActions {
	void showList(Cursor data);

	void showEmptyList();
}
