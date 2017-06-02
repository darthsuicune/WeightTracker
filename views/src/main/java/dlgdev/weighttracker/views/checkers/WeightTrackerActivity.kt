package dlgdev.weighttracker.views.checkers

import android.database.Cursor
import android.os.Bundle
import android.support.design.widget.NavigationView.OnNavigationItemSelectedListener
import android.support.v4.app.LoaderManager
import android.support.v4.content.CursorLoader
import android.support.v4.content.Loader
import android.support.v4.view.GravityCompat
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.RecyclerView
import android.view.Menu
import android.view.MenuItem
import android.view.View
import butterknife.ButterKnife
import dlgdev.weighttracker.R
import dlgdev.weighttracker.dagger.*
import dlgdev.weighttracker.domain.controllers.checkers.WeightTrackerActions
import dlgdev.weighttracker.domain.controllers.checkers.WeightTrackerRequirements
import dlgdev.weighttracker.domain.db.WeightCheckerProvider
import dlgdev.weighttracker.views.entry.NewWeightEntryDialog
import dlgdev.weighttracker.views.entry.WeightEntryAdapter
import kotlinx.android.synthetic.main.activity_user_main.*
import kotlinx.android.synthetic.main.activity_weight_checker.*
import kotlinx.android.synthetic.main.content_weight_checker.*
import org.joda.time.DateTime
import javax.inject.Inject

class WeightTrackerActivity : AppCompatActivity(), OnNavigationItemSelectedListener, WeightTrackerActions, NewWeightEntryDialog.NewWeightEntryListener {

    @Inject lateinit var controller: WeightTrackerRequirements
    @Inject lateinit var adapter: WeightEntryAdapter
    @Inject lateinit var layoutManager: RecyclerView.LayoutManager


    override fun onCreate(savedInstanceState: Bundle?) {
        val component = DaggerWeightCheckerActivityComponent.builder()
                .weightCheckerActivityModule(WeightCheckerActivityModule(this))
                .navigationControllerModule(NavigationControllerModule(this))
                .dateFormatModule(DateFormatModule(this))
                .repositoriesModule(RepositoriesModule(this))
                .build()
        component.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_weight_checker)
        ButterKnife.bind(this)

        fab.setOnClickListener { controller.requestNewEntry() }

        weight_entry_list.adapter = adapter
        weight_entry_list.layoutManager = layoutManager

        setSupportActionBar(toolbar)

        val toggle = ActionBarDrawerToggle(this, drawer_layout, toolbar,
                R.string.navigation_drawer_open, R.string.navigation_drawer_close)
        drawer_layout.addDrawerListener(toggle)
        toggle.syncState()

        nav_view.setNavigationItemSelectedListener(this)

        supportLoaderManager.restartLoader(LOADER_ENTRIES, Bundle(), EntryLoaderHelper())
    }

    override fun onBackPressed() {
        if (drawer_layout.isDrawerOpen(GravityCompat.START)) {
            drawer_layout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.weight_checker, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will automatically handle clicks on
        // the Home/Up button, so long as you specify a parent activity in AndroidManifest.xml.
        when (item.itemId) {
            R.id.action_settings -> return true
            else -> return super.onOptionsItemSelected(item)
        }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {

        }

        drawer_layout.closeDrawer(GravityCompat.START)
        return true
    }

    override fun showList() {
        empty_entry_list.visibility = View.GONE
        weight_entry_list.visibility = View.VISIBLE
    }

    override fun showEmptyList() {
        empty_entry_list.visibility = View.VISIBLE
        weight_entry_list.visibility = View.GONE
    }

    override fun onNewEntrySelected(date: DateTime, weight: String) {
        controller.addNewEntry(date, weight)
    }

    private inner class EntryLoaderHelper : LoaderManager.LoaderCallbacks<Cursor> {
        override fun onCreateLoader(id: Int, args: Bundle): Loader<Cursor> {
            val uri = WeightCheckerProvider.WeightEntries.WEIGHT_ENTRIES_URI
            return CursorLoader(this@WeightTrackerActivity, uri, null, null, null, null)
        }

        override fun onLoadFinished(loader: Loader<Cursor>, data: Cursor) {
            adapter.setData(data)
            controller.loadEntries(data)
        }

        override fun onLoaderReset(loader: Loader<Cursor>) {

        }
    }

    companion object {
        private val LOADER_ENTRIES = 1
    }
}
