package k.s.yarlykov.materialdesign.ui.lesson2

import android.graphics.Typeface
import android.os.Bundle
import android.view.View
import android.widget.TextView
import com.google.android.material.snackbar.Snackbar
import k.s.yarlykov.materialdesign.ui.lesson4.ContentSelectionActivity
import k.s.yarlykov.materialdesign.R
import k.s.yarlykov.materialdesign.extentions.create
import k.s.yarlykov.materialdesign.extentions.getColorFromAttr
import k.s.yarlykov.materialdesign.ui.BaseActivity
import kotlinx.android.synthetic.main.activity_autorization.*

class AuthorizationActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_autorization)
        initViews()
    }

    private fun initViews() {
        buttonEnter.setOnClickListener {
            if (isAuthSuccessful()) {
                this@AuthorizationActivity.create(ContentSelectionActivity::class.java)

//                postAuthSnackbar(
//                    message = resources.getString(R.string.ok_message),
//                    callback = snackCallback)
            } else {

                val actionText = resources.getString(R.string.snack_action_retry)
                val actionHandler = retryAuth

                postAuthSnackbar(
                    message = resources.getString(R.string.invalid_message),
                    action = Pair(actionText, actionHandler))
            }
        }

        buttonExit.setOnClickListener { finish() }
    }

    // Авторизация пользователя
    private fun isAuthSuccessful(): Boolean {
        return true
//        return validateInput()
    }

    // Действия после авторизации: показать Snackbar
    private fun postAuthSnackbar(
        message: String,                                // Секст сообщения
        action: Pair<String, (View) -> Unit>? = null,   // Action: текст на кнопке и обработчик
        callback: (Snackbar.Callback)? = null           // Обработчик событий в Snackbar
    ) {
        with(
            Snackbar.make(
                parent_l2_1,
                message,
                Snackbar.LENGTH_LONG
            )
        ) {

            // Обработчик Action
            action?.let {
                setActionTextColor(this@AuthorizationActivity.getColorFromAttr(R.attr.colorPrimary))
                setAction(it.first, it.second)

                // Стиль и размер шрифта кнопки Action
                view.findViewById<TextView>(com.google.android.material.R.id.snackbar_action).also { view ->
                    view.setTypeface(view.typeface, Typeface.BOLD)
                }
            }

            // Обработчик событий в Snackbar
            callback?.let { cb ->
                addCallback(cb)
            }

            show()
        }
    }


    // Валидация введенного текста с подсвечиванием ошибок
    private fun validateInput(): Boolean {

        // Проверка поля Username
        with(loginInputView) {
            if (checkLogin.matches(text.toString())) {
                error = null
            } else {
                error = "Invalid username"
                return false
            }
        }

        with(passwordInputView) {
            if (checkPassword.matches(text.toString())) {
                error = null

            } else {
                error = "Invalid password"
                return false
            }
        }

        return true
    }

    // Скрыть сообщения об ошибке ввода
    private val retryAuth: (View) -> Unit = {
        loginInputView.error = null
        passwordInputView.error = null
    }

    private val snackCallback = object : Snackbar.Callback() {

        override fun onDismissed(transientBottomBar: Snackbar?, event: Int) {
            super.onDismissed(transientBottomBar, event)

            if (event == DISMISS_EVENT_TIMEOUT) {
                refreshViews()
            }
        }
    }

    // Очистить поля ввода для повтора авторизации
    private fun refreshViews() {
        passwordInputView.text?.clear()
        loginInputView.text?.clear()
        loginInputView.requestFocus()
    }

    companion object {
        val checkLogin = Regex("^[A-Z,a-z]{3,5}$")
        val checkPassword = Regex("^(?=^.{4,}$)(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?!.*\\s).*$")
    }
}
