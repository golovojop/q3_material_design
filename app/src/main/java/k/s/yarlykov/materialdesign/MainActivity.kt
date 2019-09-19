package k.s.yarlykov.materialdesign

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.LinearLayout
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.core.content.ContextCompat
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.material.navigation.NavigationView
import com.google.android.material.snackbar.Snackbar
import k.s.yarlykov.materialdesign.extentions.create
import k.s.yarlykov.materialdesign.ui.BaseActivity
import k.s.yarlykov.materialdesign.ui.lesson1.LessonOneActivity
import k.s.yarlykov.materialdesign.ui.lesson2.BottomSheetActivity
import k.s.yarlykov.materialdesign.ui.lesson2.EditTextSnackbarActivity
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.app_bar_main.*

class MainActivity : BaseActivity(), NavigationView.OnNavigationItemSelectedListener {

    override fun onCreate(savedInstanceState: Bundle?) {

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
                updateTheme(R.style.AppThemeDob)
                recreate()
            }
            R.id.nav_theme_ic -> {
                updateTheme(R.style.AppThemeIc)
                recreate()
            }
            R.id.nav_theme_pg -> {
                updateTheme(R.style.AppThemePg)
                recreate()
            }
            R.id.nav_theme_default -> {
                updateTheme(R.style.AppTheme)
                recreate()
            }
            R.id.nav_start_l1 -> {
                this.create(LessonOneActivity::class.java)
            }
            R.id.nav_start_l2_1 -> {
                this.create(EditTextSnackbarActivity::class.java)
            }
            R.id.nav_start_l2_2 -> {
                this.create(BottomSheetActivity::class.java)
            }
        }
        val drawerLayout: DrawerLayout = findViewById(R.id.drawer_layout)
        drawerLayout.closeDrawer(GravityCompat.START)
        return true
    }

    // Настройка фона для элемента заголовка в nav_header_main
    private fun setNavHeaderBackground(header: View) {
        val bgId = when (currentTheme()) {
            R.style.AppThemeDob -> R.drawable.side_nav_bar_dob
            R.style.AppThemeIc -> R.drawable.side_nav_bar_ic
            R.style.AppThemePg -> R.drawable.side_nav_bar_pg
            else -> R.drawable.side_nav_bar
        }

        header.background = ContextCompat.getDrawable(this, bgId)
    }
}
