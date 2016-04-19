package dlgdev.weighttracker.views.checkers;

import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.NavigationView.OnNavigationItemSelectedListener;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import org.joda.time.DateTime;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import dlgdev.weighttracker.R;
import dlgdev.weighttracker.di.DaggerWeightCheckerActivityComponent;
import dlgdev.weighttracker.di.NavigationControllerModule;
import dlgdev.weighttracker.di.WeightCheckerActivityModule;
import dlgdev.weighttracker.domain.db.WeightCheckerProvider;
import dlgdev.weighttracker.domain.models.checkers.WeightTrackerActions;
import dlgdev.weighttracker.domain.models.checkers.WeightTrackerRequirements;
import dlgdev.weighttracker.views.WeightEntryRecyclerView;
import dlgdev.weighttracker.views.entry.NewWeightEntryDialog;

public class WeightTrackerActivity extends AppCompatActivity
		implements OnNavigationItemSelectedListener, WeightTrackerActions,
		NewWeightEntryDialog.NewWeightEntryListener{

	private static final int LOADER_ENTRIES = 1;

	@Inject WeightTrackerRequirements controller;

	@Bind(R.id.fab) FloatingActionButton fab;
	@Bind(R.id.drawer_layout) DrawerLayout drawer;
	@Bind(R.id.toolbar) Toolbar toolbar;
	@Bind(R.id.nav_view) NavigationView navigationView;

	@Bind(R.id.empty_entry_list) TextView emptyListView;
	@Bind(R.id.weight_entry_list) WeightEntryRecyclerView recyclerListView;

	@Override protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		DaggerWeightCheckerActivityComponent.builder()
				.weightCheckerActivityModule(new WeightCheckerActivityModule(this))
				.navigationControllerModule(new NavigationControllerModule(this))
				.build().inject(this);
		setContentView(R.layout.activity_weight_checker);
		ButterKnife.bind(this);

		setSupportActionBar(toolbar);

		ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar,
				R.string.navigation_drawer_open, R.string.navigation_drawer_close);
		drawer.addDrawerListener(toggle);
		toggle.syncState();

		navigationView.setNavigationItemSelectedListener(this);

		getSupportLoaderManager().restartLoader(LOADER_ENTRIES, null, new EntryLoaderHelper());
	}

	@Override public void onBackPressed() {
		if (drawer.isDrawerOpen(GravityCompat.START)) {
			drawer.closeDrawer(GravityCompat.START);
		} else {
			super.onBackPressed();
		}
	}

	@Override public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.weight_checker, menu);
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

	@Override public boolean onNavigationItemSelected(MenuItem item) {
		// Handle navigation view item clicks here.
		switch(item.getItemId()) {
			case R.id.nav_share:
			default:
		}

		drawer.closeDrawer(GravityCompat.START);
		return true;
	}

	@OnClick(R.id.fab) public void clickOnFab() {
		controller.requestNewEntry(recyclerListView.getLast());
	}

	@Override public void showList(Cursor data) {
		emptyListView.setVisibility(View.GONE);
		recyclerListView.setVisibility(View.VISIBLE);
		recyclerListView.setItems(data);
	}

	@Override public void showEmptyList() {
		emptyListView.setVisibility(View.VISIBLE);
		recyclerListView.setVisibility(View.GONE);
	}

	@Override public void onNewEntrySelected(DateTime date, String weight) {
		controller.addNewEntry(date, weight);
	}

	private class EntryLoaderHelper implements LoaderManager.LoaderCallbacks<Cursor> {
		@Override public Loader<Cursor> onCreateLoader(int id, Bundle args) {
			Uri uri = WeightCheckerProvider.WeightEntries.WEIGHT_ENTRIES_URI;
			return new CursorLoader(WeightTrackerActivity.this, uri, null, null, null, null);
		}

		@Override public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
			controller.loadEntries(data);
		}

		@Override public void onLoaderReset(Loader<Cursor> loader) {

		}
	}
}
