package NWUP.com.Alarm.receiver

import NWUP.com.Alarm.service.AlarmService
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.Build
import android.widget.Toast
import java.util.*


class AlertReceiver : BroadcastReceiver() {
    companion object {
        const val RECURRING = "RECURRING"
        const val MONDAY = "MONDAY"
        const val TUESDAY = "TUESDAY"
        const val WEDNESDAY = "WEDNESDAY"
        const val THURSDAY = "THURSDAY"
        const val FRIDAY = "FRIDAY"
        const val SATURDAY = "SATURDAY"
        const val SUNDAY = "SUNDAY"
        const val TITLE = "TITLE"
    }


    override fun onReceive(context: Context?, intent: Intent?) {

        if (intent != null) {
            if (Intent.ACTION_BOOT_COMPLETED == intent.action) {
                val toastText = String.format("Alarm Reboot")
                Toast.makeText(context, toastText, Toast.LENGTH_SHORT).show()
                startRescheduleAlarmsService(context)
            } else {
                val toastText = String.format("Alarm Received")
                Toast.makeText(context, toastText, Toast.LENGTH_SHORT).show()

                val bundle = intent.extras
                if (bundle!!.getBoolean(RECURRING)) {
                    startAlarmService(context, intent)
                } else {
                    if (alarmIsToday(intent)) {

                            if (bundle.getBoolean(RECURRING)) {
                                startAlarmService(context, intent)
                            } else {
                                if (alarmIsToday(intent)) {
                                    startAlarmService(context, intent)
                                }
                            }
                    }
                }
            }
        }
    }

    private fun alarmIsToday(intent: Intent): Boolean {
        val calendar = Calendar.getInstance()
        calendar.timeInMillis = System.currentTimeMillis()
        val today = calendar.get(Calendar.DAY_OF_WEEK)
        val bundle = intent.extras
        //switch case in kotlin

        when (today) {
            Calendar.MONDAY -> {
                return bundle!!.getBoolean(MONDAY)
            }
            Calendar.TUESDAY -> {
                return bundle!!.getBoolean(TUESDAY)
            }
            Calendar.WEDNESDAY -> {
                return bundle!!.getBoolean(WEDNESDAY)
            }
            Calendar.THURSDAY -> {
                return bundle!!.getBoolean(THURSDAY)
            }
            Calendar.FRIDAY -> {
                return bundle!!.getBoolean(FRIDAY)
            }
            Calendar.SATURDAY -> {
                return bundle!!.getBoolean(SATURDAY)
            }
            Calendar.SUNDAY -> {
                return bundle!!.getBoolean(SUNDAY)
            }
        }
        return false
    }

    private fun startAlarmService(context: Context?, intent: Intent) {
        val intentService = Intent(context, AlarmService::class.java)
        val bundle = intent.extras
        intentService.putExtra(TITLE, bundle!!.getString(TITLE))
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            context!!.startForegroundService(intentService)
        } else {
            context!!.startService(intentService)
        }
    }

    private fun startRescheduleAlarmsService(context: Context?) {
        val intentService = Intent(context, AlarmService::class.java)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            context!!.startForegroundService(intentService)
        } else {
            context!!.startService(intentService)
        }
    }
}