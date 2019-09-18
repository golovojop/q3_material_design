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
            if (isAuthSuccessful()) {
                postAuthAction(getData, resources.getString(R.string.oops_message))
            } else {
                postAuthAction(retryAuth, resources.getString(R.string.invalid_message))
            }
        }
    }

    // Авторизация пользователя
    private fun isAuthSuccessful(): Boolean {
        return validateInput()
    }

    // Действия после авторизации
    private fun postAuthAction(onAction: (View) -> Unit, message: String) {

        with(
            Snackbar.make(
                parent_l2_1,
                message,
                Snackbar.LENGTH_LONG
            )
        ) {
            setActionTextColor(this@EditTextSnackbarActivity.getColorFromAttr(R.attr.colorPrimary))
            setAction(resources.getString(R.string.snack_action), onAction)

            // Стиль и размер шрифта Action
            view.findViewById<TextView>(com.google.android.material.R.id.snackbar_action).also {view ->
                view.setTypeface(view.typeface, Typeface.BOLD)
            }
            show()
        }
    }

    // Валидация введенного текста с подсвечиванием ошибок
    private fun validateInput() : Boolean {

        fun showError(view: TextView, error: String) = view.setError(error)
        fun hideError(view: TextView) = view.setError(null)

        val login = loginInputView.text.toString()
        val password = passwordInputView.text.toString()

        if(checkLogin.matches(login)) {
            hideError(loginInputView)
        } else {
            showError(loginInputView, "Invalid username")
            return false
        }

        if(checkPassword.matches(password)) {
            hideError(passwordInputView)
        } else {
            showError(passwordInputView, "Invalid password")
            return false
        }

        return true
    }

    // Заглушка
    private val retryAuth: (View) -> Unit = {}

    // Очистить поля ввода для повтора авторизации
    private val getData: (View) -> Unit = {
        loginInputView.text?.clear()
        passwordInputView.text?.clear()
        loginInputView.requestFocus()
    }

    companion object {
        val checkLogin = Regex("^[A-Z][a-z]{3,5}$")
        val checkPassword = Regex("^(?=^.{4,}$)(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?!.*\\s).*$")
    }
}