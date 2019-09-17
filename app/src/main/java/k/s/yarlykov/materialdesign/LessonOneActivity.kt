package k.s.yarlykov.materialdesign

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity

class LessonOneActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lesson1)
    }

    companion object {
        fun start(context: Context) {
            context.startActivity(Intent(context, LessonOneActivity::class.java))
        }
    }
}