package dlgdev.weighttracker.domain.controllers.users;

import android.database.Cursor;

import dlgdev.weighttracker.domain.models.UserDataCardInterface;

public interface UserMainActivityController {
	void displayData(Cursor data, UserDataCardInterface dataCard);
	void addNewEntries();
}