package dlgdev.weighttracker.views.users;

import android.os.Bundle;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import java.util.List;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import dlgdev.weighttracker.R;
import dlgdev.weighttracker.dagger.DaggerUsersActivityComponent;
import dlgdev.weighttracker.dagger.NavigationControllerModule;
import dlgdev.weighttracker.dagger.UsersActivityModule;
import dlgdev.weighttracker.domain.models.User;
import dlgdev.weighttracker.domain.models.users.UsersActivityActions;
import dlgdev.weighttracker.domain.models.users.UsersActivityController;

public class UsersActivity extends AppCompatActivity implements UsersActivityActions {
	private static final int LOADER_USERS = 1;

	@Inject UsersActivityController controller;

	@Bind(R.id.toolbar) Toolbar toolbar;

	@Override protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		DaggerUsersActivityComponent.builder()
				.usersActivityModule(new UsersActivityModule(this))
				.navigationControllerModule(new NavigationControllerModule(this))
				.build().inject(this);

		//TODO: Fix this when users are added.
		if(true) {
			controller.selectUser(null);
			finish();
		} else {
			getSupportLoaderManager().restartLoader(LOADER_USERS, null, new UsersLoaderHelper());
		}
	}

	@Override public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.menu_users, menu);
		return true;
	}

	@Override public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will automatically handle clicks on
		// the Home/Up button, so long as you specify a parent activity in AndroidManifest.xml.
		switch(item.getItemId()) {
			case R.id.action_settings:
				return true;
			default:
				return super.onOptionsItemSelected(item);
		}
	}
	
	@Override public void loadUserList(List<User> userList) {
		setContentView(R.layout.activity_users);
		ButterKnife.bind(this);
		setSupportActionBar(toolbar);
	}

	@OnClick(R.id.fab) public void onFabClick() {
		controller.addUser();
	}

	private class UsersLoaderHelper implements LoaderManager.LoaderCallbacks<List<User>> {
		@Override public Loader<List<User>> onCreateLoader(int id, Bundle args) {
			return null;
		}

		@Override public void onLoadFinished(Loader<List<User>> loader, List<User> data) {
			controller.loadUsers(data);
		}

		@Override public void onLoaderReset(Loader<List<User>> loader) {

		}
	}
}
