package k.s.yarlykov.materialdesign.ui.lesson6

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager

class StaggeredItemDecoration(var columns: Int, val offset: Int) : RecyclerView.ItemDecoration() {

    override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State) {

        val params = view.layoutParams as StaggeredGridLayoutManager.LayoutParams

//        if(params is StaggeredGridLayoutManager.LayoutParams) {
            with(outRect) {
                top = offset

                if (params.spanIndex % 2 == 0) {
                    left = offset
                    right = offset / 2
                } else {
                    left = offset / 2
                    right = offset
                }
            }
//        }
    }
}