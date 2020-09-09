package com.arctouch.io.racemapper.scenes.main

import android.os.Bundle
import android.support.design.widget.NavigationView
import android.support.v4.view.GravityCompat
import android.support.v7.app.ActionBarDrawerToggle
import android.view.Menu
import android.view.MenuItem
import com.arctouch.io.racemapper.R
import com.arctouch.io.racemapper.base.BaseActivity
import com.arctouch.io.racemapper.base.BaseContract
import com.arctouch.io.racemapper.core.RaceMapperApplication
import com.arctouch.io.racemapper.di.component.DaggerMainComponent
import com.arctouch.io.racemapper.di.component.MainComponent
import com.arctouch.io.racemapper.extensions.invisible
import com.arctouch.io.racemapper.extensions.visible
import com.arctouch.io.racemapper.scenes.history.HistoryFragment
import com.arctouch.io.racemapper.scenes.run.RunFragment
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.app_bar_main.*
import kotlinx.android.synthetic.main.toolbar.*

class MainActivity: BaseActivity<BaseContract.View, MainPresenter, MainComponent>(),
                    NavigationView.OnNavigationItemSelectedListener {

    private var runFragment: RunFragment? = null
    private var historyFragment: HistoryFragment? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        val toggle = ActionBarDrawerToggle(
            this, drawer_layout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close
        )
        drawer_layout.addDrawerListener(toggle)
        toggle.syncState()

        nav_view.setNavigationItemSelectedListener(this)
        containerId = R.id.containerIdFL

        if (savedInstanceState == null) {
            nav_view.menu.performIdentifierAction(R.id.nav_run, 0)
        }
    }

    override fun createComponent(): MainComponent = DaggerMainComponent.builder()
                                                        .applicationComponent(RaceMapperApplication.component).build()

    override fun onBackPressed() {
        if (drawer_layout.isDrawerOpen(GravityCompat.START)) {
            drawer_layout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun goToRunFragment() {
        runFragment ?: run {
            runFragment = RunFragment.newInstance()
        }
        titleTXT.text = getString(R.string.title_run)
        openFragment(runFragment!!)
    }

    private fun goToHistoryFragment() {
        historyFragment ?: run {
            historyFragment = HistoryFragment.newInstance()
        }
        titleTXT.text = getString(R.string.title_run_history)
        openFragment(historyFragment!!)
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.nav_run -> {
                goToRunFragment()
            }
            R.id.nav_run_history -> {
                goToHistoryFragment()
            }
            R.id.nav_weather -> {

            }
        }

        drawer_layout.closeDrawer(GravityCompat.START)
        return true
    }

    override fun showError(error: String) {
        toast(error)
    }

    override fun showLoading() {
        serverPB?.visible()
    }

    override fun hideLoading() {
        serverPB?.invisible()
    }
}