package k.s.yarlykov.materialdesign.extentions

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity

fun <T> AppCompatActivity.create(clazz: Class<T>) {
    this.startActivity(Intent(this, clazz ))
}