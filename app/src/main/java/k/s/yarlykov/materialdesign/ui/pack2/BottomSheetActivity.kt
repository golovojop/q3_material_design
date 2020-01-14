package k.s.yarlykov.materialdesign.ui.pack2

import android.os.Bundle
import android.view.View
import com.google.android.material.bottomsheet.BottomSheetBehavior
import k.s.yarlykov.materialdesign.R
import k.s.yarlykov.materialdesign.ui.BaseActivity
import kotlinx.android.synthetic.main.layout_lesson2_bottomsheel.*

class BottomSheetActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_social_share)

        with(BottomSheetBehavior.from(l2_bottomSheet)) {

            bottomSheetCallback = object : BottomSheetBehavior . BottomSheetCallback () {
                override fun onSlide(bottomSheet: View, slideOffset: Float) {
                    animateBottomSheetArrows(slideOffset)
                }

                override fun onStateChanged(bottomSheet: View, newState: Int) {
                }
            }
        }
    }

    // Поворот стрелки.
    private fun animateBottomSheetArrows(slideOffset : Float) {
        imgArrow.rotation = slideOffset * -180f
    }
}