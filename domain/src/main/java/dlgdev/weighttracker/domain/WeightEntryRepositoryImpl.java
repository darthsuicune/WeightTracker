package dlgdev.weighttracker.domain;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.net.Uri;
import android.os.AsyncTask;

import org.joda.time.DateTime;

import javax.inject.Inject;

import dlgdev.weighttracker.domain.db.WeightCheckerDatabase;
import dlgdev.weighttracker.domain.db.WeightCheckerProvider;

public class WeightEntryRepositoryImpl implements WeightEntryRepository {

	private ContentResolver resolver;

	@Inject public WeightEntryRepositoryImpl(ContentResolver resolver) {
		this.resolver = resolver;
	}

	@Override public void addNewEntry(DateTime date, String weight) {
		new InsertEntryTask(date, weight).execute();
	}

	@Override public double getLast() {
		return 0.0;
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
