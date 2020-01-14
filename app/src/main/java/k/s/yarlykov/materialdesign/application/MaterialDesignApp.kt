package k.s.yarlykov.materialdesign.application

import android.app.Application
import k.s.yarlykov.materialdesign.R

class MaterialDesignApp : Application() {
    var currentTheme : Int = R.style.AppTheme

    override fun onCreate() {
        super.onCreate()
    }
}