package k.s.yarlykov.materialdesign.ui.lesson4

import android.os.Bundle
import android.os.Parcelable
import android.view.MenuItem
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.recyclerview.widget.*
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.navigation.NavigationView
import k.s.yarlykov.materialdesign.R
import k.s.yarlykov.materialdesign.ui.lesson6.*
import k.s.yarlykov.materialdesign.ui.lesson6.fragments.FruitsFragment
import k.s.yarlykov.materialdesign.ui.lesson6.fragments.NatureFragment
import k.s.yarlykov.materialdesign.ui.lesson6.fragments.VegetablesFragment
import kotlinx.android.parcel.Parcelize
import kotlinx.android.synthetic.main.app_bar_content_selection.*
import kotlinx.android.synthetic.main.content_selection.*

class ContentSelectionActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_content_selection)
        setSupportActionBar(toolbar_l4)

        val fab: FloatingActionButton = findViewById(R.id.fab_l4)
        fab.setOnClickListener {
            // Если стек пустой, то закрыть активити
            if(!supportFragmentManager.popBackStackImmediate()) {
                finish()
            }
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

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        val fragment = when (item.itemId) {
            R.id.nav_vegetables -> {
                VegetablesFragment.create(R.array.name_of_vegs, R.array.veg_pics)
            }
            R.id.nav_nature -> {
                NatureFragment.create(R.array.name_of_place, R.array.nature_pics)
            }
            else -> {
                FruitsFragment.create(R.array.name_of_fruits, R.array.fruit_pics)
            }
        }

        supportFragmentManager
            .beginTransaction()
            .replace(R.id.content_frame, fragment)
            .addToBackStack(fragment.javaClass.simpleName)
            .commit()

        val drawerLayout: DrawerLayout = findViewById(R.id.drawer_layout_l4)
        drawerLayout.closeDrawer(GravityCompat.START)
        return true
    }
}

@Parcelize
data class LayoutType(val type: TYPE): Parcelable {
    enum class TYPE {
        LINEAR_SIMPLE, LINEAR_CARD, GRID_CARD, STAGGERED_GRID
    }
}
