package dlgdev.weighttracker.domain.controllers.users;

import android.database.Cursor;

import java.util.List;

import javax.inject.Inject;

import dlgdev.weighttracker.domain.MathUtils;
import dlgdev.weighttracker.domain.NavigationController;
import dlgdev.weighttracker.domain.WeightEntryRepository;
import dlgdev.weighttracker.domain.models.User;
import dlgdev.weighttracker.domain.models.UserDataCardInterface;
import dlgdev.weighttracker.domain.models.WeightEntry;

public class UserMainActivityControllerImpl implements UserMainActivityController {

	NavigationController navigation;
	WeightEntryRepository repository;
	UserDataCardPresenter presenter;
	User user;

	@Inject public UserMainActivityControllerImpl(NavigationController navigation,
										   WeightEntryRepository repository,
										   UserDataCardPresenter presenter,
												  User user) {
		this.navigation = navigation;
		this.repository = repository;
		this.presenter = presenter;
		this.user = user;
	}

	@Override public void displayData(Cursor data, UserDataCardInterface dataCard) {
		List<WeightEntry> entries = repository.loadEntries(data);
		double[] values = new double[entries.size()];
		for(int i = 0, len = entries.size(); i < len; i++) {
			values[i] = entries.get(i).weight;
		}
		presenter.setDataCard(dataCard);
		this.presenter.showAverage(MathUtils.average(values));
	}

	@Override public void addNewEntries() {
		navigation.navigateToUserStats(user);
	}
}
