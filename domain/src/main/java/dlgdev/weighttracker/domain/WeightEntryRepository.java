package dlgdev.weighttracker.domain;

import android.net.Uri;

import org.joda.time.DateTime;

public interface WeightEntryRepository {
	Uri addNewEntry(DateTime something, String weight);
}
