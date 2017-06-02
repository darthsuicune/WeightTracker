package dlgdev.weighttracker.dagger

import dagger.Module
import dagger.Provides
import dlgdev.weighttracker.domain.controllers.users.UserDataCardPresenter
import dlgdev.weighttracker.domain.controllers.users.UserDataCardPresenterImpl
import dlgdev.weighttracker.domain.controllers.users.UserMainActivityController
import dlgdev.weighttracker.domain.controllers.users.UserMainActivityControllerImpl
import dlgdev.weighttracker.domain.models.User

@Module
class UserMainActivityModule {

    @Provides
    internal fun provideController(implementation: UserMainActivityControllerImpl): UserMainActivityController {
        return implementation
    }

    @Provides internal fun provideDataCardInterface(presenter: UserDataCardPresenterImpl): UserDataCardPresenter {
        return presenter
    }

    @Provides fun provideUser() : User {
        return User()
    }
}
