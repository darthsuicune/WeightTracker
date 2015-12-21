package dlgdev.weighttracker.domain;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.net.Uri;

import org.joda.time.DateTime;

import dlgdev.weighttracker.domain.db.WeightCheckerDatabase;
import dlgdev.weighttracker.domain.db.WeightCheckerProvider;

public class WeightEntryRepositoryImpl implements WeightEntryRepository {

	private ContentResolver resolver;

	public WeightEntryRepositoryImpl(ContentResolver resolver) {
		this.resolver = resolver;
	}

	@Override public Uri addNewEntry(DateTime date, String weight) {
		Uri uri = WeightCheckerProvider.WeightEntries.WEIGHT_ENTRIES_URI;
		ContentValues values = new ContentValues(2);
		values.put(WeightCheckerDatabase.WeightEntryColumns.WEIGHT, weight);
		values.put(WeightCheckerDatabase.WeightEntryColumns.DATE, date.getMillis());
		return resolver.insert(uri, values);
	}
}
