package k.s.yarlykov.materialdesign.ui.lesson2

import android.graphics.Typeface
import android.os.Bundle
import android.view.View
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
            if (!isAuthSuccessful()) {
                onAuthAction(retryAuth)
            }
        }
    }

    private fun isAuthSuccessful(): Boolean {
        return false
    }

    private fun onAuthAction(op: (View) -> Unit) {

        with(
            Snackbar.make(
                parent_l2_1,
                resources.getString(R.string.error_auth),
                Snackbar.LENGTH_LONG
            )
        ) {
            setActionTextColor(this@EditTextSnackbarActivity.getColorFromAttr(R.attr.colorPrimary))
            setAction(resources.getString(R.string.retry_action), op)

            // Стиль и размер шрифта Action
            view.findViewById<TextView>(com.google.android.material.R.id.snackbar_action).also {view ->
                view.textSize *= 0.7f
                view.setTypeface(view.typeface, Typeface.BOLD)
            }
            show()
        }
    }

    // Очистить поля ввода для повтора авторизации
    private val retryAuth: (View) -> Unit = {
        loginInputView.text?.clear()
        passwordInputView.text?.clear()
        loginInputView.requestFocus()
    }
}