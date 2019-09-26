package k.s.yarlykov.materialdesign.ui.lesson4

import android.os.Bundle
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import androidx.core.view.GravityCompat
import androidx.appcompat.app.ActionBarDrawerToggle
import android.view.MenuItem
import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.material.navigation.NavigationView
import androidx.appcompat.app.AppCompatActivity
import android.view.View
import k.s.yarlykov.materialdesign.R
import kotlinx.android.synthetic.main.app_bar_content_selection.*
import kotlinx.android.synthetic.main.content_selection.*

class ContentSelectionActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_content_selection)
        setSupportActionBar(toolbar_l4)

        val fab: FloatingActionButton = findViewById(R.id.fab_l4)
        fab.setOnClickListener { _ ->
            finish()
        }
        val drawerLayout: DrawerLayout = findViewById(R.id.drawer_layout_l4)
        val navView: NavigationView = findViewById(R.id.nav_view_l4)
        val toggle = ActionBarDrawerToggle(
            this, drawerLayout, toolbar_l4,
            R.string.navigation_drawer_open,
            R.string.navigation_drawer_close
        )
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()

        navView.setNavigationItemSelectedListener(this)
    }

    override fun onBackPressed() {
        val drawerLayout: DrawerLayout = findViewById(R.id.drawer_layout_l4)
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

//    override fun onCreateOptionsMenu(menu: Menu): Boolean {
//        // Inflate the menu; this adds items to the action bar if it is present.
//        menuInflater.inflate(R.menu.content_selection, menu)
//        return true
//    }
//
//    override fun onOptionsItemSelected(item: MenuItem): Boolean {
//        // Handle action bar item clicks here. The action bar will
//        // automatically handle clicks on the Home/Up button, so long
//        // as you specify a parent activity in AndroidManifest.xml.
//        return when (item.itemId) {
//            R.id.action_settings -> true
//            else -> super.onOptionsItemSelected(item)
//        }
//    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {

        when (item.itemId) {
            R.id.nav_fruits -> {
                setCardContent(R.drawable.fruits, R.string.pic_fruits)
            }
            R.id.nav_vegetables -> {
                setCardContent(R.drawable.vegetables_purple, R.string.pic_vegetables)
            }
            R.id.nav_nature -> {
                setCardContent(R.drawable.nature, R.string.pic_nature)
            }
         }

        menuFrame.visibility = View.VISIBLE

        val drawerLayout: DrawerLayout = findViewById(R.id.drawer_layout_l4)
        drawerLayout.closeDrawer(GravityCompat.START)
        return true
    }

    private fun setCardContent(picId : Int, textId : Int) {
        menuPic.setImageResource(picId)
        menuCategory.text = getString(textId)
    }
}
