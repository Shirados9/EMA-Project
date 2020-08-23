package NWUP.com

import NWUP.com.Alarm.activities.AlarmFragment
import NWUP.com.Stoppuhr.StoppuhrFragment
import NWUP.com.Timer.TimerFragment
import NWUP.com.Weltuhr.WeltuhrFragment
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //Default fragment
        loadFragment(WeltuhrFragment())

        bottom_navigation.setOnNavigationItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.weltuhr -> {
                    loadFragment(WeltuhrFragment())
                    return@setOnNavigationItemSelectedListener true
                }
                R.id.alarm -> {
                    loadFragment(AlarmFragment())
                    return@setOnNavigationItemSelectedListener true
                }
                R.id.stoppuhr -> {
                    loadFragment(StoppuhrFragment())
                    return@setOnNavigationItemSelectedListener true
                }
                R.id.timer -> {
                    loadFragment(TimerFragment())
                    return@setOnNavigationItemSelectedListener true
                }
                else -> {
                    return@setOnNavigationItemSelectedListener false
                }
            }
        }
    }

    private fun loadFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction().also { fragmentTransaction->
            fragmentTransaction.replace(R.id.frame_layout, fragment)
            fragmentTransaction.commit()
        }
    }
}
