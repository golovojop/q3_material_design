package k.s.yarlykov.materialdesign.ui.lesson6.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.content.res.AppCompatResources.getDrawable
import androidx.fragment.app.Fragment
import com.google.android.material.tabs.TabLayout
import k.s.yarlykov.materialdesign.KEY_LAYOUT_ID
import k.s.yarlykov.materialdesign.KEY_PLACE
import k.s.yarlykov.materialdesign.Place
import k.s.yarlykov.materialdesign.R
import k.s.yarlykov.materialdesign.ui.lesson7.CustomFragmentPagerAdapter
import k.s.yarlykov.materialdesign.ui.lesson7.fragments.NatureContentFragment
import kotlinx.android.synthetic.main.app_bar_content_selection.*
import kotlinx.android.synthetic.main.fragment_nature.*

class NatureFragment : Fragment() {

    companion object {
        private val KEY_TEXT_ID = "KEY_TEXT_ID"
        private val KEY_PICS_ID = "KEY_PICS_ID"

        fun create(textId: Int, picsId: Int): NatureFragment {
            return NatureFragment().apply {
                arguments = Bundle().apply {
                    putInt(KEY_TEXT_ID, textId)
                    putInt(KEY_PICS_ID, picsId)
                }
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_nature, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initTabs()
    }

    private fun initTabs() {
        val sectionsPagerAdapter = CustomFragmentPagerAdapter(activity!!.supportFragmentManager)

        sectionsPagerAdapter.addFragment(createFragment(Place.MOUNTAIN), getString(R.string.tab_text_1))
        sectionsPagerAdapter.addFragment(createFragment(Place.RIVER), getString(R.string.tab_text_2))
        sectionsPagerAdapter.addFragment(createFragment(Place.VILLAGE), getString(R.string.tab_text_3))

        view_pager.adapter = sectionsPagerAdapter
        tabs.setupWithViewPager(view_pager)

//        tabs.getTabAt(0)?.icon = getDrawable(R.drawable.ic_spring)
//        tabs.getTabAt(1)?.icon = getDrawable(R.drawable.ic_beach)
//        tabs.getTabAt(2)?.icon = getDrawable(R.drawable.ic_autumn)

        tabs.tabGravity = TabLayout.GRAVITY_FILL
    }

    private fun createFragment(place : Place, layoutId : Int = R.layout.fragment_place_holder) : Fragment {
        return with(Bundle()) {
            putInt(KEY_LAYOUT_ID, layoutId)
            putSerializable(KEY_PLACE, place)
            NatureContentFragment.create(this)
        }
    }
}