package dlgdev.weighttracker.domain;

import android.database.Cursor;

import org.joda.time.DateTime;

import java.util.List;

import dlgdev.weighttracker.domain.models.WeightEntry;

public interface WeightEntryRepository {
	void addNewEntry(DateTime something, String weight);

	WeightEntry getLast();

	List<WeightEntry> loadEntries(Cursor data);
}
