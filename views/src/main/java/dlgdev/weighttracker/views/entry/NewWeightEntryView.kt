package dlgdev.weighttracker.views.entry

import android.content.Context
import android.text.format.DateUtils
import android.util.AttributeSet
import android.view.View
import android.widget.NumberPicker
import android.widget.RelativeLayout
import android.widget.TextView
import butterknife.BindView
import butterknife.ButterKnife
import dlgdev.weighttracker.R
import org.joda.time.DateTime

class NewWeightEntryView : RelativeLayout {
    @BindView(R.id.date_view) lateinit var dateView: TextView
    @BindView(R.id.value_view) lateinit var valueView: NumberPicker

    constructor(context: Context) : super(context)

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs)

    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(context, attrs, defStyleAttr)

    fun setup(date: DateTime, min: Int, max: Int, value: Double, listener: View.OnClickListener) {
        ButterKnife.bind(this)
        setDate(date)
        dateView.setOnClickListener(listener)
        valueView.maxValue = max
        valueView.minValue = min
        valueView.value = value.toInt()
    }

    fun setDate(date: DateTime) {
        dateView.text = DateUtils.formatDateTime(context, date.millis,
                DateUtils.FORMAT_ABBREV_ALL or DateUtils.FORMAT_SHOW_YEAR)
    }

    val value: String
        get() = Integer.toString(valueView.value)
}
