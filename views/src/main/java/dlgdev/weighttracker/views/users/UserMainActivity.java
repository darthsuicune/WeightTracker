package dlgdev.weighttracker.views.users;

import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;
import dlgdev.weighttracker.R;
import dlgdev.weighttracker.dagger.DaggerUserMainActivityComponent;
import dlgdev.weighttracker.dagger.NavigationControllerModule;
import dlgdev.weighttracker.dagger.RepositoriesModule;
import dlgdev.weighttracker.dagger.UserMainActivityComponent;
import dlgdev.weighttracker.domain.controllers.users.UserMainActivityController;
import dlgdev.weighttracker.domain.db.WeightCheckerProvider;

public class UserMainActivity extends AppCompatActivity {
	private static final int LOADER_DATA = 1;

	UserMainActivityComponent component;
	@Inject UserMainActivityController controller;

	@Bind(R.id.toolbar) Toolbar toolbar;
	@Bind(R.id.user_data_card) UserDataCard dataCard;

	@Override protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_user_main);
		ButterKnife.bind(this);
		setSupportActionBar(toolbar);
		component = DaggerUserMainActivityComponent.builder()
				.navigationControllerModule(new NavigationControllerModule(this))
				.repositoriesModule(new RepositoriesModule(this))
				.build();
		component.inject(this);

		getSupportLoaderManager().restartLoader(LOADER_DATA, null, new EntriesHelper());
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

	private class EntriesHelper implements LoaderManager.LoaderCallbacks<Cursor> {
		@Override public Loader<Cursor> onCreateLoader(int id, Bundle args) {
			Uri uri = WeightCheckerProvider.WeightEntries.WEIGHT_ENTRIES_URI;
			return new CursorLoader(UserMainActivity.this, uri, null, null, null, null);
		}

		@Override public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
			controller.displayData(data, dataCard);
		}

		@Override public void onLoaderReset(Loader<Cursor> loader) {

		}
	}
}
