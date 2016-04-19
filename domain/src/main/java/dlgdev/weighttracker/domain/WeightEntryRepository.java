package dlgdev.weighttracker.domain;

import org.joda.time.DateTime;

public interface WeightEntryRepository {
	void addNewEntry(DateTime something, String weight);
}
