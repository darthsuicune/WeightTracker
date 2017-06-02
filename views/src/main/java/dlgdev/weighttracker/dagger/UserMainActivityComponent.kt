package dlgdev.weighttracker.dagger

import dagger.Component
import dlgdev.weighttracker.views.users.UserMainActivity

@Component(modules = arrayOf(UserMainActivityModule::class, NavigationControllerModule::class, RepositoriesModule::class))
interface UserMainActivityComponent {
    fun inject(activity: UserMainActivity)
}
