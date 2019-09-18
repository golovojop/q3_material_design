package k.s.yarlykov.materialdesign.ui.lesson2

import android.graphics.Typeface
import android.os.Bundle
import android.widget.TextView
import com.google.android.material.snackbar.Snackbar
import k.s.yarlykov.materialdesign.R
import k.s.yarlykov.materialdesign.extentions.getColorFromAttr
import k.s.yarlykov.materialdesign.ui.BaseActivity
import kotlinx.android.synthetic.main.activity_lesson2_text_fields.*

class EditTextSnackbarActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lesson2_text_fields)
        initViews()
    }

    private fun initViews() {

        buttonEnter.setOnClickListener {
            if (!onAuthenticate()) {
                onAuthErrorAlert()
            }
        }
    }

    private fun onAuthenticate(): Boolean {
        return false
    }

    private fun onAuthErrorAlert() {

        with(
            Snackbar.make(
                parent_l2_1,
                resources.getString(R.string.error_auth),
                Snackbar.LENGTH_LONG
            )
        ) {
            setActionTextColor(this@EditTextSnackbarActivity.getColorFromAttr(R.attr.colorPrimary))
            setAction(resources.getString(R.string.retry_action)) {
                loginInputView.text?.clear()
                passwordInputView.text?.clear()
                loginInputView.requestFocus()
            }

            // Стиль и размер шрифта Action
            view.findViewById<TextView>(com.google.android.material.R.id.snackbar_action).also {view ->
                view.textSize *= 0.7f
                view.setTypeface(view.typeface, Typeface.BOLD)
            }
            show()
        }
    }
}