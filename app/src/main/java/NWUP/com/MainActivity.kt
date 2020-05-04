package NWUP.com

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.Switch
import android.widget.ToggleButton
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*

class MainActivity : AppCompatActivity() {

//    lateinit var alarmFragment: AlarmFragment
//    lateinit var stoppuhrFragment: StoppuhrFragment
//    lateinit var timerFragment: TimerFragment
//    lateinit var weltuhrFragment: WeltuhrFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //Default fragment
        loadFragment(WeltuhrFragment())

        bottom_navigation.setOnNavigationItemSelectedListener { menuItem ->
            when{
                menuItem.itemId == R.id.weltuhr -> {
                    loadFragment(WeltuhrFragment())
                    return@setOnNavigationItemSelectedListener true
                }
                menuItem.itemId == R.id.alarm -> {
                    loadFragment(AlarmFragment())
                    return@setOnNavigationItemSelectedListener true
                }
                menuItem.itemId == R.id.stoppuhr -> {
                    loadFragment(StoppuhrFragment())
                    return@setOnNavigationItemSelectedListener true
                }
                menuItem.itemId == R.id.timer -> {
                    loadFragment(TimerFragment())
                    return@setOnNavigationItemSelectedListener true
                }
                else -> {
                    return@setOnNavigationItemSelectedListener false
                }
            }
        }

//        val bottomNavigationView: BottomNavigationView = findViewById(R.id.bottom_navigation)
//
//        weltuhrFragment = WeltuhrFragment();
//        supportFragmentManager
//            .beginTransaction()
//            .replace(R.id.frame_layout, weltuhrFragment)
//            .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
//            .commit()
//
//        bottomNavigationView.setOnNavigationItemReselectedListener { item ->
//
//            when (item.itemId) {
//
//                R.id.alarm -> {
//                    alarmFragment = AlarmFragment();
//                    supportFragmentManager
//                        .beginTransaction()
//                        .replace(R.id.frame_layout, alarmFragment)
//                        .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
//                        .commit()
//                }
//
//                R.id.stoppuhr -> {
//                    stoppuhrFragment = StoppuhrFragment();
//                    supportFragmentManager
//                        .beginTransaction()
//                        .replace(R.id.frame_layout, stoppuhrFragment)
//                        .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
//                        .commit()
//                }
//                R.id.weltuhr -> {
//                    weltuhrFragment = WeltuhrFragment();
//                    supportFragmentManager
//                        .beginTransaction()
//                        .replace(R.id.frame_layout, weltuhrFragment)
//                        .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
//                        .commit()
//                }
//                R.id.timer -> {
//                    timerFragment = TimerFragment();
//                    supportFragmentManager
//                        .beginTransaction()
//                        .replace(R.id.frame_layout, timerFragment)
//                        .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
//                        .commit()
//                }
//            }
    }

    private fun loadFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction().also { fragmentTransaction->
            fragmentTransaction.replace(R.id.frame_layout, fragment)
            fragmentTransaction.commit()
        }
    }
}
