package dlgdev.weighttracker.domain.controllers.users;

import dlgdev.weighttracker.domain.models.UserDataCardInterface;

public interface UserDataCardPresenter {
	void setDataCard(UserDataCardInterface dataCard);

	void showAverage(double average);
}
