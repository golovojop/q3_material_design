package k.s.yarlykov.materialdesign

import android.os.Bundle
import android.support.design.widget.FloatingActionButton
import android.support.design.widget.Snackbar
import android.support.v4.view.GravityCompat
import android.support.v7.app.ActionBarDrawerToggle
import android.view.MenuItem
import android.support.v4.widget.DrawerLayout
import android.support.design.widget.NavigationView
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.view.Menu
import android.view.View
import android.widget.LinearLayout
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.app_bar_main.*
import kotlinx.android.synthetic.main.nav_header_main.*

class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        // Установка темы
        setTheme(lastTheme)

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
        }

        val toggle = ActionBarDrawerToggle(
            this, drawer_layout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close
        )
        drawer_layout.addDrawerListener(toggle)
        toggle.syncState()

        nav_view.setNavigationItemSelectedListener(this)

        // Сменить фон заголовка в выдвигающейся шторке
        val header = nav_view.getHeaderView(0)
        header.findViewById<LinearLayout>(R.id.ll_nav_header)?.let {
            setNavHeaderBackground(it)
        }
    }

    override fun onBackPressed() {
        val drawerLayout: DrawerLayout = findViewById(R.id.drawer_layout)
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        // Handle navigation view item clicks here.
        when (item.itemId) {
            R.id.nav_theme_dob -> {
                lastTheme = R.style.AppThemeDob
                recreate()
            }
            R.id.nav_theme_ic -> {
                lastTheme = R.style.AppThemeIc
                recreate()
            }
            R.id.nav_theme_pg -> {
                lastTheme = R.style.AppThemePg
                recreate()
            }
            R.id.nav_theme_default -> {
                lastTheme = R.style.AppTheme
                recreate()
            }
            R.id.nav_share -> {

            }
            R.id.nav_send -> {

            }
        }
        val drawerLayout: DrawerLayout = findViewById(R.id.drawer_layout)
        drawerLayout.closeDrawer(GravityCompat.START)
        return true
    }

    private fun setNavHeaderBackground(header: View) {

        val bgId = when (lastTheme) {
            R.style.AppThemeDob -> R.drawable.side_nav_bar_dob
            R.style.AppThemeIc -> R.drawable.side_nav_bar_ic
            R.style.AppThemePg -> R.drawable.side_nav_bar_pg
            else -> R.drawable.side_nav_bar
        }

        header.background = ContextCompat.getDrawable(this, bgId)
    }

    companion object {
        private var lastTheme: Int = R.style.AppTheme
    }
}


