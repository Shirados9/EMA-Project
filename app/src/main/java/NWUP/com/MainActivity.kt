package NWUP.com

import NWUP.com.Alarm.activities.AlarmFragment
import NWUP.com.Stoppuhr.StoppuhrFragment
import NWUP.com.Timer.TimerFragment
import NWUP.com.Weltuhr.WeltuhrFragment
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        bottom_navigation.setOnNavigationItemSelectedListener { menuItem ->
            if (menuItem.itemId == bottom_navigation.selectedItemId)
                return@setOnNavigationItemSelectedListener true
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

        val type = intent.getStringExtra("startTimeFragment")
        if (type != null) {
            if (type == "timerFragment") {
                loadFragment(TimerFragment())
                bottom_navigation.menu.getItem(3).setChecked(true)
                return
            }
        }
//        if (intent.action.equals("startTimeFragment"))
//            loadFragment(TimerFragment())

        //Default fragment
        loadFragment(WeltuhrFragment())


    }

    private fun loadFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction().also { fragmentTransaction ->
            fragmentTransaction.replace(R.id.frame_layout, fragment)
            fragmentTransaction.commit()
        }

    }

    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)

        Toast.makeText(this, "Test", Toast.LENGTH_LONG).show()

    }
}
