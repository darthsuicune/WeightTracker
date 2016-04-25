package dlgdev.weighttracker.domain;

import dlgdev.weighttracker.domain.models.User;
import dlgdev.weighttracker.domain.models.WeightEntry;

public interface NavigationController {
	void navigateToUserStats(User user);

	void requestEntryData(WeightEntry lastValue);
}
