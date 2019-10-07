package k.s.yarlykov.materialdesign.ui.lesson6

import android.content.Context.WINDOW_SERVICE
import android.graphics.Point
import android.graphics.Rect
import android.view.View
import android.view.WindowManager
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView


class GridItemDecoration(var columns: Int) : RecyclerView.ItemDecoration() {

    override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State) {

        val wm = parent.context.getSystemService(WINDOW_SERVICE) as WindowManager

        val parentWidth = with(Point()){
            wm.defaultDisplay.getSize(this)
            this.x
        }

        val offset = (parentWidth/columns - view.layoutParams.width)/2
        val params = view.layoutParams

        if(params is GridLayoutManager.LayoutParams) {
            with(outRect) {
                top = offset

                if(params.spanIndex % 2 == 0) {
                    left = offset
                    right = offset/2
                } else {
                    left = offset/2
                    right = offset
                }
            }
        }
    }
}