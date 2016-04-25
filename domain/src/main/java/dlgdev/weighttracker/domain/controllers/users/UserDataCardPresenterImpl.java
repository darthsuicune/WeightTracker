package dlgdev.weighttracker.domain.controllers.users;

import javax.inject.Inject;

import dlgdev.weighttracker.domain.models.UserDataCardInterface;

public class UserDataCardPresenterImpl implements UserDataCardPresenter {
	private UserDataCardInterface dataCard;

	@Inject public UserDataCardPresenterImpl() {}

	@Override public void setDataCard(UserDataCardInterface dataCard) {
		this.dataCard = dataCard;
	}

	@Override public void showAverage(double average) {
		dataCard.showAverage(average);
	}
}
