package dlgdev.weighttracker.dagger

import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import dagger.Module
import dagger.Provides
import dlgdev.weighttracker.domain.controllers.checkers.WeightTrackerActions
import dlgdev.weighttracker.domain.controllers.checkers.WeightTrackerController
import dlgdev.weighttracker.domain.controllers.checkers.WeightTrackerRequirements
import dlgdev.weighttracker.views.checkers.WeightTrackerActivity

@Module
class WeightCheckerActivityModule(internal var activity: WeightTrackerActivity) {

    @Provides internal fun provideController(impl: WeightTrackerController): WeightTrackerRequirements {
        return impl
    }

    @Provides internal fun provideActionsCallback(): WeightTrackerActions {
        return activity
    }

    @Provides internal fun provideManager(): RecyclerView.LayoutManager {
        return LinearLayoutManager(activity)
    }
}
