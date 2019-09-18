package k.s.yarlykov.materialdesign.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import k.s.yarlykov.materialdesign.application.MaterialDesignApp

abstract class BaseActivity :  AppCompatActivity() {
    lateinit var app : MaterialDesignApp

    override fun onCreate(savedInstanceState: Bundle?) {
        // Установка темы
        app = applicationContext as MaterialDesignApp
        setTheme(app.currentTheme)
        super.onCreate(savedInstanceState)
    }

    // Обновить текущую схему
    fun updateTheme(theme: Int) {
        app.currentTheme = theme
    }

    fun currentTheme() = app.currentTheme
}