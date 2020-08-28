package NWUP.com.Alarm.service

import NWUP.com.Alarm.data.Alarm
import NWUP.com.Alarm.data.AlarmRepository
import android.app.Service
import android.content.Intent
import android.os.IBinder
import androidx.lifecycle.LifecycleService
import androidx.lifecycle.Observer


class RescheduleAlarmsService : LifecycleService() {
    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        super.onStartCommand(intent, flags, startId)
        val alarmRepository = AlarmRepository(application)
        alarmRepository.alarmsLiveData.observe(
            this,
            Observer<List<Alarm>> { alarms ->
                for (a in alarms) {
                    if (a.started) {
                        a.schedule(applicationContext)
                    }
                }
            })
        return Service.START_STICKY
    }

    override fun onBind(intent: Intent): IBinder? {
        super.onBind(intent)
        return null
    }
}