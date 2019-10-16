package k.s.yarlykov.materialdesign.ui.lesson7

import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.google.android.material.tabs.TabLayout
import k.s.yarlykov.materialdesign.KEY_LAYOUT_ID
import k.s.yarlykov.materialdesign.KEY_PLACE
import k.s.yarlykov.materialdesign.Place
import k.s.yarlykov.materialdesign.R
import k.s.yarlykov.materialdesign.ui.lesson7.fragments.NatureTabbedFragment
import kotlinx.android.synthetic.main.activity_tabbed.*

class TabbedActivity : AppCompatActivity() {

    private val PREFS_NAME = "tabbed_activity_prefs"
    private val PREFS_KEY_THEME = "key_theme"

    override fun onCreate(savedInstanceState: Bundle?) {
        currentTheme = readTheme()
        setTheme(currentTheme)

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tabbed)
        setSupportActionBar(toolbar_l8)

        fab.setOnClickListener {
            finish()
        }

        initTabs()
    }

    private fun readTheme(): Int {
        return currentTheme
//        return with(getSharedPreferences(PREFS_NAME, MODE_PRIVATE)) {
//            if (contains(PREFS_KEY_THEME)) {
//                getInt(PREFS_KEY_THEME, R.style.AppThemeL4)
//            } else {
//                R.style.AppThemeL4
//            }
//        }
    }

    private fun writeTheme(theme: Int) {
        currentTheme = theme
//        with(getSharedPreferences(PREFS_NAME, MODE_PRIVATE).edit()) {
//            putInt(PREFS_KEY_THEME, theme)
//            apply()
//        }
    }

    private fun initTabs() {
        val sectionsPagerAdapter = CustomFragmentPagerAdapter(supportFragmentManager)

        sectionsPagerAdapter.addFragment(createFragment(Place.MOUNTAIN), getString(R.string.tab_text_1))
        sectionsPagerAdapter.addFragment(createFragment(Place.RIVER), getString(R.string.tab_text_2))
        sectionsPagerAdapter.addFragment(createFragment(Place.VILLAGE), getString(R.string.tab_text_3))

        view_pager.adapter = sectionsPagerAdapter
        tabs.setupWithViewPager(view_pager)

        tabs.getTabAt(0)?.icon = getDrawable(R.drawable.ic_mountain)
        tabs.getTabAt(1)?.icon = getDrawable(R.drawable.ic_water)
        tabs.getTabAt(2)?.icon = getDrawable(R.drawable.ic_village)

        tabs.tabGravity = TabLayout.GRAVITY_FILL
    }

    private fun createFragment(place: Place, layoutId: Int = R.layout.fragment_place_holder): Fragment {
        return with(Bundle()) {
            putInt(KEY_LAYOUT_ID, layoutId)
            putSerializable(KEY_PLACE, place)
            NatureTabbedFragment.create(this)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        Log.e("TAG", "onCreateOptionsMenu")
        menuInflater.inflate(R.menu.color_balls, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {

        when (item!!.itemId) {
            R.id.menu_theme_1 -> {
                writeTheme(R.style.AppThemeDob)
                recreate()
            }
            R.id.menu_theme_2 -> {
                writeTheme(R.style.AppThemeIc)
                recreate()
            }
            R.id.menu_theme_3 -> {
                writeTheme(R.style.AppThemePg)
                recreate()
            }
        }
        return super.onOptionsItemSelected(item)
    }

    companion object {
        private var currentTheme : Int = R.style.AppThemeL4    }
}
