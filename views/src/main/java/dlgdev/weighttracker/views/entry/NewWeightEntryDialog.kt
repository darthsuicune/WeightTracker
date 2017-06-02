package dlgdev.weighttracker.views.entry

import android.app.DatePickerDialog
import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.support.v4.app.DialogFragment
import android.support.v7.app.AlertDialog
import android.view.LayoutInflater
import android.view.View
import com.dlgdev.views.DialogBasedFragment
import dlgdev.weighttracker.R
import org.joda.time.DateTime

class NewWeightEntryDialog : DialogFragment() {
    lateinit var listener: NewWeightEntryListener
    var date = DateTime.now()
    var defaultValue: Double = 0.toDouble()

    override fun onAttach(context: Context) {
        super.onAttach(context)
        listener = context as NewWeightEntryListener
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putLong(KEY_DATE, date.millis)
        outState.putDouble(KEY_DEFAULT_VALUE, defaultValue)
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val entryView = LayoutInflater.from(context)
                .inflate(R.layout.fragment_new_weight_entry_dialog, null) as NewWeightEntryView
        defaultValue(savedInstanceState)
        entryView.setup(date(savedInstanceState), 0, 250, defaultValue,
                View.OnClickListener{ showPickerDialog(entryView) })
        return getNewEntryDialog(entryView)
    }

    private fun defaultValue(savedInstanceState: Bundle?) {
        if (savedInstanceState != null && savedInstanceState.containsKey(KEY_DEFAULT_VALUE)) {
            defaultValue = savedInstanceState.getLong(KEY_DEFAULT_VALUE).toDouble()
        }
    }

    fun getNewEntryDialog(entryView: NewWeightEntryView): AlertDialog {
        return AlertDialog.Builder(context)
                .setView(entryView)
                .setTitle(R.string.add_entry)
                .setPositiveButton(android.R.string.ok) { dialogInterface, i ->
                    if (!java.lang.Double.isNaN(java.lang.Double.parseDouble(entryView.value))) {
                        listener.onNewEntrySelected(date, entryView.value)
                    }
                }.create()
    }

    private fun showPickerDialog(entryView: NewWeightEntryView) {
        DialogBasedFragment.createWithDialog(DatePickerDialog(context,
                DatePickerDialog.OnDateSetListener { picker, year, month, day ->
                    date = date.withDate(year, month + 1, day)
                    entryView.setDate(date)
                }, date.year, date.monthOfYear, date.dayOfMonth))
                .show(fragmentManager, TAG_DATE_PICKER)
    }

    private fun date(savedState: Bundle?): DateTime {
        date = if (savedState != null && savedState.containsKey(KEY_DATE))
            DateTime(savedState.getLong(KEY_DATE))
        else
            DateTime.now()
        return date
    }

    interface NewWeightEntryListener {
        fun onNewEntrySelected(date: DateTime, weight: String)
    }

    companion object {

        private val TAG_DATE_PICKER = "date picker"
        private val KEY_DATE = "date"
        private val KEY_DEFAULT_VALUE = "default value"

        fun newInstance(lastValue: Double): DialogFragment {
            val fragment = NewWeightEntryDialog()
            val args = Bundle()
            args.putDouble(KEY_DEFAULT_VALUE, lastValue)
            fragment.arguments = args
            return fragment
        }
    }
}
