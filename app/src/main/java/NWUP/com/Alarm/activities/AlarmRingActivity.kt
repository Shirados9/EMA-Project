package NWUP.com.Alarm.activities

import NWUP.com.Alarm.data.Alarm
import NWUP.com.Alarm.service.AlarmService
import NWUP.com.R
import android.animation.ObjectAnimator
import android.animation.ValueAnimator
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.fragment_alarm_ringactivity.*
import java.util.*

//creates the fragment to cancel/snooze the ongoing alarm
class AlarmRingActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fragment_alarm_ringactivity)


        //starts new Intent to stop the Alarm
        ringactivity_ring_dismiss.setOnClickListener {
            val intentService =
                Intent(applicationContext, AlarmService::class.java)
            applicationContext.stopService(intentService)
            finish()
        }
        //constructs a new Alarm which starts in 10 Minutes
        ringactivity_ring_snooze.setOnClickListener {
            val calendar: Calendar = Calendar.getInstance()
            calendar.timeInMillis = System.currentTimeMillis()
            calendar.add(Calendar.MINUTE, 10)
            val alarm = Alarm(
                Random().nextInt(Int.MAX_VALUE),
                calendar.get(Calendar.HOUR_OF_DAY),
                calendar.get(Calendar.MINUTE),
                title = "Snooze",
                started = true,
                recurring = false,
                monday = false,
                tuesday = false,
                wednesday = false,
                thursday = false,
                friday = false,
                saturday = false,
                sunday = false
            )
            alarm.schedule(applicationContext)
            val intentService =
                Intent(applicationContext, AlarmService::class.java)
            applicationContext.stopService(intentService)
            finish()
        }
        animateClock()
    }


    //lets the Alarm Image move
    private fun animateClock() {
        val rotateAnimation =
            ObjectAnimator.ofFloat(ringactivity_imageview, "rotation", 0f, 20f, 0f, -20f, 0f)
        rotateAnimation.repeatCount = ValueAnimator.INFINITE
        rotateAnimation.duration = 800
        rotateAnimation.start()
    }
}
