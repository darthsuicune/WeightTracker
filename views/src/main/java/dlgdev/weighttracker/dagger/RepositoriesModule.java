package dlgdev.weighttracker.dagger;

import android.content.ContentResolver;
import android.content.Context;

import dagger.Module;
import dagger.Provides;
import dlgdev.weighttracker.domain.WeightEntryRepository;
import dlgdev.weighttracker.domain.WeightEntryRepositoryImpl;

@Module
public class RepositoriesModule {

	private Context context;

	public RepositoriesModule(Context context) {
		this.context = context;
	}

	@Provides WeightEntryRepository provideRepository(WeightEntryRepositoryImpl repo) {
		return repo;
	}

	@Provides ContentResolver provideResolver() {
		return context.getContentResolver();
	}

}
