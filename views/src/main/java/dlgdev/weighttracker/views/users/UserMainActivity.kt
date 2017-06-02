package dlgdev.weighttracker.views.users

import android.database.Cursor
import android.os.Bundle
import android.support.v4.app.LoaderManager
import android.support.v4.content.CursorLoader
import android.support.v4.content.Loader
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import butterknife.ButterKnife
import dlgdev.weighttracker.R
import dlgdev.weighttracker.dagger.DaggerUserMainActivityComponent
import dlgdev.weighttracker.dagger.NavigationControllerModule
import dlgdev.weighttracker.dagger.RepositoriesModule
import dlgdev.weighttracker.dagger.UserMainActivityComponent
import dlgdev.weighttracker.domain.controllers.users.UserMainActivityController
import dlgdev.weighttracker.domain.db.WeightCheckerProvider
import kotlinx.android.synthetic.main.activity_user_main.*
import kotlinx.android.synthetic.main.content_user_main.*
import javax.inject.Inject

class UserMainActivity : AppCompatActivity() {

    lateinit var component: UserMainActivityComponent
    @Inject lateinit var controller: UserMainActivityController

    override fun onCreate(savedInstanceState: Bundle?) {
        component = DaggerUserMainActivityComponent.builder()
                .navigationControllerModule(NavigationControllerModule(this))
                .repositoriesModule(RepositoriesModule(this))
                .build()
        component.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_main)
        ButterKnife.bind(this)
        setSupportActionBar(toolbar)

        fab.setOnClickListener { controller.addNewEntries() }

        supportLoaderManager.restartLoader(LOADER_DATA, null, EntriesHelper())
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_users, menu)
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

    private inner class EntriesHelper : LoaderManager.LoaderCallbacks<Cursor> {
        override fun onCreateLoader(id: Int, args: Bundle?): Loader<Cursor> {
            val uri = WeightCheckerProvider.WeightEntries.WEIGHT_ENTRIES_URI
            return CursorLoader(this@UserMainActivity, uri, null, null, null, null)
        }

        override fun onLoadFinished(loader: Loader<Cursor>, data: Cursor) {
            controller.displayData(data, user_data_card)
        }

        override fun onLoaderReset(loader: Loader<Cursor>) {

        }
    }

    companion object {
        private val LOADER_DATA = 1
    }
}
