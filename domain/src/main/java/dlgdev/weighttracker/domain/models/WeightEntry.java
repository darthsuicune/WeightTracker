package dlgdev.weighttracker.domain.models;

import android.database.Cursor;

import org.joda.time.DateTime;

import dlgdev.weighttracker.domain.db.WeightCheckerDatabase.WeightEntryColumns;

public class WeightEntry {
	public DateTime date;
	public double weight;

	public WeightEntry() {}

	public WeightEntry(DateTime date, double weight) {
		this.date = date;
		this.weight = weight;
	}

	public WeightEntry(Cursor data) {
		this.date = new DateTime(data.getLong(data.getColumnIndex(WeightEntryColumns.DATE)));
		this.weight = data.getDouble(data.getColumnIndex(WeightEntryColumns.WEIGHT));
	}
}
