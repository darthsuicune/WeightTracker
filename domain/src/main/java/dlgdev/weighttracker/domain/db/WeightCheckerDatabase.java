package dlgdev.weighttracker.domain.db;

import net.simonvt.schematic.annotation.AutoIncrement;
import net.simonvt.schematic.annotation.DataType;
import net.simonvt.schematic.annotation.Database;
import net.simonvt.schematic.annotation.NotNull;
import net.simonvt.schematic.annotation.PrimaryKey;
import net.simonvt.schematic.annotation.Table;

import static net.simonvt.schematic.annotation.DataType.Type.INTEGER;
import static net.simonvt.schematic.annotation.DataType.Type.TEXT;

@Database(version = WeightCheckerDatabase.VERSION)
public final class WeightCheckerDatabase {
	public static final int VERSION = 1;
	@Table(WeightEntryColumns.class) public static final String WEIGHT_ENTRIES = "weight_entries";

	public interface WeightEntryColumns {
		@DataType(INTEGER) @PrimaryKey @AutoIncrement String _ID = "_id";
		@DataType(INTEGER) String USER_ID = "user_id";
		@DataType(TEXT) @NotNull String DATE = "date";
		@DataType(TEXT) @NotNull String WEIGHT = "weight";
	}
}
