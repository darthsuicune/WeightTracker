package dlgdev.weighttracker.views.users

import android.content.Context
import android.support.v7.widget.CardView
import android.util.AttributeSet
import android.widget.TextView

import butterknife.BindView
import butterknife.ButterKnife
import dlgdev.weighttracker.R
import dlgdev.weighttracker.domain.models.UserDataCardInterface

class UserDataCard(context: Context, attrs: AttributeSet) : CardView(context, attrs), UserDataCardInterface {

    @BindView(R.id.ten_day_average_weight) lateinit var averageWeight: TextView

    override fun onFinishInflate() {
        super.onFinishInflate()
        ButterKnife.bind(this)
    }

    override fun showAverage(average: Double) {
        averageWeight.text = context.getString(R.string.ten_day_average_weight, average)
    }
}
