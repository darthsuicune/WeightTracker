package dlgdev.weighttracker.dagger

import android.content.ContentResolver
import android.content.Context

import dagger.Module
import dagger.Provides
import dlgdev.weighttracker.domain.WeightEntryRepository
import dlgdev.weighttracker.domain.WeightEntryRepositoryImpl

@Module
class RepositoriesModule(private val context: Context) {

    @Provides internal fun provideRepository(repo: WeightEntryRepositoryImpl): WeightEntryRepository {
        return repo
    }

    @Provides internal fun provideResolver(): ContentResolver {
        return context.contentResolver
    }

}
