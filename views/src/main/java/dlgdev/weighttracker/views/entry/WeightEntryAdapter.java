package dlgdev.weighttracker.views.entry;

import android.database.Cursor;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.text.DateFormat;
import java.util.Date;

import javax.inject.Inject;

import dlgdev.weighttracker.R;

import static dlgdev.weighttracker.domain.db.WeightCheckerDatabase.WeightEntryColumns.DATE;
import static dlgdev.weighttracker.domain.db.WeightCheckerDatabase.WeightEntryColumns.WEIGHT;

public class WeightEntryAdapter extends RecyclerView.Adapter<WeightEntryViewHolder> {
	Cursor items;
	DateFormat dateFormat;

	@Inject public WeightEntryAdapter(DateFormat dateFormat) {
		this.dateFormat = dateFormat;
	}

	@Override public WeightEntryViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
		View v = LayoutInflater.from(parent.getContext())
				.inflate(R.layout.weight_entry_view, parent, false);
		return new WeightEntryViewHolder(v);
	}

	@Override public void onBindViewHolder(WeightEntryViewHolder holder, int position) {
		if (items.moveToPosition(position)) {
			String date = dateFormat.format(new Date(items.getLong(items.getColumnIndex(DATE))));
			String entry = items.getString(items.getColumnIndex(WEIGHT));
			holder.setEntry(date, entry);
		}
	}

	@Override public int getItemCount() {
		return items.getCount();
	}

	public void setData(Cursor data) {
		this.items = data;
		this.notifyDataSetChanged();
	}
}
