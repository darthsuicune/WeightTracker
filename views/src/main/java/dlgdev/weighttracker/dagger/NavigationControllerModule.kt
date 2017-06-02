package dlgdev.weighttracker.dagger

import android.support.v7.app.AppCompatActivity

import dagger.Module
import dagger.Provides
import dlgdev.weighttracker.domain.NavigationController
import dlgdev.weighttracker.views.AppNavigation

@Module
class NavigationControllerModule(private val activity: AppCompatActivity) {

    @Provides internal fun provideNavigationController(navigation: AppNavigation): NavigationController {
        return navigation
    }

    @Provides internal fun provideActivity(): AppCompatActivity {
        return activity
    }
}
