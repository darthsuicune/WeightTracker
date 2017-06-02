package dlgdev.weighttracker.views.entry

import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.TextView

import butterknife.BindView
import butterknife.ButterKnife
import dlgdev.weighttracker.R

class WeightEntryViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    @BindView(R.id.date_view) lateinit var dateView: TextView
    @BindView(R.id.entry_view) lateinit var entryView: TextView

    init {
        ButterKnife.bind(this, itemView)
    }

    fun setEntry(date: String, entry: String) {
        dateView.text = date
        entryView.text = entry
    }
}
