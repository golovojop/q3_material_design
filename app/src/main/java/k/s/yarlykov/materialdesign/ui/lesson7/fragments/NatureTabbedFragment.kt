package k.s.yarlykov.materialdesign.ui.lesson7.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.GridLayoutManager
import k.s.yarlykov.materialdesign.*
import k.s.yarlykov.materialdesign.ui.lesson7.GridItemDecorationL7
import k.s.yarlykov.materialdesign.ui.lesson7.RVAdapterL7
import kotlinx.android.synthetic.main.fragment_place_holder.*

class NatureTabbedFragment : Fragment() {

    companion object {

        fun create(bundle: Bundle?): NatureTabbedFragment {
            return NatureTabbedFragment().apply {
                arguments = Bundle().apply {
                    putBundle(KEY_BUNDLE, bundle)
                }
            }
        }
    }

    var place = Place.MOUNTAIN

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedtate: Bundle?) : View? {

        val layoutId = arguments?.getBundle(KEY_BUNDLE)!!.let { bundle ->
            place = bundle.getSerializable(KEY_PLACE) as Place
            bundle.getInt(KEY_LAYOUT_ID)
        }

        return inflater.inflate(layoutId, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val pics = when(place) {
            Place.MOUNTAIN -> R.array.mountains_pics
            Place.VILLAGE -> R.array.village_pics
            Place.RIVER -> R.array.rivers_pics
        }

        loadData(pics)
    }

    private fun loadData(picsId : Int) {
        val stuffPics = with(resources.obtainTypedArray(picsId)) {

            mutableListOf<Int>().also { li ->
                (0 until length()).forEach { i ->
                    li.add(i, getResourceId(i, R.drawable.m_01))
                }
                recycle()
            }
        }
        initRecycleView(stuffPics)
    }

    private fun initRecycleView(data : List<Int>) {

        recycle_view_l7.apply {
            setHasFixedSize(true)
            addItemDecoration(GridItemDecorationL7(2))
            itemAnimator = DefaultItemAnimator()
            layoutManager = GridLayoutManager(activity?.applicationContext, 2)
            adapter = RVAdapterL7(data, R.layout.layout_rv_item_nature)
        }
    }
}