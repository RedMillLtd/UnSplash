package uk.co.red_mill.unsplash.view.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.activity_main.*
import uk.co.red_mill.unsplash.R


class MainActivity : AppCompatActivity() {

    private val mOnNavigationItemSelectedListener =
        BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.navigation_home -> {
//                message.setText(R.string.title_home)
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_dashboard -> {
//                message.setText(R.string.title_dashboard)
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_notifications -> {
//                message.setText(R.string.title_notifications)
                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)

        // Add project list fragment if this is first creation
        if (savedInstanceState == null) {
            val fragment = ImageListFragment()

            supportFragmentManager.beginTransaction()
                .add(R.id.container, fragment, ImageListFragment.TAG).commit()
        }
    }
//
//    /** Shows the project detail fragment  */
//    fun show(image: UnsplashImage) {
//        val projectFragment = ProjectFragment.forProject(project.name)
//
//        supportFragmentManager
//            .beginTransaction()
//            .addToBackStack("project")
//            .replace(
//                R.id.fragment_container,
//                projectFragment, null
//            ).commit()
//    }
}
