package dlgdev.weighttracker.views;

import android.content.Context;
import android.database.Cursor;
import android.text.format.DateUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.dlgdev.views.DividerWrappedRecyclerView;

import butterknife.Bind;
import butterknife.ButterKnife;
import dlgdev.weighttracker.R;

import static dlgdev.weighttracker.domain.db.WeightCheckerDatabase.WeightEntryColumns.DATE;
import static dlgdev.weighttracker.domain.db.WeightCheckerDatabase.WeightEntryColumns.WEIGHT;

public class WeightEntryRecyclerView extends DividerWrappedRecyclerView {
	private WeightEntryAdapter adapter;

	public WeightEntryRecyclerView(Context context) {
		super(context);
	}

	public WeightEntryRecyclerView(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	public WeightEntryRecyclerView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
	}

	public void setItems(Cursor data) {
		if (adapter == null) {
			adapter = new WeightEntryAdapter();
			this.setAdapter(adapter);
		}
		adapter.setData(data);
	}

	public double getLast() {
		return (adapter != null) ? adapter.getLast() : 0;
	}

	private class WeightEntryAdapter extends Adapter<WeightEntryViewHolder> {
		Cursor items;

		@Override public WeightEntryViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
			View v = LayoutInflater.from(getContext())
					.inflate(R.layout.weight_entry_view, parent, false);
			return new WeightEntryViewHolder(v);
		}

		@Override public void onBindViewHolder(WeightEntryViewHolder holder, int position) {
			if (items.moveToPosition(position)) {
				String date = DateUtils
						.formatDateTime(getContext(), items.getLong(items.getColumnIndex(DATE)),
								DateUtils.FORMAT_ABBREV_ALL | DateUtils.FORMAT_SHOW_YEAR);
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

		public double getLast() {
			items.moveToPosition(items.getCount() - 1);
			return items.getLong(items.getColumnIndex(WEIGHT));
		}
	}

	public static class WeightEntryViewHolder extends ViewHolder {
		@Bind(R.id.date_view) TextView dateView;
		@Bind(R.id.entry_view) TextView entryView;

		public WeightEntryViewHolder(View itemView) {
			super(itemView);
			ButterKnife.bind(this, itemView);
		}

		public void setEntry(String date, String entry) {
			dateView.setText(date);
			entryView.setText(entry);
		}
	}
}
