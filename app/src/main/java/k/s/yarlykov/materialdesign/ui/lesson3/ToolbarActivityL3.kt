package k.s.yarlykov.materialdesign.ui.lesson3

import android.os.Bundle
import android.view.MenuItem
import k.s.yarlykov.materialdesign.R
import k.s.yarlykov.materialdesign.ui.BaseActivity
import kotlinx.android.synthetic.main.activity_toolbar_l3.*

class ToolbarActivityL3 : BaseActivity(){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_toolbar_l3)
        setSupportActionBar(toolbarSecondary)

        // Icon "<-"
        with(supportActionBar!!) {
            setDisplayHomeAsUpEnabled(true)
            title = getString(R.string.lesson_3_activity_title)
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                finish()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}