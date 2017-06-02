package dlgdev.weighttracker.dagger

import android.content.Context
import dagger.Module
import dagger.Provides
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*

@Module
class DateFormatModule(private val context: Context) {

    @Provides internal fun provideLocale(): Locale {
        return context.resources.configuration.locale
    }

    @Provides internal fun provideDateFormat(locale: Locale): DateFormat {
        return SimpleDateFormat("yyyy-MM-dd - HH:mm:ss", locale)
    }
}
