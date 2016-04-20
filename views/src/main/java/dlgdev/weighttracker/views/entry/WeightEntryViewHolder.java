package dlgdev.weighttracker.views.entry;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import butterknife.Bind;
import butterknife.ButterKnife;
import dlgdev.weighttracker.R;

public class WeightEntryViewHolder extends RecyclerView.ViewHolder{
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
