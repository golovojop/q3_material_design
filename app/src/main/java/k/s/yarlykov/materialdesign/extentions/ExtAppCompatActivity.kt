package k.s.yarlykov.materialdesign.extentions

import android.content.Intent
import android.util.TypedValue
import androidx.annotation.AttrRes
import androidx.appcompat.app.AppCompatActivity

// Создать активити
fun <T> AppCompatActivity.create(clazz: Class<T>) {
    this.startActivity(Intent(this, clazz))
}

// Получить цвет по атрибуту текущей темы
fun AppCompatActivity.getColorFromAttr(
    @AttrRes attrColor: Int,
    typedValue: TypedValue = TypedValue(),
    resolveRefs: Boolean = true
): Int {
    theme.resolveAttribute(attrColor, typedValue, resolveRefs)
    return typedValue.data
}