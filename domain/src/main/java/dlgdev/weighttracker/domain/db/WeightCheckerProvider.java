package dlgdev.weighttracker.domain.db;

import android.content.ContentResolver;
import android.net.Uri;

import net.simonvt.schematic.annotation.ContentProvider;
import net.simonvt.schematic.annotation.ContentUri;
import net.simonvt.schematic.annotation.TableEndpoint;

@ContentProvider(authority = WeightCheckerProvider.AUTHORITY,
		database = WeightCheckerDatabase.class)
public final class WeightCheckerProvider {
	public static final String AUTHORITY = "com.dlgdev.weightchecker.WeightCheckerProvider";

	@TableEndpoint(table = WeightCheckerDatabase.WEIGHT_ENTRIES)
	public static class WeightEntries {
		@ContentUri(path = WeightCheckerDatabase.WEIGHT_ENTRIES,
				type = ContentResolver.CURSOR_ITEM_BASE_TYPE + "/weight_entry")
		public static final Uri WEIGHT_ENTRIES_URI =
				Uri.parse(ContentResolver.SCHEME_CONTENT + "://" + AUTHORITY + "/" +
						  WeightCheckerDatabase.WEIGHT_ENTRIES);
	}
}
