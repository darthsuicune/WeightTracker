package dlgdev.weighttracker.views.entry;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.DatePicker;

import com.dlgdev.views.DialogBasedFragment;

import org.joda.time.DateTime;

import dlgdev.weighttracker.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class NewWeightEntryDialog extends DialogFragment {

	private static final String TAG_DATE_PICKER = "date picker";
	private static final String KEY_DATE = "date";
	private static final String KEY_DEFAULT_VALUE = "default value";
	private NewWeightEntryListener listener;
	private DateTime date = DateTime.now();
	private double defaultValue;

	public NewWeightEntryDialog() {
		// Required empty public constructor
	}

	@Override public void onAttach(Context context) {
		super.onAttach(context);
		listener = (NewWeightEntryListener) context;
	}

	@Override public void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState);
		outState.putLong(KEY_DATE, date.getMillis());
		outState.putDouble(KEY_DEFAULT_VALUE, defaultValue);
	}

	@NonNull @Override public Dialog onCreateDialog(Bundle savedInstanceState) {
		final NewWeightEntryView entryView = (NewWeightEntryView) LayoutInflater.from(getContext())
				.inflate(R.layout.fragment_new_weight_entry_dialog, null);
		defaultValue(savedInstanceState);
		entryView.setup(date(savedInstanceState), new View.OnClickListener() {
			@Override public void onClick(View view) {
				showPickerDialog(entryView);
			}
		}, 0, 250, defaultValue);
		return getNewEntryDialog(entryView);
	}

	private void defaultValue(Bundle savedInstanceState) {
		if(savedInstanceState != null && savedInstanceState.containsKey(KEY_DEFAULT_VALUE)) {
			defaultValue = savedInstanceState.getLong(KEY_DEFAULT_VALUE);
		}
	}

	public AlertDialog getNewEntryDialog(final NewWeightEntryView entryView) {
		return new AlertDialog.Builder(getContext())
				.setView(entryView)
				.setTitle(R.string.add_entry)
				.setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
					@Override public void onClick(DialogInterface dialogInterface, int i) {
						if (!Double.isNaN(Double.parseDouble(entryView.getValue()))) {
							listener.onNewEntrySelected(date, entryView.getValue());
						}
					}
				}).create();
	}

	private void showPickerDialog(final NewWeightEntryView entryView) {
		DialogBasedFragment.setWithDialog(new DatePickerDialog(getContext(),
				new DatePickerDialog.OnDateSetListener() {
					@Override
					public void onDateSet(DatePicker picker, int year, int month, int day) {
						date = date.withDate(year, month + 1, day);
						entryView.setDate(date);
					}
				}, date.getYear(), date.getMonthOfYear(), date.getDayOfMonth()))
				.show(getFragmentManager(), TAG_DATE_PICKER);
	}

	private DateTime date(Bundle savedState) {
		date = (savedState != null && savedState.containsKey(KEY_DATE)) ?
				new DateTime(savedState.getLong(KEY_DATE)) : DateTime.now();
		return date;
	}

	public static DialogFragment newInstance(double lastValue) {
		NewWeightEntryDialog fragment = new NewWeightEntryDialog();
		Bundle args = new Bundle();
		args.putDouble(KEY_DEFAULT_VALUE, lastValue);
		fragment.setArguments(args);
		return fragment;
	}

	public interface NewWeightEntryListener {
		void onNewEntrySelected(DateTime date, String weight);
	}
}
