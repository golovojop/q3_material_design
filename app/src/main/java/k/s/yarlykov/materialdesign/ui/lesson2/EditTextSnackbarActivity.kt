package k.s.yarlykov.materialdesign.ui.lesson2

import android.app.TaskStackBuilder
import android.graphics.Typeface
import android.os.Bundle
import android.view.View
import android.widget.TextView
import com.google.android.material.snackbar.Snackbar
import k.s.yarlykov.materialdesign.R
import k.s.yarlykov.materialdesign.extentions.getColorFromAttr
import k.s.yarlykov.materialdesign.ui.BaseActivity
import kotlinx.android.synthetic.main.activity_lesson2_text_fields.*
import java.util.regex.Pattern

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
            } else {
                onAuthAction(retryAuth)
            }
        }
    }

    private fun isAuthSuccessful(): Boolean {
        return validateInput()
    }

    private fun onAuthAction(action: (View) -> Unit) {

        with(
            Snackbar.make(
                parent_l2_1,
                resources.getString(R.string.error_auth),
                Snackbar.LENGTH_LONG
            )
        ) {
            setActionTextColor(this@EditTextSnackbarActivity.getColorFromAttr(R.attr.colorPrimary))
            setAction(resources.getString(R.string.retry_action), action)

            // Стиль и размер шрифта Action
            view.findViewById<TextView>(com.google.android.material.R.id.snackbar_action).also {view ->
                view.textSize *= 0.7f
                view.setTypeface(view.typeface, Typeface.BOLD)
            }
            show()
        }
    }

    private fun validateInput() : Boolean {
        val login = loginInputView.text.toString()
        val password = passwordInputView.text.toString()

        if(checkLogin.matcher(login).matches()) {
            hideError(loginInputView)
        } else {
            showError(loginInputView, "Incorrect username")
            return false
        }

        if(checkPassword.matcher(password).matches()) {
            hideError(passwordInputView)
        } else {
            showError(passwordInputView, "incorrect password")
            return false
        }

        return true
    }


    // Очистить поля ввода для повтора авторизации
    private val retryAuth: (View) -> Unit = {
        loginInputView.text?.clear()
        passwordInputView.text?.clear()
        loginInputView.requestFocus()
    }

    private fun showError(view: TextView, error: String) {
        view.error = error
    }
    fun hideError(view: TextView) {
        view.error = null
    }

    override fun onPrepareNavigateUpTaskStack(builder: TaskStackBuilder?) {

    }

    companion object {
        val checkLogin = Pattern.compile("^[A-Z][a-z]{3,5}$")
        val checkPassword = Pattern.compile("^(?=^.{4,}$)(?=.+\\d)(?=.+[a-z])(?=.+[A-Z])(?!.*\\s).*$")
    }

}