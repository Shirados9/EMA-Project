package NWUP.com.Alarm.activities

import NWUP.com.Alarm.data.Alarm
import NWUP.com.Alarm.service.AlarmService
import NWUP.com.R
import android.animation.ObjectAnimator
import android.animation.ValueAnimator
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.fragment_alarm_ringactivity.*
import java.util.*


class AlarmRingActivity: AppCompatActivity() {
    private var dismiss: Button = ringactivity_ring_dismiss
    private var snooze: Button = ringactivity_ring_snooze
    private var clock: ImageView = ringactivity_imageview


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fragment_alarm_ringactivity)


        dismiss.setOnClickListener {
            val intentService =
                Intent(applicationContext, AlarmService::class.java)
            applicationContext.stopService(intentService)
            finish()
        }
        snooze.setOnClickListener {
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




    private fun animateClock() {
        val rotateAnimation =
            ObjectAnimator.ofFloat(clock, "rotation", 0f, 20f, 0f, -20f, 0f)
        rotateAnimation.repeatCount = ValueAnimator.INFINITE
        rotateAnimation.duration = 800
        rotateAnimation.start()
    }
}
