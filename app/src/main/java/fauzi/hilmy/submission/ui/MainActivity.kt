package fauzi.hilmy.submission.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import com.google.android.material.bottomnavigation.BottomNavigationView
import fauzi.hilmy.submission.R
import fauzi.hilmy.submission.ui.content.ContentFragment

class MainActivity : AppCompatActivity() {

    private val SELECTED_MENU = "selected_menu"
    private var navView: BottomNavigationView? = null

    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.action_movie -> {
                switchFragment(ContentFragment.newInstance(true))
                return@OnNavigationItemSelectedListener true
            }
            R.id.action_tvshow -> {
                switchFragment(ContentFragment.newInstance(false))
                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }

    private fun switchFragment(fragment: Fragment) {
        supportFragmentManager
            .beginTransaction()
            .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
            .replace(R.id.container, fragment)
            .commit()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        navView = findViewById(R.id.navigation)
        navView!!.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)

        switchFragment(ContentFragment.newInstance(true))
        savedInstanceState?.getInt(SELECTED_MENU) ?: (navView!!.selectedItemId == R.id.action_movie)
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putInt(SELECTED_MENU, navView!!.selectedItemId)
    }
}
