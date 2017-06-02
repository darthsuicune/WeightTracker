package dlgdev.weighttracker.views.entry

import android.database.Cursor
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import dlgdev.weighttracker.R
import dlgdev.weighttracker.domain.db.WeightCheckerDatabase.WeightEntryColumns.DATE
import dlgdev.weighttracker.domain.db.WeightCheckerDatabase.WeightEntryColumns.WEIGHT
import java.text.DateFormat
import java.util.*
import javax.inject.Inject

class WeightEntryAdapter @Inject constructor(internal var dateFormat: DateFormat) : RecyclerView.Adapter<WeightEntryViewHolder>() {
    var items: Cursor? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WeightEntryViewHolder {
        val v = LayoutInflater.from(parent.context)
                .inflate(R.layout.weight_entry_view, parent, false)
        return WeightEntryViewHolder(v)
    }

    override fun onBindViewHolder(holder: WeightEntryViewHolder, position: Int) {
        if (items != null && items!!.moveToPosition(position)) {
            val date = dateFormat.format(Date(items!!.getLong(items!!.getColumnIndex(DATE))))
            val entry = items!!.getString(items!!.getColumnIndex(WEIGHT))
            holder.setEntry(date, entry)
        }
    }

    override fun getItemCount(): Int {
        return if(items != null) items!!.count else 0
    }

    fun setData(data: Cursor) {
        this.items = data
        this.notifyDataSetChanged()
    }
}
