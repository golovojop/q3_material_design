package k.s.yarlykov.materialdesign.ui.lesson7

import android.os.Bundle
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment
import com.google.android.material.tabs.TabLayout
import k.s.yarlykov.materialdesign.KEY_LAYOUT_ID
import k.s.yarlykov.materialdesign.KEY_PLACE
import k.s.yarlykov.materialdesign.Place
import k.s.yarlykov.materialdesign.R
import k.s.yarlykov.materialdesign.ui.lesson7.fragments.NatureTabbedFragment

import kotlinx.android.synthetic.main.activity_tabbed.*

class TabbedActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tabbed)

        fab.setOnClickListener {
            finish()
        }

        initTabs()
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

    private fun createFragment(place : Place, layoutId : Int = R.layout.fragment_place_holder) : Fragment {
        return with(Bundle()) {
            putInt(KEY_LAYOUT_ID, layoutId)
            putSerializable(KEY_PLACE, place)
            NatureTabbedFragment.create(this)
        }
    }
}
