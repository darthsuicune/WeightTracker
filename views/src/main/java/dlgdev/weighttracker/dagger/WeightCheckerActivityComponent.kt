package dlgdev.weighttracker.dagger

import dagger.Component
import dlgdev.weighttracker.views.checkers.WeightTrackerActivity

@Component(modules = arrayOf(WeightCheckerActivityModule::class, NavigationControllerModule::class, DateFormatModule::class, RepositoriesModule::class))
interface WeightCheckerActivityComponent {
    fun inject(activity: WeightTrackerActivity)
}
