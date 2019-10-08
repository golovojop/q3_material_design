package k.s.yarlykov.materialdesign.ui.lesson6.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.GridLayoutManager
import k.s.yarlykov.materialdesign.R
import k.s.yarlykov.materialdesign.ui.lesson6.GridItemDecoration
import k.s.yarlykov.materialdesign.ui.lesson6.RVAdapter
import kotlinx.android.synthetic.main.content_selection.*

class VegetablesFragment : Fragment() {

    companion object {
        private val KEY_TEXT_ID = "KEY_TEXT_ID"
        private val KEY_PICS_ID = "KEY_PICS_ID"

        fun create(textId: Int, picsId: Int): VegetablesFragment {
            return VegetablesFragment().apply {
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
        return inflater.inflate(R.layout.content_selection, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
    }

    private fun initViews() {
        val textId: Int = arguments!!.getInt(KEY_TEXT_ID)
        val picsId: Int = arguments!!.getInt(KEY_PICS_ID)

        val stuffNames: Array<String> = resources.getStringArray(textId)
        val stuffPics = with(resources.obtainTypedArray(picsId)) {
            val li = mutableListOf<Int>()
            (0 until length()).forEach { i ->
                li.add(i, getResourceId(i, R.drawable.vegetables))
            }
            recycle()
            li.toTypedArray()
        }

        recycle_view.apply {
            setHasFixedSize(true)
            addItemDecoration(GridItemDecoration(2))
            itemAnimator = DefaultItemAnimator()
            layoutManager = GridLayoutManager(activity?.applicationContext, 2)
            adapter = RVAdapter(stuffPics.zip(stuffNames), R.layout.layout_rv_item_grid)
        }
    }
}