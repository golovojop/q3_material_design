/**
 * Materials
 *
 * Привязка анимации к состоянию BottomSheet
 * https://android.jlelse.eu/choreographic-animations-with-androids-bottomsheet-fef06e6ecb81
 * https://github.com/ogasimli/BottomSheetDemo
 */

package k.s.yarlykov.materialdesign.ui.lesson2

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

//        private void animateBottomSheetArrows(float slideOffset) {
//            mLeftArrow.setRotation(slideOffset * -180);
//            mRightArrow.setRotation(slideOffset * 180);
//        }

    }

    private fun animateBottomSheetArrows(slideOffset : Float) {
        l2_bottomSheet.rotation = slideOffset * -180f
    }


}