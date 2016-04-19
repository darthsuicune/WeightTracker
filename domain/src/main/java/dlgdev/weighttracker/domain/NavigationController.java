package dlgdev.weighttracker.domain;

import dlgdev.weighttracker.domain.models.User;

public interface NavigationController {
	void navigateToUserStats(User user);

	void requestEntryData(double lastValue);
}
