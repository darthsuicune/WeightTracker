package dlgdev.weighttracker.domain;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.os.AsyncTask;

import org.joda.time.DateTime;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import dlgdev.weighttracker.domain.db.WeightCheckerDatabase;
import dlgdev.weighttracker.domain.db.WeightCheckerProvider;
import dlgdev.weighttracker.domain.models.WeightEntry;

public class WeightEntryRepositoryImpl implements WeightEntryRepository {

	private ContentResolver resolver;

	@Inject public WeightEntryRepositoryImpl(ContentResolver resolver) {
		this.resolver = resolver;
	}

	@Override public void addNewEntry(DateTime date, String weight) {
		new InsertEntryTask(date, weight).execute();
	}

	@Override public WeightEntry getLast() {
		Cursor data =
				resolver.query(WeightCheckerProvider.WeightEntries.WEIGHT_ENTRIES_URI, null, null,
						null, WeightCheckerDatabase.WeightEntryColumns.DATE + " DESC");
		if(data != null && data.moveToFirst()) {
			return new WeightEntry(data);
		} else {
			throw new RuntimeException("Failed to find the last entry");
		}
	}

	@Override public List<WeightEntry> loadEntries(Cursor data) {
		List<WeightEntry> entries = new ArrayList<>();
		if (data.moveToFirst()) {
			do {
				entries.add(new WeightEntry(data));
			} while (data.moveToNext());
		}
		return entries;
	}

	private class InsertEntryTask extends AsyncTask<Void, Void, Void> {
		private final DateTime date;
		private final String weight;

		public InsertEntryTask(DateTime date, String weight) {
			this.date = date;
			this.weight = weight;
		}

		@Override protected Void doInBackground(Void... voids) {
			Uri uri = WeightCheckerProvider.WeightEntries.WEIGHT_ENTRIES_URI;
			ContentValues values = new ContentValues(2);
			values.put(WeightCheckerDatabase.WeightEntryColumns.WEIGHT, weight);
			values.put(WeightCheckerDatabase.WeightEntryColumns.DATE, date.getMillis());
			resolver.insert(uri, values);
			return null;
		}
	}
}
