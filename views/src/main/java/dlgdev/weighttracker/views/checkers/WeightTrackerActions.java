package dlgdev.weighttracker.views.checkers;

import android.database.Cursor;
import android.support.v4.app.FragmentManager;

public interface WeightTrackerActions {
	void showList(Cursor data);

	void showEmptyList();

	FragmentManager fragmentManager();
}
