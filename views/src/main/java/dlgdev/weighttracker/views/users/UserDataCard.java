package dlgdev.weighttracker.views.users;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.util.AttributeSet;
import android.widget.TextView;

import butterknife.Bind;
import butterknife.ButterKnife;
import dlgdev.weighttracker.R;
import dlgdev.weighttracker.domain.models.UserDataCardInterface;

public class UserDataCard extends CardView implements UserDataCardInterface {

	@Bind(R.id.ten_day_average_weight) TextView averageWeight;

	public UserDataCard(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	@Override protected void onFinishInflate() {
		super.onFinishInflate();
		ButterKnife.bind(this);
	}

	@Override public void showAverage(double average) {
		averageWeight.setText(getContext().getString(R.string.ten_day_average_weight, average));
	}
}
