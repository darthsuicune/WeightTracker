package dlgdev.weighttracker.views;

import android.content.Intent;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;

import javax.inject.Inject;

import dlgdev.weighttracker.domain.NavigationController;
import dlgdev.weighttracker.domain.models.User;
import dlgdev.weighttracker.domain.models.WeightEntry;
import dlgdev.weighttracker.views.checkers.WeightTrackerActivity;
import dlgdev.weighttracker.views.entry.NewWeightEntryDialog;

public class AppNavigation implements NavigationController {
	private static final String TAG_NEW_WEIGHT_DIALOG = "tag_new_weight_dialog";
	AppCompatActivity activity;

	@Inject public AppNavigation(AppCompatActivity activity) {
		this.activity = activity;
	}

	@Override public void navigateToUserStats(User user) {
		activity.startActivity(new Intent(activity, WeightTrackerActivity.class));
	}

	@Override public void requestEntryData(WeightEntry lastValue) {
		DialogFragment fragment = NewWeightEntryDialog.newInstance(lastValue.weight);
		fragment.show(activity.getSupportFragmentManager(), TAG_NEW_WEIGHT_DIALOG);
	}
}
