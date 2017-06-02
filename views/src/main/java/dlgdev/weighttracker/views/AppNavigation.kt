package dlgdev.weighttracker.views

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import dlgdev.weighttracker.domain.NavigationController
import dlgdev.weighttracker.domain.models.User
import dlgdev.weighttracker.domain.models.WeightEntry
import dlgdev.weighttracker.views.checkers.WeightTrackerActivity
import dlgdev.weighttracker.views.entry.NewWeightEntryDialog
import javax.inject.Inject

class AppNavigation @Inject constructor(val activity: AppCompatActivity) : NavigationController {

    override fun navigateToUserStats(user: User) {
        activity.startActivity(Intent(activity, WeightTrackerActivity::class.java))
    }

    override fun requestEntryData(lastValue: WeightEntry) {
        val fragment = NewWeightEntryDialog.newInstance(lastValue.weight)
        fragment.show(activity.supportFragmentManager, TAG_NEW_WEIGHT_DIALOG)
    }

    companion object {
        private val TAG_NEW_WEIGHT_DIALOG = "tag_new_weight_dialog"
    }
}
