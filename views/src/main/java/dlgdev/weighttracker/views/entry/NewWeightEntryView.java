package dlgdev.weighttracker.views.entry;

import android.content.Context;
import android.text.format.DateUtils;
import android.util.AttributeSet;
import android.widget.NumberPicker;
import android.widget.RelativeLayout;
import android.widget.TextView;

import org.joda.time.DateTime;

import butterknife.Bind;
import butterknife.ButterKnife;
import dlgdev.weighttracker.R;

public class NewWeightEntryView extends RelativeLayout {
	@Bind(R.id.date_view) TextView dateView;
	@Bind(R.id.value_view) NumberPicker valueView;

	public NewWeightEntryView(Context context) {
		super(context);
	}

	public NewWeightEntryView(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	public NewWeightEntryView(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
	}

	public void setup(DateTime date, OnClickListener listener, int min, int max, double value) {
		ButterKnife.bind(this);
		setDate(date);
		dateView.setOnClickListener(listener);
		valueView.setMaxValue(max);
		valueView.setMinValue(min);
		valueView.setValue((int)value);
	}

	public void setDate(DateTime date) {
		dateView.setText(DateUtils.formatDateTime(getContext(), date.getMillis(),
				DateUtils.FORMAT_ABBREV_ALL | DateUtils.FORMAT_SHOW_YEAR));
	}

	public String getValue() {
		return Integer.toString(valueView.getValue());
	}
}
