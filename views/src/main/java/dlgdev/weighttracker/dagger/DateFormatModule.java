package dlgdev.weighttracker.dagger;

import android.content.Context;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Locale;

import dagger.Module;
import dagger.Provides;

@Module
public class DateFormatModule {

	private Context context;

	public DateFormatModule(Context context) {
		this.context = context;
	}

	@Provides Locale provideLocale() {
		return context.getResources().getConfiguration().locale;
	}

	@Provides DateFormat provideDateFormat(Locale locale) {
		return new SimpleDateFormat("yyyy-MM-dd - HH:mm:ss", locale);
	}
}
